package com.sist.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.mapper.*;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<CategoryVO> categoryListData(Map map)
	{
		return mapper.categoryListData(map);
	}
	//카테고리별 목록 출력
	/*@Select("SELECT fno,name,poster,address,tel,type,score "
			+"FROM project_food "
			+"WHERE cno=#{cno}")*/
	public List<FoodVO> foodListData(int cno)
	{
		return mapper.foodListData(cno);
	}
	
	/*@Select("SELECT title,subject FROM project_category "
			+"WHERE cno=#{cno}")*/
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
	
	//상세보기
	/*@Select("SELECT * FROM project_category "
			+"WHERE fno=#{fno}")*/
	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
	/*
	 * @Select({
		"<script>"
		+"SELECT fno,name,poster,num "
		+"FROM (SELECT fno,name,poster,rownum as num "
		+"FROM (SELECT fno,name,poster "
		+"FROM food_location "
		+"<if test=\"ss!='all'\">"
		+"WHERE address LIKE '%'||#{ss}||'%'"
		+"</if>"
		+"ORDER BY fno ASC)) "
		+"WHERE num BETWEEN #{start} AND #{end}"
		+"</script>"
	})
	 */
	public List<FoodVO> foodFindData(Map map)
	{
		return mapper.foodFindData(map);
	}
	
	
	
}
