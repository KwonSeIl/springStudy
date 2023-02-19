package com.sist.myapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/*
 * 	어노테이션
 * 	-------
 * 	  = 메모리 할당(객체생성)
 * 	  	@Component
 * 			|
 * 		------------------------------------------------------------
 * 		|			|				|			|					|
 * 	@Repository	 @Service		@Controller		@RestController		@ControllerAdvice
 * 	=================================> 기능별로 구분
 *  @Repository: DAO 클래스를 알려준다
 *  @Service : BI (DAO여러개를 묶어서 사용)
 *  -------------------------------------------------------------
 *  @Controller : Model(화면 전환)
 *  @RestController : Model(데이터 전송. 화면전환X)
 *   ---- 다른 프로그램과 연동 (Front: Ajax, Vue, React) => JSON
 *  @ControllerAdvice: 공통 예외처리
 *  -------------------------------------------------------------- 여기까지는 웹과 관련
 *    = 주입
 *    @Qualifier : 특정 객체를 지정
 *    @Inject : 주입
 *    @Autowired : 자동 주입 => 스프링에 의해서 객체를 찾아서 자동으로 메모리 주소값을 넘겨준다(객체주소)
 *      private A a;
 *      public void setA(A a)
 *      {
 *      }
 *      ------------------------- p:a-ref(수동 방법)
 *      @Autowired
 *      private A a; => 자동 주입
 *      -------------------------- 메모리 할당이 아니라 주입(DI)
 *      @PostConstructor : init-method
 *      @PreDestory : destory-method
 *    = 공통모듈
 *      @Aspect
 *      @After
 *      @Around
 *      @After-Returning
 *      @After-Throwing
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.manager.*;
@Component //MainClass는 스프링이 관리하는 클래스가 됨 (스프링에서 관리 요청) => 객체 생성 -> DI -> 객체 소멸까지 담당 => 메모리 할당 (DL => 자동으로 id가 생성 => mainClass)
//@Component("mc")
public class MainClass {
	@Autowired
	private MovieManager mm; // 생성된 주소값을 스프링으로부터 받아온다
	// 지역변수를 제어할 수 없다
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=
				new ClassPathXmlApplicationContext("app.xml");
		/*MainClass mc=(MainClass)app.getBean("mainClass");
		while(true)
		{
			Scanner scan=new Scanner(System.in);
			System.out.println("================= 메뉴 ================");
			System.out.println("1.일일 박스오피스");
			System.out.println("2.실시간 예매율");
			System.out.println("3.좌석점유율순위");
			System.out.println("4.온라인상영관 일일");
			System.out.println("5.종료");
			System.out.println("======================================");
			System.out.print("메뉴 선택:");
			int no=scan.nextInt();
			if(no==5) break;
			List<MovieVO> list=mc.mm.movieListData(no);
			for(MovieVO vo:list)
			{
				System.out.println(vo.getRank()+" "+vo.getTitle()+" "+vo.getGenre()+" "+vo.getDirector());
			}
		}*/
		NewsManager n=(NewsManager)app.getBean("newsManager");
		Scanner scan=new Scanner(System.in);
		System.out.print("검색어 입력:");
		String fd=scan.next();
		List<NewsVO> list=n.newsListData(fd);
		for(NewsVO vo:list)
		{
			System.out.println(vo.getTitle());
			System.out.println(vo.getDescription());
			System.out.println(vo.getPubDate());
			System.out.println("===============================================================================================");
		}
		
	}

}
