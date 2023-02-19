package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

/*
 * 	BOOK_NO             NUMBER        
	NO         NOT NULL NUMBER        
	CONDITION           VARCHAR2(100) 
	PRICE               NUMBER        
	REGDATE             DATE          
	STATE               NUMBER        
	IMG                 VARCHAR2(1000) 
	PUBLISHER           VARCHAR2(500) 
	AUTHOR              VARCHAR2(1000) 
	DISCOUNT            NUMBER        
	PUB_DATE            DATE          
	DESCRIPTION          CLOB          
	TYPE                VARCHAR2(100) 
	TITLE               VARCHAR2(1000) 
	PERCENT             VARCHAR2(30) 
 */
@Getter
@Setter
public class BookVO {
   private int no,price;
   private String condition,publisher,author,title,type;
}
