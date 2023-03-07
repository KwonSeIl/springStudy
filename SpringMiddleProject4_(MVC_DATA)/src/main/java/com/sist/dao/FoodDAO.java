package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	/*
	 * @Select("SELECT cno,title,poster,subject "
			+"FROM project_category "
			+"WHERE cno BETWEEN #{start} AND #{end}")
		public List<CategoryVO> categoryListData(Map map);
	 */
	public List<CategoryVO> categoryListData(Map map)
	{
		return mapper.categoryListData(map);
	}
	
	/*@Select("SELECT fno,name,poster,address,tel,type "
			+"FROM project_food "
			+"WHERE con=#{cno}")*/
	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno);
	}
	/*
	 * @Select("SELECT title,subject FROM project_category "
				+"WHERE cno=#{cno}")
		public CategoryVO categoryInfoData(int cno);
	 */
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
}
