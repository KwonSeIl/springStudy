package com.sist.dao;
import java.util.*;

import org.mybatis.spring.support.SqlSessionDaoSupport;
public class StudentDAO extends SqlSessionDaoSupport {
	//1. 데이터 추가
	public void studentInsert(StudentVO vo)
	{
		getSqlSession().insert("studentInsert",vo);
	}
	//2. 데이터 목록
	public List<StudentVO> studentListData()
	{
		return getSqlSession().selectList("studentListData");
	}
	//3. 데이터 검색
	public StudentVO studentDetailData(int hakbun)
	{
		return getSqlSession().selectOne("studentDetailData", hakbun);
	}
	//4. 데이터 수정
	public void studentUpdate(StudentVO vo)
	{
		getSqlSession().update("studentUpdate", vo);
	}
	//5. 데이터 삭제
	public void studentDelete(int hakbun)
	{
		getSqlSession().delete("studentDelete", hakbun);
	}
}
