package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
public interface FoodMapper {
   @Select("SELECT cno,title,subject,poster "
         +"FROM project_category")
   public List<CategoryVO> categoryListData();

   @Select("SELECT fno,cno,name,address,poster,tel,type,score "
         +"FROM project_food "
         +"WHERE cno=#{cno}")
   public List<FoodVO> foodListData(int cno);
   
   @Select("SELECT title,subject FROM project_category WHERE cno=#{cno}")
   public CategoryVO categoryInfoData(int cno);
   
   @Select("SELECT * FROM project_food "
		   +"WHERE fno=#{fno}")
   public FoodVO foodDetailData(int fno);
   
   @Select("SELECT fno,name,poster,num "
		   +"FROM (SELECT fno,name,poster,rownum as num "
		   +"FROM (SELECT fno,name,poster "
		   +"FROM food_location WHERE address LIKE '%'||#{addr}||'%' ORDER BY fno ASC)) "
		   +"WHERE num BETWEEN #{start} AND #{end}")
   public List<FoodVO> foodSearchData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/20.0) "
		   +"FROM food_location "
		   +"WHERE address LIKE '%'||#{addr}||'%'")
   public int foodSearchTotalPage(Map map);
   
   @Select("SELECT * FROM food_location "
		   +"WHERE fno=#{fno}")
   public FoodVO foodLocationDetailData(int fno);
   
   @Select({
	   "<script>"
	   +"SELECT cno,title,poster "
	   +"FROM project_category "
	   +"WHERE "
	   +"<if test='no==1'>"
	   +"cno BETWEEN 31 AND 42"
	   +"</if>"
	   +"<if test='no==2'>"
	   +"cno BETWEEN 43 AND 48"
	   +"</if>"
	   +"<if test='no==3'>"
	   +"cno BETWEEN 49 AND 60"
	   +"</if>"
	   +"</script>"
   })
   public List<CategoryVO> categoryVueData(Map map);

}