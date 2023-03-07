package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
import com.sist.vo.*;
public class RecipeDAO extends SqlSessionDaoSupport{
	public List<RecipeVO> recipeListData()
	{
		return getSqlSession().selectList("recipeListData");
		// 받는 경우 =>resultType="RecipeVO"
		// List<RecipeVO> => selectList
		// RecipeVO => selectOne
	}
}
