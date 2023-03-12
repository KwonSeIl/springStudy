package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.*;
@Service
public class RecipeService {
	@Autowired
	private RecipeMapper rMapper;
	
	@Autowired
	private ChefMapper cMapper;
	
	/*@Select("SELECT no,title,poster,chef,num "
			+"FROM (SELECT no,title,poster,chef,rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)no,title,poster,chef "
			+"FROM recipe)) "
			+"WHERE num BETWEEN #{start} AND #{end}")*/
	public List<RecipeVO> recipeListData(Map map)
	{
		return rMapper.recipeListData(map);
	}
	
	//@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe")
	public int recipeTotalPage()
	{
		return rMapper.recipeTotalPage();
	}
	
	//@Select("SELECT COUNT(*) FROM recipe")
	public String recipeRowCount() //총개수
	{
		return rMapper.recipeRowCount();
	}
	
	/*@Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
			+"FROM(SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,roownum as num "
			+"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2 "
			+"FROM chef)) "
			+"WHERE num BETWEEN #{start} AND #{end}")*/
	public List<ChefVO> chefListData(Map map)
	{
		return cMapper.chefListData(map);
	}
	
	//@Select("SELECT CEIL(COUNT(*)/50.0) FROM chef")
	public int chefTotalPage()
	{
		return cMapper.chefTotalPage();
	}
	
	/*@Select("SELECT no,title,poster,chef,rownum "
			   +"FROM recipe "
			   +"WHERE chef=#{chef} AND rownum<=20")*/
	   public List<RecipeVO> chefMakeRecipeData(String chef)
	   {
		   return cMapper.chefMakeRecipeData(chef);
	   }
}
