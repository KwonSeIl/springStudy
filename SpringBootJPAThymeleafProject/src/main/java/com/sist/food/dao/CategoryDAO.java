package com.sist.food.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.food.entity.*;
@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity,Integer>{
	@Query(value="SELECT cno,title,subject,poster,link FROM project_category",nativeQuery = true)
	public List<CategoryEntity>	categoryListData();
}
