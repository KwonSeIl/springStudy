package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
public class EmpDAO extends SqlSessionDaoSupport {
	/*
	 * 
	 */
	public List<EmpVO> empListData()
	{
		return getSqlSession().selectList("empListData");
	}
	public EmpVO empDetailData(int empno)
	{
		return getSqlSession().selectOne("empDetailData", empno);
	}
}
