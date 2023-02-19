package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
import com.sist.vo.*;
public class FoodDAO extends SqlSessionDaoSupport{
	public List<FoodVO> foodListData()
	{
		return getSqlSession().selectList("foodListData");
	}

}
