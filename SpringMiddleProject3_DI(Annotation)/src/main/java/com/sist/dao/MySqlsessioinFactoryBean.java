package com.sist.dao;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ssf")
//Target(value={TYPE}) => 클래스 위 위치
public class MySqlsessioinFactoryBean extends SqlSessionFactoryBean {

	@Autowired
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
	

}
