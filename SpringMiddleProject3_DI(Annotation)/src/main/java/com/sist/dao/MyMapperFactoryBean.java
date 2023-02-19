package com.sist.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("mapper")
public class MyMapperFactoryBean extends MapperFactoryBean{

   
   /*
    * <bean id="mapper" class="org.mybatis.spring.mapper.MapperFactoryBean"
        p:sqlSessionFactory-ref="ssf"
        p:mapperInterface="com.sist.mapper.SeoulMapper"
      />
    */
   @Autowired
   public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
      // TODO Auto-generated method stub
      super.setSqlSessionFactory(sqlSessionFactory);
   }
   public MyMapperFactoryBean()
   {
      setMapperInterface(com.sist.mapper.SeoulMapper.class);
   }
}