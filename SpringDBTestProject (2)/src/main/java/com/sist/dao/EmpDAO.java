package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository //DAO라고 Spring에게 알려줌(자동으로 메모리 할당)
public class EmpDAO {
	// Mapper => 인터페이스: 구현된 클래스를 받는다
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empListData()
	{
		return mapper.empListData();
	}
}
