package com.sist.myapp;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sist.dao.*;

@Component
public class MainClass {
	@Autowired
	private StudentDAO dao;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		List<StudentVO> list=mc.dao.studentListData();
		for(StudentVO vo:list)
		{
			System.out.println(vo.getHakbun()+" "
					+vo.getName()+" "
					+vo.getKor()+" "
					+vo.getEng()+" "
					+vo.getMath());
		}
		
		StudentVO vo1=new StudentVO();
		vo1.setHakbun(8);
		vo1.setName("강감찬2");
		vo1.setKor(80);
		vo1.setEng(80);
		vo1.setMath(80);
		
		StudentVO vo2=new StudentVO();
		vo2.setHakbun(8);
		vo2.setName("을지문덕");
		vo2.setKor(80);
		vo2.setEng(80);
		vo2.setMath(80);
		
		mc.dao.studentInsert(vo1, vo2);

	}

}
