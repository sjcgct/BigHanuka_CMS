package com.sjc.Controller;

import com.sjc.Service.FileService;
import com.sjc.Utills.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@ControllerAdvice
public class FileController{

	 @Autowired
	 FileService fileService;


	 @PostMapping("/file")
	 public ResponseEntity<Object> upload(@RequestParam ("file") MultipartFile file) throws IOException{
		  if (file.isEmpty()) {
			   ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("File is empty"));
			   return data;
		  }
		  fileService.upload(file);
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.CREATED).body(new Message("File uploaded Successfully"));
		  return data;
	 }

	 @GetMapping("/file/{fileName}")
	 public ResponseEntity<Resource> download(@PathVariable String fileName) throws IOException {
	 	  long fileLength=fileService.getFileLength(fileName);
		  Resource resource=null;
	 	  if(fileLength>0){
	 	  	   resource=fileService.download(fileName);
			   return ResponseEntity.ok()
					   .contentLength(fileLength)
					   .contentType(MediaType.parseMediaType("application/octet-stream"))
					   .body(resource);
		  }

		  return ResponseEntity.badRequest().body(resource);

	 }

	 @PostMapping("/image")
	 public ResponseEntity<Object> downloadImageSizeOf(@RequestParam ("image") MultipartFile file) throws IOException{
		  if (file.isEmpty()) {
			   ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("File is empty"));
			   return data;
		  }
          if(!fileService.isImageOnRatio(file)){
			   ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message("Image ratio should be 3:2"));
			   return data;
		  }
		  fileService.upload(file);
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.CREATED).body(new Message("File uploaded Successfully"));
		  return data;
	 }

	 @ExceptionHandler(IOException.class)
	 public ResponseEntity<Object> handlePathVariableIDErrors (IOException exception) { // Or whatever exception type you want to handle
		  return ResponseEntity.badRequest ().body (new Message("Check path or File not exists"));
	 }
}
