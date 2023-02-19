package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.EmpVO;
public interface EmpMapper {
	@Select("SELECT empno,ename,job,sal,hiredate FROM emp")
	public List<EmpVO> empListData();
}
