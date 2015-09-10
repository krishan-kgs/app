package com.learning.app.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.app.service.UploadFileService;

@RestController
@RequestMapping("upload")
public class UploadFileController {
	
	@Autowired
	private UploadFileService uploadFileService;

	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uplaodDocs(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) throws IOException{
		
		try{
			uploadFileService.uploadMultipleFiles(files);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody String handleException(HttpServletRequest request, Exception ex) {
		System.out.println("Error occurred in serving requested URL[ " + request.getRequestURL() + "?" + request.getQueryString() + " ], error ::: "
				+ ex.getMessage());
		String errorMessage = "Procession failed :: " + ex.getMessage();
		//DBObject dbObject = new BasicDBObject().append("msg", errorMessage).append("ok", 0);
		return errorMessage;
	}
}
