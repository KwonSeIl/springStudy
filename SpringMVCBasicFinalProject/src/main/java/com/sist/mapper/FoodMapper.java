package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;


import com.sist.vo.*;
public interface FoodMapper {
   @Select({
      "<script>"
      +"SELECT cno,title,poster,subject "
      +"FROM project_category "
      +"WHERE "
      +"<if test='cno==1'> "
      +"cno BETWEEN 31 AND 42"
      +"</if>"
      +"<if test='cno==2'> "
      +"cno BETWEEN 43 AND 48"
      +"</if>"
      +"<if test='cno==3'> "
      +"cno BETWEEN 49 AND 60"
      +"</if>"
      +"</script>"
   })
   public List<CategoryVO> categoryListData(Map map);
   //카테고리별 목록 출력
	@Select("SELECT fno,name,poster,address,tel,type,score "
			+"FROM project_food "
			+"WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
	
	@Select("SELECT title,subject FROM project_category "
			+"WHERE cno=#{cno}")
	public CategoryVO categoryInfoData(int cno);
	
	//상세보기
	@Select("SELECT * FROM project_food "
			+"WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	//검색: 동적 쿼리 (<if>,<choose>,<foreach>:IN, <trim>,<where>,<set>) => 다중 조건문
	// MyBatis => 핵심, Join, subquery, procedure,function,trigger
	// PD SYSTEM => 절대 지원X
	@Select({
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
	public List<FoodVO> foodFindData(Map map);
}