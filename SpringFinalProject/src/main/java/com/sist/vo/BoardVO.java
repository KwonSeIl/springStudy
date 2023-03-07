package com.sist.vo;
import java.util.*;
/*
 * NO     NOT NULL NUMBER        
NAME   NOT NULL VARCHAR2(34)  
SUBJECT NOT NULL VARCHAR2(2000) 
CONTENT NOT NULL CLOB          
PWD    NOT NULL VARCHAR2(10)  
REGDATE          DATE          
HIT             NUMBER 
 */
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
