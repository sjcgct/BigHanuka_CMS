package com.sjc.Repository;

import java.util.List;
import java.util.Optional;

import com.sjc.Model.Hog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HogRepository extends CrudRepository<Hog, Long> {
	 @Override
	 Optional<Hog> findById(Long aLong);

	 @Override
	 Hog save(Hog hog);

	 @Override
	 void deleteById(Long aLong);

	 @Query(value = "SELECT id FROM hog WHERE title = ?1",nativeQuery = true)
	 Long getIdFromTitle(String title);

     @Query(value = "WITH NumberedMyTable AS ( SELECT id , ROW_NUMBER() OVER ( ORDER BY datetime DESC ) AS RowNumber FROM hog ) SELECT id FROM NumberedMyTable WHERE RowNumber BETWEEN ?1 AND ?2",nativeQuery = true)
	 List<Long> getIdFromAndTo(Integer from,Integer to);

}