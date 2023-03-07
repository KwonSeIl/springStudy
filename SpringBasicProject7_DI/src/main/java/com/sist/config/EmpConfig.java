package com.sist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sist.dao.EmpDAO;
import com.sist.myapp.MainClass;

@Configuration
public class EmpConfig {
	/*
	 * <bean id="dao" class="com.sist.dao.EmpDAO"
	  c:driver="oracle.jdbc.driver.OracleDriver"
	  p:url="jdbc:oracle:thin:@localhost:1521:XE"
	  p:user="hr"
	  p:password="happy"
	/>
	 */
	@Bean("dao") // id <bean id="dao">
	public EmpDAO empDAO()
	{
		EmpDAO dao=new EmpDAO("oracle.jdbc.driver.OracleDriver");
		dao.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dao.setUser("hr");
		dao.setPassword("happy");
		
		return dao;
	}
	/*
	 * <bean id="mc" class="com.sist.myapp.MainClass"
	  p:dao-ref="dao"
	/>
	 */
	@Bean("mc") // id <bean id="mc">
	public MainClass mainClass()
	{
		MainClass mc=new MainClass();
		mc.setDao(empDAO());
		return mc;
	}
}
