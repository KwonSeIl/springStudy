package com.sist.vo;

import lombok.Getter;
import lombok.Setter;
import java.util.*;
/*
 * RNO    NOT NULL NUMBER      
	CATE_NO          NUMBER      
	NO              NUMBER      
	ID              VARCHAR2(20) 
	NAME   NOT NULL VARCHAR2(34) 
	MSG    NOT NULL CLOB        
	REGDATE          DATE
 */
@Getter
@Setter
public class ReplyVO {
	private int rno,type,no;
	private String id,name,msg,dbday;
	private Date regdate;
}
