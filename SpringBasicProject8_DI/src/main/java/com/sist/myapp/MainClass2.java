package com.sist.myapp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.ModelConfig;
import com.sist.model.*;
public class MainClass2 {
	public static void main(String[] args) {
		/*ApplicationContext app=
				new ClassPathXmlApplicationContext("app2.xml");*/
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(ModelConfig.class);
		AModel a=(AModel)app.getBean("am");
		a.board();
		BModel b=(BModel)app.getBean("BModel"); //id 사용하지 않으면 자동으로 id 지정 => class명으로 
		b.member();
		CModel c=new CModel();
		//CModel c=(CModel)app.getBean("cModel"); => 에러발생(메모리할당 요청하지 않았기 때문)
		c.food();
	}
}
