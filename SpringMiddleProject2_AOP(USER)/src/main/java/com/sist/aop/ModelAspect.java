package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.sist.model.EmpDAO;
import com.sist.model.EmpVO;

import java.util.*;
import lombok.Setter;

public class ModelAspect {
	@Setter
	private EmpDAO dao;
	public void before() {
		System.out.println("before Call:시작전");
		dao.getConnection();
	}
	public void after()
	{
		System.out.println("after Call:finally");
		dao.disConnection();
	}
	/*
	 * 	try
	 * 	{
	 * 		=================== around 시작
	 * 		소스 코딩	
	 * 		=================== around 끝
	 * 	}catch(Exception ex){
	 * 		afterThrowing : 오류 발생 시 처리
	 *  }
	 *  finally
	 *  {
	 * 		after
	 *  }
	 * 
	 *  return afterReturning : 정상 수행 시 처리
	 * 
	 *  뉴스 읽기 ==> Before
	 *  뉴스를 List ==> 핵심
	 *  해당 화면 전송 ==> afterReturning => request로 브라우저 전송
	 *  
	 *  반복 수행이 많은 경우에 주로 처리
	 *  -------------------------
	 *  머신 러닝
	 *  ==========
	 *  데이터 수집 ==> Before
	 *  ===================
	 *  데이터 전처리
	 *  데이터 분석
	 *  데이터 통계
	 * 	=================== 핵심
	 *  데이터 예측 ==> finally(After)
	 *  ===================
	 */
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("around Call:try 위 ~ try 종료전 호출");
		Object obj=null;
		// 수행시간 계산
		long start=0;
		long end=0;
		try
		{
			start=System.currentTimeMillis();
			System.out.println("Client 요청 메소드:"+jp.getSignature().getName());
			obj=jp.proceed();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			end=System.currentTimeMillis();
			System.out.println("수행시간:"+(end-start));
		}
		return obj;
		
	}
	public void afterReturning(Object obj) throws Throwable
	{
		//결과값 받아서 출력 ==> web에서 return 값을 먼저 처리 => 자동로그인,id저장,권한
		System.out.println("afterReturning Call");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEname()+" "+vo.getJob());
		}
	}
	public void afterThrowing(Throwable ex) throws Throwable
	{
		System.out.println("afterThrowing Call");
		//catch수행
		System.out.println(ex.getMessage());
	}
	
}
