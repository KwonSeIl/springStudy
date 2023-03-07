package com.sist.spring3;
//스프링에서 사용 => 클래스 관리(팩토리 패턴 , 싱글턴) =>장점) 메모리 절약이 가능하다 , 연결이 단순하다 ,표준화(모든 개발자가 동일한 형식으로 사용)
//모든 언어에 호환이 가능 , open source
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
      		new ApplicationContext();
		AModel a=(AModel)app.getBean("amodel");
		a.display();
		System.out.println("a="+a);
		AModel a1=(AModel)app.getBean("amodel");
		a1.display();
		System.out.println("a1="+a1);
      
		BModel b=(BModel)app.getBean("bmodel");
		b.display();
		CModel c=(CModel)app.getBean("cmodel");
		c.display();
	}

}