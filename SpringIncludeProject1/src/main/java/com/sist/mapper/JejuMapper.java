package com.sist.mapper;
/*
 * TITLE     VARCHAR2(300) 
	URL       VARCHAR2(500) 
	POSTER    VARCHAR2(300) 
	CONTENT    VARCHAR2(500) 
	PRICE     VARCHAR2(50)  
	PRICE2    NUMBER(38)    
	IMAGE     VARCHAR2(4000) 
	INFO      VARCHAR2(4000) 
	NO        NUMBER        
	ADDR      VARCHAR2(200) 
	TYPE      VARCHAR2(200) 
	HIT       NUMBER
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface JejuMapper {
	@Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT no,title,poster "
			+"FROM jejuLocation ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuLocationVO> jejuLocationListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuLocation")
	public int jejuTotalPage();
	
	@Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT no,title,poster "
			+"FROM jejuFood ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuFoodVO> jejuFoodListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuFood")
	public int jejuFoodTotalPage();
	
	@Select("SELECT * FROM jejuFood "
			+"WHERE no=#{no}")
	public JejuFoodVO jejuDetailData(int no);
}
