package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface RecipeMapper {
	@Select("SELECT no,title,chef,rownum "
			+ "FROM (SELECT no,title,chef FROM recipe ORDER BY no ASC) "
			+ "WHERE rownum<=30")
	public List<RecipeVO> recipeListData();
}
