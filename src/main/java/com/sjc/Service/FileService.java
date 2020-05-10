package com.sjc.Service;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService{
	 public static String FILE_FOLDER;
	 public static Double EXPECTED_IMAGE_RATIO=1.5;

	 static{
		  FILE_FOLDER=System.getProperty("user.dir")+"/Files/";
	 }

	 public void upload(MultipartFile file) throws IOException{
		  byte[] bytes = file.getBytes();
		  Path path = Paths.get(FILE_FOLDER + file.getOriginalFilename());
		  Files.write(path, bytes);
	 }

	 public Resource download(String fileName) throws FileNotFoundException{
		  File file=new File(FILE_FOLDER+"/"+fileName);
		  InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		  return resource;
	 }

	 public long getFileLength(String fileName) throws FileNotFoundException{
		  File file=new File(FILE_FOLDER+"/"+fileName);
		  return file.length();
	 }


	 public boolean isImageOnRatio(MultipartFile file) throws IOException{
		  ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
		  BufferedImage originalImage=ImageIO.read(inputStream);
          int width=originalImage.getWidth();
          int height=originalImage.getHeight();
          double ratio=(width*1.0)/(height*1.0);
          System.out.println("ration is "+ratio);
          return isInRatio(ratio);
	 }

	 boolean isInRatio(double ratio){
	 	 double difference=diff(EXPECTED_IMAGE_RATIO,ratio);
	 	 return difference<0.1&&difference>-0.1;
	 }

	 double diff(double a,double b){
	 	 if(a>b){
	 	 	 return a-b;
		 }
	 	 return b-a;
	 }



}
