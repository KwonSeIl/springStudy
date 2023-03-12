package com.sist.myapp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	private DeptDAO dao;
	
	public void setDao(DeptDAO dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
				new ClassPathXmlApplicationContext("application-datasource.xml"); 
		MainClass mc=(MainClass)app.getBean("mc");
		List<DeptVO> list=mc.dao.deptListData();
		for(DeptVO vo:list)
		{
			System.out.println(vo.getDeptno()+" "
					+vo.getDname()+" "
					+vo.getLoc());
		}
		
		DeptVO vo=mc.dao.deptDetailData(10);
		System.out.println(vo.getDeptno()+" "
				+vo.getDname()+" "
				+vo.getLoc());
	
	}

}
