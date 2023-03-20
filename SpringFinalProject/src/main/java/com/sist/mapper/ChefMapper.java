package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface ChefMapper {
   @Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num "
		  +"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2 "
		  +"FROM chef)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<ChefVO> chefListData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/50.0) FROM chef")
   public int chefTotalPage();
   
   //쉐프의 작품 출력 
   @Select("SELECT no,title,poster,chef,rownum "
		  +"FROM recipe "
		  +"WHERE chef=#{chef} AND rownum<=20")
   public List<RecipeVO> chefMakeRecipeData(String chef);
   
}