package com.sist.common;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 *  스프링: 객체 생성 ~ 소멸 (필요시마다 호출이 가능 => 기본 싱글턴) => 기본 목적: 핵심만 코딩 -> 소스 간략화
 *  1.메모리 할당 (제외: VO - 사용자 정의 데이터형, Mapper - 인터페이스) ==> 메모리 할당은 하지만 기능별로 분리해서 저장
 *    @Component => OpenAPI (일반 클래스)
 *    @Repository => DAO (저장소: 데이터베이스 관련) ==> 테이블 1개 연결
 *    @Service => DAO가 여러개인 경우 (Join, SubQuery)
 *    ---------------------------------------------- 1) Service VS DAO의 차이점
 *    @Controller : 브라우저와 연결(DispatcherServlet이 위임) : 파일명 (화면 이동)
 *    @RestController : 다른 프로그램과 연결할 때 사용 (자바스크립와 연결, Kotlin과 연결 등..) => JSON / JSONP
 *    @ControllerAdvice : Controller에서 공통 예외 처리
 *    @RestControllerAdvice : RestController에서 공통 예외 처리
 *    @Configuration : 자바로 환경설정(스프링 세팅)
 *  2. 주입
 *    일반 문자열 주입 => @Value
 *    클래스 객체 주소 주입 => @Autowired, @Resource(가장 많이 사용함), @Qualifier
 *    								 --------- 특정 객체 지정
 *    				  => @Autowired + @Qualifier = @Resource
 *    				  => @Qualifier("id명"), @Resource(name="id명") => JDK1.8 (JDK1.8이 호환성이 가장 좋음)
 *  => 기술면접 : DI / AOP / Service vs DAO / Transaction
 *  => 자바 : 캡슐화 vs 은닉화 / 상속 vs 포함 / 오버로딩 vs 오버라이딩 / 추상클래스 vs 인터페이스 / 객체란? / 예외처리의 종류(예외 복구 / 예외 회피)
 *  		컬렉션의 종류 (List, Map, Set) => 코테:set,이중for / 쓰레드 부분
 *  => 오라클 : JOIN / SUBQuery / View (sql문장 길거나 반복수행할 때 사용 => 보안이 뛰어남)
 *  		  Index (어느 때 사용, index가 무엇인지)
 *  		  Procedure vs Function vs Trigger
 *  		  데이터형 (BLOB, BFILE) => 동영상이나 이미지 저장할 때 사용. blob=>binarry 형태로 저장 befile => file형태로 저장
 *  => HTML / CSS : 시멘텍 / GET / POST
 *  => JSP : GET / POST / Cookie / Session 구분 / MVC가 무엇인지
 *  => Front-End : VueJS vs ReactJS의 장단점의 차이점
 *     자바스크립트 : var vs let vs const / Arrow함수가 무엇인지, 클로저 => Ajax vs axios 차이점
 *  => 면접관 : 5명 => 3문제씩 총 15문제 질문
 *  => 프로젝트에 대해 물어봄 => 인성 면접 (자기소개서)
 */
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
/*
 *  1. RuntimeException : NumberFormatException / NullPointerException / ClassCastException
 *  2. SQLException
 *  3. IOException
 *  4. Exception
 */
public class CommonException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex)
	{
		System.out.println("==================== RuntimeException =====================");
		System.out.println(ex.getMessage());
		System.out.println("===========================================================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException ex)
	{
		System.out.println("==================== SQLException =========================");
		System.out.println(ex.getMessage());
		System.out.println("===========================================================");
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex)
	{
		System.out.println("==================== IOException =========================");
		System.out.println(ex.getMessage());
		System.out.println("===========================================================");
	}
	@ExceptionHandler(Exception.class)
	public void exception(Exception ex)
	{
		System.out.println("==================== Exception ============================");
		System.out.println(ex.getMessage());
		System.out.println("===========================================================");
	}
}
