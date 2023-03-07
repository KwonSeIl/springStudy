package com.sist.sawon;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import lombok.Setter;

/*
 * 	스프링 => 클래스 관리
 * 			---- 클래스 하나를 컴포넌트라고 한다 => 컴포넌트 여러개를 모아서 관리 : 컨테이너
 * 스프링 => 컨테이너(라이브러리 이미 제작)
 * 			BeanFactory
 * 				|
 * 			ApplicationContext => 클래스를 Map에 저장(id,new클래스()) => DL(클라이언트가 요청한 클래스를 찾아주는 역할)
 * 																	------------------------------- 사용하는 메소드: getBean(id)
 * 									=> 장점) 클래스와 클래스간의 연결 관계 없이 독립적으로 동작을 한다 (POJO) => 결합성이 낮은 프로그램 제작 => 유지보수에 중심
 * 				|
 * 		--------------------------------------------------------------------------------
 * 		|								|												|
 * 	GenericApplicationContext		AnnotationConfigApplicationContext		WebApplicationContext(MVC구조 가짐)
 * 	=> 컨테이너 종료하는 기능 존재			=> 자바로 스프링 설정했을 때 사용				
 * 
 * 	컨테이너: 클래스 관리 (객체 생성~객체 소멸까지 담당)
 * 		  --------
 * 			1) 객체 생성(메모리 할당)
 * 				= new 이용
 * 				***= 리플렉션 => 클래스의 이름으로 메모리 할당 => Class.forName()
 * 				= id, 객체 주소 저장 -> 필요할 때마다 가져다 씀 <bean id="" class="">
 * 				= 클래스에서 제외(~VO:데이터형이기 때문)
 * 			2) 필요한 데이터를 설정: DataBase 연결, 채팅 서버 연결 ...
 * 								=> 필요한 멤버변수의 값을 설정해야 한다
 * 									-------------------------
 * 									= setXxx()
 * 									= 생성자
 * 									------------------------- DI
 * 			3) 메소드 호출 가능 : 객체 생성: init-method
 * 							 객체 소멸: destory-method
 * ----------------------------------------------------------------------------------
 * 			
 */

/*
 * 	<bean id="sa" class="com.sist.sawon.Sawon">
 *  -------------------------------------------
 *  추출할 수 있는것: String id="sa"
 *  			 String cls="com.sist.sawon.Sawon"
 *  	
 *  			 Class clsName=Class.forName(cls); 클래스 정보를 읽어온다
 *  			 Object obj=clsName.newInstance(); 메모리 할당
 *  			 map.put(id,obj) => 저장	============================== 여기까지 스프링 내부에서 작업을 해준다 (싱글턴): 요청시마다 메모리를 한개만 사용
 *  			 ----------------------									메모리 누수현상 방지
 *  			 app.getBean("sa") => obj를 받아서 사용할 수 있게 된다 ===> 프로그램에 필요한 객체를 얻어온다
 *  
 *  		스프링에서 XML을 읽는 경우, Annotation
 *  				---			 ----------
 *  		setter DI, 생성자 DI 가능		값 	주입이 어렵다
 *  		-----------------------------------------
 *  		생명주기
 *  			= 생성자 호출 (메모리 할당)
 *  				= 생성자 DI 
 *  			= setter DI
 *  			= 프로그래머 활용
 *  			= 객체 소멸
 */
public class Sawon implements InitializingBean,DisposableBean,BeanNameAware,BeanFactoryAware{
	private String name;
	private String sex;
	
	public Sawon()
	{
		System.out.println("1. 스프링에서 메모리 할당: Sawon 객체 생성... Class.forName()");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("2. setName()을 호출: name값을 채워준다(주입=DI) => setName("+name+")");
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
		System.out.println("3. setSex()을 호출: sex값을 채워준다(주입=DI) => setSex("+sex+")");
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("setBeanFactory() Call..");
		
	}

	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.out.println("setBeanName() Call..:"+name); //app.getBean("sa")
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("객체 소멸.. destory()...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterPropertiesSet(): SetterDI 완료: 데이터를 주입 완료...");
		
	}
	
	public void print()
	{
		System.out.println("프로그래머 활용");
		System.out.println("name:"+name);
		System.out.println("sex:"+sex);
	}

}
