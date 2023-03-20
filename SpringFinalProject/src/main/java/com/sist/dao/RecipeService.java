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
	public String recipeRowCount()
	{
		return rMapper.recipeRowCount();
	}
	
	/*@Select("SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,num "
			  +"FROM (SELECT chef,poster,mem_cont1,mem_cont3,mem_cont7,mem_cont2,rownum as num "
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
	/*@Select("SELECT COUNT(*) FROM recipeDetail "
	  +"WHERE no=#{no}")*/
public int recipeDetailCount(int no)
{
return rMapper.recipeDetailCount(no);
}

/*@Select("SELECT * FROM recipeDetail "
	  +"WHERE no=#{no}")*/
public RecipeDetailVO recipeDetailData(int no)
{
return rMapper.recipeDetailData(no);
}
/*
*  @Select("SELECT no,goods_name,goods_price,goods_poster,rownum "
+"FROM (SELECT no,goods_name,goods_price,goods_poster "
+"FROM goods_all "
+"WHERE goods_name LIKE '%'||#{goods_name}||'%' "
+"ORDER BY TO_NUMBER(REPLACE(REPLACE(goods_price,',',''),'원','')) ASC) "
+"WHERE rownum<=3")
public List<GoodsVO> goodsListData(String goods_name);
*/
public List<GoodsVO> goodsListData(String goods_name)
{
return rMapper.goodsListData(goods_name);
}
}
