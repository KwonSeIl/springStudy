package com.sist.dao;

import lombok.Getter;
import lombok.Setter;

/*
 * 	NO     NOT NULL NUMBER        
	TITLE  NOT NULL VARCHAR2(200) 
	POSTER NOT NULL VARCHAR2(500) 
	MSG    NOT NULL VARCHAR2(4000) 
	ADDRESS NOT NULL VARCHAR2(300) 
	HIT             NUMBER 
 */
//lombok 깔고 게터세터 지우기
@Getter
@Setter
public class SeoulLocationVO {
	private int no,hit;
	private String title,poster,msg,address;
	
	
}
