package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sist.dao.EmpDAO;

/*
 * 	<bean id="dao" class="com.sist.dao.EmpDAO"
	  c:driver="oracle.jdbc.driver.OracleDriver"
	  p:url="jdbc:oracle:thin:@localhost:1521:XE"
	  p:user="hr"
	  p:pwd="happy"
	/>
 */
// XML 대신 사용하는 환결설정
@Configuration
public class EmpConfig {
	@Bean("dao")
	public EmpDAO empDao()
	{
		EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");
		dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dao.setUser("hr");
		dao.setPwd("happy");
		return dao;
	}
}
