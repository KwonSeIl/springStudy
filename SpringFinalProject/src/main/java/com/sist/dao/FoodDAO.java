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
   
   /*// 검색
   @Select("SELECT fno,name,poster,score,num "
		   +"FROM (SELECT fno,name,poster,score,rownum as num "
		   +"FROM (SELECT fno,name,poster,score "
		   +"FROM food_location "
		   +"WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC)) "
		   +"WHERE num BETWEEN #{start} AND #{end}")*/
   public List<FoodVO> foodLocationFindData(Map map)
   {
	   return mapper.foodLocationFindData(map);
   }
  
   // 상세보기
  /* @Select("SELECT * FROM food_location "
		   +"WHERE fno=#{fno}")*/
   public FoodVO foodLocationDetailData(int fno)
   {
	   return mapper.foodLocationDetailData(fno);
   }
   /*총페이지 구하기
   @Select("SELECT CEIL(COUNT(*)/20.0) "
		   +"FROM food_location "
		   +"WHERE address LIKE '%'||#{address}||'%'")*/
   public int foodFindTotalPage(String address) 
   {
	   return mapper.foodFindTotalPage(address);
   }
   // Top-N => 인라인뷰를 이용해서 사용 (순서대로 들어와야하기 때문) rownum은 이미 지정 돼 있음
   /*@Select("SELECT fno,name,address,rownum "
		   +"FROM (SELECT fno,name,address "
		   +"FROM project_food ORDER BY hit DESC "
		   +"WHERE rownum<=7")*/
   public List<FoodVO> foodTop7()
   {
	   return mapper.foodTop7();
   }
}