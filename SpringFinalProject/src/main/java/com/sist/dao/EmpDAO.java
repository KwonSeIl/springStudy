package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class EmpDAO {
   @Autowired
   private EmpMapper mapper;// 스프링에서만 구현 ==> new Mapper() => 메모리 할당 
   
   /*@Results({
	   @Result(property = "dvo.dname",column = "dname"),
	   @Result(property = "dvo.loc",column = "loc")
   })
   @Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM_DD') as dbday,sal,dname,loc "
		  +"FROM emp,dept "
		  +"WHERE emp.deptno=dept.deptno "
		  +"ORDER BY sal DESC")*/
   public List<EmpVO> empListData()
   {
	   return mapper.empListData();
   }
}
