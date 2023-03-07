package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface FoodMapper {
   @Select("SELECT cno,title,poster "
         +"FROM project_category ORDER BY cno ASC")
   public List<CategoryVO> categoryListData();
   
   @Select("SELECT title,subject FROM project_category "
		   +"WHERE cno=#{cno}")
   public CategoryVO categoryInfoData(int cno);
   
   @Select("SELECT fno,name,poster,score,tel,address,type "
		   +"FROM project_food "
		   +"WHERE cno=#{cno}")
   public List<FoodVO> foodListData(int cno);
   
   @Update("UPDATE project_food SET "
		   +"hit=hit+1 "
		   +"WHERE fno=#{fno}")
   public void foodHitIncrement(int fno);
   
   @Select("SELECT * FROM project_food "
		   +"WHERE fno=#{fno}")
   public FoodVO foodDetailData(int fno);
}