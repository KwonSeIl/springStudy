package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface EmpMapper {
	@Results({
		@Result(property = "dvo.dname",column = "dname"),
		@Result(property = "dvo.loc",column = "loc")
	})
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,dname,loc "
			+"FROM emp,dept "
			+"WHERE emp.deptno=dept.deptno "
			+"ORDER BY sal DESC")
	public List<EmpVO> empListData();
}
