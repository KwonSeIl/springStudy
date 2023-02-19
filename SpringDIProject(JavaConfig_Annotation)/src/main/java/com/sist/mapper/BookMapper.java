package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
/*
 * 	private int no,price;
   private String condition,publisher,author,title,type;
 */
public interface BookMapper {
	@Select("SELECT no,price,publisher,author,title,type,rownum "
			+"FROM(SELECT no,price,publisher,author,title,type FROM books ORDER BY no ASC) "
			+"WHERE type=#{type}")
	public List<BookVO> bookCategoryListData(String type);
	
	@Select("SELECT DISTINCT type FROM books")
	public List<String> bookCategory();
}
