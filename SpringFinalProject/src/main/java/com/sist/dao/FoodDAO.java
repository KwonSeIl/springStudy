package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;

import java.util.*;
@Repository
public class FoodDAO {
   @Autowired
   private FoodMapper mapper;
   
   /*@Select("SELECT cno,title,poster "
         +"FROM project_category ORDER BY cno ASC")*/
   public List<CategoryVO> categoryListData()
   {
      return mapper.categoryListData();
   }
   
    /* @Select("SELECT title,subject FROM project_category "
		   +"WHERE cno=#{cno}")*/
   public CategoryVO categoryInfoData(int cno)
   {
	   return mapper.categoryInfoData(cno);
   }
   /*@Select("SELECT fno,name,poster,score,tel,address,type "
		   +"FROM project_food "
		   +"WHERE cno=#{cno}")*/
   public List<FoodVO> foodListData(int cno)
   {
	   return mapper.foodListData(cno);
   }
   /*@Update("UPDATE project_food SET "
		   +"hit=hit+1 "
		   +"WHERE fno=#{fno}")
   public void foodHitIncrement(int fno);
   
   @Select("SELECT * FROM project_food "
		   +"WHERE fno=#{fno}")*/
   public FoodVO foodDetailData(int fno)
   {
	   mapper.foodHitIncrement(fno);
	   return mapper.foodDetailData(fno);
   }
   
   public FoodVO foodCookieDetailData(int fno)
   {
	   return mapper.foodDetailData(fno);
   }
}