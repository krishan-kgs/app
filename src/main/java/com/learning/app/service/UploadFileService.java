package com.learning.app.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
	
	public List<String> uploadMultipleFiles(MultipartFile[] files) throws IOException, Exception;

}
