package com.sist.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.MyDAO;

public class MainClass {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      /*
       * MyDAO dao=new MyDAO("oracle.jdbc.driver.OracleDriver");
       * dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE"); dao.setUser("hr");
       * dao.setPassword("happy");
       */
      ///////////////////////////////////////////스프링에 떠넘긴다
       ApplicationContext app=
                  new ClassPathXmlApplicationContext("app.xml");
       MyDAO dao=(MyDAO)app.getBean("dao");
       //연결
       dao.getConnection();

   }

}