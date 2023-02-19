package com.sist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration //스프링 셋팅 파일 (app.xml 대신 사용)
// 어노테이션 메모리 할당 요청
// <context:component-scan base-package="com.sist.*"/>
@ComponentScan(basePackages = {"com.sist.*"})
// interface 구현
//<mybatis-spring:scan base-package="com.sist.mapper" factory-ref="ssf"/>
@MapperScan(basePackages = {"com.sist.mapper"})
// <util:properties id="db" location="classpath:db.properties"/>
@PropertySource("classpath:/db.properties")
public class BookConfig {
	@Value("${db.driver}")
	private String driver;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	/*
	 * 	<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
	      p:driverClassName="#{db['driver']}"
	      p:url="#{db['url']}"
	      p:username="#{db['username']}"
	      p:password="#{db['password']}"
	    />
	 */
	
	@Bean("ds")
	public BasicDataSource basicDataSource()
	{
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}
	/*
	 * 	<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
	      p:dataSource-ref="ds"
	    />
	 */
	@Bean("ssf")
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
		ssf.setDataSource(basicDataSource());
		return ssf.getObject();
	}
}
