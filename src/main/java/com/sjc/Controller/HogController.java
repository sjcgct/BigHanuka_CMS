package com.sjc.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sjc.Model.Hog;
import com.sjc.Service.HogService;
import com.sjc.Utills.Message;
import com.sjc.ViewModels.HogViewModel;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@ControllerAdvice
@CrossOrigin
public class HogController
{

	 @Autowired
	 HogService hogService;

	 @GetMapping("/hog/{title}")
	 public ResponseEntity<Object> getPost(@PathVariable String title) throws IOException{
	 	  Hog hog=hogService.getHog(title);
	 	  if(hog==null){
	 	  	 ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Hog post ,Not Exists"));
			 return data;
		  }
		  HogViewModel hogViewModel=new HogViewModel(hog);
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.OK).body(hogViewModel);
		  return data;
	 }

	 @GetMapping("/hog/{fromIndex}/{toIndex}")
	 public ResponseEntity<Object> getPostsCountFromAndTo(@PathVariable String fromIndex,@PathVariable String toIndex) throws IOException{
	 	 int startingIndex=Integer.valueOf(fromIndex);
	 	 int endIndex=Integer.valueOf(toIndex);
	 	 List<Hog> hogList=hogService.getHogsFromAndTo(startingIndex,endIndex);
	 	 if(hogList==null){
			  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Hog Records ,Not Exists in given range"));
			  return data;
		 }
	 	  List<HogViewModel> hogViewModelList=HogViewModel.getHogViewModelListFrom(hogList);
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.OK).body(hogViewModelList);
		  return data;
	 }

	 @PostMapping ("/hog/")
	 public ResponseEntity<Object> createPost(@RequestBody HogViewModel hogViewModel) throws IOException{
		  Hog hog=hogService.createHog(hogViewModel);

		  if (hog==null) {
			   ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Given Post Already Exists"));
			   return data;
		  }

		 ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.CREATED).body(hog);
		 return data;
	 }

	 @PutMapping("/hog/{title}")
	 public ResponseEntity<Object> deletePost(@PathVariable String title) throws IOException{
		  if(!hogService.deleteHog(title)){
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Post Not Exists"));
		  return data;
		  }
		  ResponseEntity<Object> data=ResponseEntity.status(HttpStatus.OK).body(new Message("Post Deleted"));
		  return data;
	 }


	 @PostMapping("/hog")
	 public ResponseEntity<Object> createPost(@PathVariable String Title){
           return null;
	 }

	 @ExceptionHandler (JDBCConnectionException.class)
	 public ResponseEntity<Object> handleDBErrors (JDBCConnectionException exception) { // Or whatever exception type you want to handle
		  return ResponseEntity.badRequest ().body (new Message( "Server down"));
	 }

	 @ExceptionHandler (JsonMappingException.class) // Or whatever exception type you want to handle
	 public ResponseEntity<Object> handleConverterErrors (JsonMappingException exception) { // Or whatever exception type you want to handle
		  return ResponseEntity.badRequest ().body (new Message ("Enter valid data"));
	 }

	 @ExceptionHandler(NumberFormatException.class)
	 public ResponseEntity<Object> handlePathVariableIDErrors (NumberFormatException exception) { // Or whatever exception type you want to handle
		  return ResponseEntity.badRequest ().body (new Message("Enter valid data type"));
	 }


}
