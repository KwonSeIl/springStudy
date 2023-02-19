package com.sist.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//XML,Container 호출 
/*
*    1) 스프링에 등록할 클래스 제작 => Model,DAO,Manager... (기능을 가지고 있는 클래스)
*    2) 제외 : 사용자 정의 데이터형 => VO , DTO , Bean
*    -------------------------------------------------------------------
*    3) XML을 이용해서 등록 
*    4) =================== 스프링 라이브러라에서 처리 (ApplicationContext)
*    5) 등록된 클래스를 ApplicationContext에서 얻어와서 구현 ========================> 조립
*    6) ApplicationContext에서 등록된 클래스를 소멸
*    
*       스프링 라이브러리(자바소스 코딩이 불가능)  <========> 사용자(프로그래머와 통신)
*                                        XML,Annotation => 메타데이터 
*       ==============================
*       역할 : 객체 생성 / 객체 소멸 / 객체 찾기 ==> getBean() ==> DL
*    7) 문제 : 객체를 생성할때 필요한 데이터가 있는면 => DI
*    -------------------------------------------- XML은 메뉴얼 , Annotation는 검색 
*                                                 ---------------------------
*    => MVC (라이브러리) ,  클래스 관리자 (낮은 결합성) => 수정시에 다른 클래스에 영향이 없는 관리 (유지보수)
*    =================================================================================
*    
*/
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
        		new ClassPathXmlApplicationContext("app.xml");
        // ClassPath => src/main/java
        AModel a=(AModel)app.getBean("a");
        a.display();
        System.out.println("a="+a);
        AModel b=(AModel)app.getBean("a");
        b.display();
        System.out.println("b="+b);
	}
}

