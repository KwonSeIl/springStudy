package com.sist.vo;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EmpVO {
   private int empno,sal,deptno;
   private String ename,job;
   private Date regdate;
   private String dname,loc;
   private int grade;
}