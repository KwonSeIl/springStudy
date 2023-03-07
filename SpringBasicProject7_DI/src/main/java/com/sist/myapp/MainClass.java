package com.sist.myapp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.EmpConfig;
import com.sist.dao.*;
// 클래스와 클래스간의 상호 연결 => XML 
public class MainClass {
	// 주입 => 스프링에서 생성 후에 주입
	private EmpDAO dao;
	
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");*/
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(EmpConfig.class);
		MainClass mc=(MainClass)app.getBean("mc");
		//MainClass mc=new MainClass(); //ApplicationContext와 MainClass mc=(MianClass) 부분 없이 혼자 사용했을 시 : 생성함과 동시에 값을 주입받지 못해 널포인트 에러 발생
		List<EmpVO> list=mc.dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}

}
