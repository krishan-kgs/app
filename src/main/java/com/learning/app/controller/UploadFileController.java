package com.learning.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		final Map<String, Object> response = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.OK;
		try{
			String uploadMultipleFiles = uploadFileService.uploadMultipleFiles(files);
			response.put("uploadMultipleFiles", uploadMultipleFiles);
			response.put("msg", "success");
			response.put("ok", 1);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			response.put("msg", new StringBuilder("failure"));
			response.put("ok", 0);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(response, status);
		
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
