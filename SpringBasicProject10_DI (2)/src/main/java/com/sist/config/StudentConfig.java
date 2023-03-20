package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sist.dao.StudentDAO;
import com.sist.myapp.MainClass2;

@Configuration
public class StudentConfig {
	/*
	 * 	<bean id="dao" class="com.sist.dao.StudentDAO"
		  p:sqlSessionFactory-ref="ssf"
		/>
	 */
	/*
	 * 
	 */
	@Bean("mc")
	public MainClass2 MainClass22() throws Exception
	{
		MainClass2 mc=new MainClass2();
		mc.setDao(studentDAO());
		return mc;
	}
	@Bean("ds")
	public BasicDataSource basicDataSource()
	{
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(basicDataSource());
		Resource res=new ClassPathResource("Config.xml");
		ssf.setConfigLocation(res);
		return ssf.getObject();
	}
	@Bean("dao")
	public StudentDAO studentDAO() throws Exception
	{
		StudentDAO dao=new StudentDAO();
		dao.setSqlSessionFactory(sqlSessionFactory());
		return dao;
	}
}
