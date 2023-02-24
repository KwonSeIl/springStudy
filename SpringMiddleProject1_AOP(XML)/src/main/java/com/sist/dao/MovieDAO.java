package com.sist.dao;
//OOP => 보완 (AOP)
public class MovieDAO {
	public void getConnection()
	{
		System.out.println("오라클 연결..");
	}
	public void disConnection()
	{
		System.out.println("오라클 닫기..");
	}
	public void select()
	{
		getConnection(); // ============== 공통모듈(반복)
		System.out.println("SELECT 문장 수행"); //핵심모듈
		disConnection(); // ============== 공통모듈(반복)
	}
	public void insert()
	{
		getConnection();
		System.out.println("INSERT 문장 수행");
		disConnection();
	}
	public void update()
	{
		getConnection();
		System.out.println("UPDATE 문장 수행");
		disConnection();
	}
	public void delete()
	{
		getConnection();
		System.out.println("DELETE 문장 수행");
		disConnection();
	}

}
