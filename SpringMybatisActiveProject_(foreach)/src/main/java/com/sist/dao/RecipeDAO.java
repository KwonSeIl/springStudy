package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository
public class RecipeDAO {
   @Autowired
   private RecipeMapper mapper;
   
   /*@Select("SELECT title,chef,poster,rownum "
			 +"FROM recipe "
			 +"WHERE rownum<=40 AND REGEXP_LIKE(title,#{menu}")*/
   public List<RecipeVO> recipeFindData(String menu)
   {
	   return mapper.recipeFindData(menu);
   }
}