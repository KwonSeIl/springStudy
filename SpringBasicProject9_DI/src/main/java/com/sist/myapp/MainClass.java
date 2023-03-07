package com.sist.myapp;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;
public class MainClass {
	private FoodDAO dao;
	
	public void setDao(FoodDAO dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String[] xml={"application-context.xml","application-datasource.xml"};
         ApplicationContext app=
        		 new ClassPathXmlApplicationContext(xml);
         MainClass mc=(MainClass)app.getBean("mc");
         List<FoodVO> list=mc.dao.foodListData();
         for(FoodVO vo:list)
         {
        	 System.out.println("맛집번호:"+vo.getFno());
        	 System.out.println("맛집명:"+vo.getName());
        	 System.out.println("주소:"+vo.getAddress());
        	 System.out.println("===============================================================");
         }
         Scanner scan=new Scanner(System.in);
         System.out.print("번호 입력:");
         int fno=scan.nextInt();
         FoodVO vo=mc.dao.foodDetailData(fno);
         System.out.println("맛집명:"+vo.getName());
    	 System.out.println("주소:"+vo.getAddress());
    	 System.out.println("영업시간:"+vo.getTime());
    	 System.out.println("주차:"+vo.getParking());
    	 System.out.println("음식종류:"+vo.getType());
    	 System.out.println("메뉴:"+vo.getMenu());
         
	}

}
