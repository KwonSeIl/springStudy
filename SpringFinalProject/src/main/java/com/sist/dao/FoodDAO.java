package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class FoodDAO {
  @Autowired
  private FoodMapper mapper;
  
  /*@Select("SELECT cno,title,poster "
		  +"FROM project_category")*/
  public List<CategoryVO> categoryListData()
  {
	  return mapper.categoryListData();  
  }
  
  /*@Select("SELECT title,subject FROM project_category "
		  +"WHERE cno=#{cno}")*/
  public CategoryVO categoyInfoData(int cno)
  {
	  return mapper.categoyInfoData(cno);
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
		  +"WHERE fno=#{fno}")
   public FoodVO foodDetailData(int fno);*/
    public FoodVO foodDetailData(int fno) {
	   mapper.foodHitIncrement(fno);
	   return mapper.foodDetailData(fno);
    }

	public FoodVO foodCookieDetailData(int fno) {
		  return mapper.foodDetailData(fno);
	}
	
	/*
	 *   // 검색 
	   @Select("SELECT fno,name,poster,score,num "
			  +"FROM (SELECT fno,name,poster,score,rownum as num "
			  +"FROM (SELECT fno,name,poster,score "
			  +"FROM food_location "
			  +"WHERE address LIKE '%'||#{address}||'%' ORDER BY fno ASC)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")
	   public List<FoodVO> foodLocationFindData(Map map);
	   
	   // 상세보기 
	   @Select("SELECT * FROM food_location "
			  +"WHERE fno=#{fno}")
	   public FoodVO foodLocationDetailData(int fno);
	 */
	public List<FoodVO> foodLocationFindData(Map map){
		return mapper.foodLocationFindData(map);
	}
	public FoodVO foodLocationDetailData(int fno) {
		return mapper.foodLocationDetailData(fno);
	}
	//총페이지
	/*@Select("SELECT CEIL(COUNT(*)/20.0) "
			  +"FROM food_location "
			  +"WHERE address LIKE '%'||#{address}||'%'")*/
	public int foodFindTotalPage(String address) {
		return mapper.foodFindTotalPage(address);
	}
	// Top-N = 인라인뷰 이용 
	// top-7
	/*@Select("SELECT fno,name,address,rownum "
			  +"FROM (SELECT fno,name,address "
			  +"FROM project_food ORDER BY hit DESC) "
			  +"WHERE rownum<=7")*/
	public List<FoodVO> foodTop7()
	{
		return mapper.foodTop7();
	}
	
	/*@Select("SELECT DISTINCT name FROM food_locateion "
		   +"WHERE length(name)>1 OR name!='라구'")*/
   public List<String> foodGetNameData()
   {
	   return mapper.foodGetNameData();
   }
   
   /*@Select("SELECT fno,name,poster,score,rownum "
   		   +"FROM (SELECT fno,name,poster,score "
		   +"FROM food_location ORDER BY fno ASC))"
		   +"WHERE name=#{name} AND rownum=1")*/
   public FoodVO foodInfoData(String name)
   {
	   return mapper.foodInfoData(name);
   }
}