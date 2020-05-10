package com.sjc.ViewModels;

import com.sjc.Model.Hog;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HogViewModel{
	 Long id;
	 Timestamp timestamp;
	 String title,contentPath,imgPath;
	 String author,name,category;

	 public HogViewModel(){};


	 public HogViewModel(Hog hog) throws IOException{
		  this.id = hog.getId();
		  this.timestamp = hog.getTimestamp();
		  this.title = hog.getTitle();
		  this.contentPath=hog.getContentPath();
		  this.imgPath=hog.getImagePath();
		  this.name=hog.getName();
		  this.category=hog.getCategory();
		  this.author=hog.getAuthor();
	 }

	 public static List<HogViewModel> getHogViewModelListFrom(List<Hog> hogList) throws IOException{
	 	 List<HogViewModel> hogViewModelList=new ArrayList<>();
	 	 for(Hog h:hogList){
	 	 	 if(h!=null){
	 	 	 	 hogViewModelList.add(new HogViewModel(h));
			 }
		 }
	 	 return hogViewModelList;
	 }

	 public Long getId(){
		  return id;
	 }

	 public void setId(Long id){
		  this.id = id;
	 }

	 public Timestamp getTimestamp(){
		  return timestamp;
	 }

	 public void setTimestamp(Timestamp timestamp){
		  this.timestamp = timestamp;
	 }

	 public String getTitle(){
		  return title;
	 }

	 public void setTitle(String title){
		  this.title = title;
	 }

	 public String getContentPath(){
		  return contentPath;
	 }

	 public void setContentPath(String contentPath){
		  this.contentPath = contentPath;
	 }

	 public String getImgPath(){
		  return imgPath;
	 }

	 public void setImgPath(String imgPath){
		  this.imgPath = imgPath;
	 }

	 public String getAuthor(){
		  return author;
	 }

	 public void setAuthor(String author){
		  this.author = author;
	 }

	 public String getName(){
		  return name;
	 }

	 public void setName(String name){
		  this.name = name;
	 }

	 public String getCategory(){
		  return category;
	 }

	 public void setCategory(String category){
		  this.category = category;
	 }
}

