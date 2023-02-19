package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.BookMapper;
import com.sist.vo.BookVO;

@Repository("bdao")
public class BookDAO {
	@Autowired
	private BookMapper mapper;
	/*@Select("SELECT no,price,publisher,author,title,rownum "
			+"FROM(SELECT no,price,publisher,author,title FROM books ORDER BY no ASC) "
			+"WHERE type=#{type}")*/
	public List<BookVO> bookCategoryListData(String type)
	{
		return mapper.bookCategoryListData(type);
	}
	
	//@Select("SELECT DISTINCT type FROM books")
	public List<String> bookCategory()
	{
		return mapper.bookCategory();
	}

}
