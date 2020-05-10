package com.sjc.Model;

import com.sjc.ViewModels.HogViewModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "hog")
public class Hog{

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
	 Long id;

	 @Column(name = "datetime")
	 Timestamp timestamp;

	 @Column(name = "title")
	 String title;

	 @Column(name = "content")
	 String contentPath;

	 @Column(name = "imgpath")
	 String imagePath;

	 @Column(name ="name")
	 String name;

	 @Column(name="category")
	 String category;

	 @Column(name="author")
	 String author;


	 public Hog(HogViewModel hogViewModel){
	 	 this.timestamp=hogViewModel.getTimestamp();
	 	 this.title=hogViewModel.getTitle();
	 	 this.contentPath=hogViewModel.getContentPath();
	 	 this.imagePath=hogViewModel.getImgPath();
	 	 this.name=hogViewModel.getName();
	 	 this.author=hogViewModel.getAuthor();
	 	 this.category=hogViewModel.getCategory();

	 }

	 public  Hog(){}

	 public Long getId(){
		  return id;
	 }

	 public Timestamp getTimestamp(){
		  return timestamp;
	 }

	 public String getTitle(){
		  return title;
	 }

	 public String getContentPath(){
		  return contentPath;
	 }

	 public String getImagePath(){
		  return imagePath;
	 }

	 public String getName(){
		  return name;
	 }

	 public String getCategory(){
		  return category;
	 }
	 public String getAuthor(){
		  return author;
	 }

}
