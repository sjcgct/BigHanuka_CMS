package com.sjc.Service;

import com.sjc.Model.Hog;
import com.sjc.Repository.HogRepository;
import com.sjc.ViewModels.HogViewModel;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class HogService{
	 @Autowired
	 HogRepository hogRepository;

	 public Hog getHog(String title){
	 	 Long id=hogRepository.getIdFromTitle(title);
	 	 if(id==null){
	 	 	 return null;
		 }
         Hog hog=hogRepository.findById(id).get();
         return hog;
	 }

	 public Hog createHog(HogViewModel hogViewModel) throws IOException{
		  Long id=hogRepository.getIdFromTitle(hogViewModel.getTitle());
		  if(id==null){
			   Hog hog=new Hog(hogViewModel);
			   hog=hogRepository.save(hog);
			   return hog;
		  }
	 	 return null;
	 }

	 public  boolean deleteHog(String title){
		  Long id = hogRepository.getIdFromTitle(title);
		  if (id != null){
			   hogRepository.deleteById(id);
			   return true;
		  }
		  return false;
	 }

	 public List<Hog> getHogsFromAndTo(int from,int to){
	   	 List<Long> ids=hogRepository.getIdFromAndTo(from,to);
	   	 if(ids==null){
	   	 	 return null;
		 }

	   	 ArrayList<Hog> hogList=new ArrayList<>();
	   	 for(Long id:ids){
	   	 	 if(id!=null){
	   	 	 	  Hog hog=hogRepository.findById(id).get();
				  hogList.add(hog);
			 }
		 }
	   	 return hogList;
	 }
}
