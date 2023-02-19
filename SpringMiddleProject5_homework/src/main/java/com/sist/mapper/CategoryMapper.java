package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.CategoryVO;

public interface CategoryMapper {
	@Select("SELECT cno,cate_name FROM project_goods_category ORDER BY cno")
	public List<CategoryVO> categoryListData();
}