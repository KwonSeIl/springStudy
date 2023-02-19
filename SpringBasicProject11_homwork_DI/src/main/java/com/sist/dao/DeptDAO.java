package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
public class DeptDAO extends SqlSessionDaoSupport {
	public List<DeptVO> deptListData()
	{
		return getSqlSession().selectList("deptListData");
	}
	public DeptVO deptDetailData(int deptno)
	{
		return getSqlSession().selectOne("deptDetailData", deptno);
	}
	
}
