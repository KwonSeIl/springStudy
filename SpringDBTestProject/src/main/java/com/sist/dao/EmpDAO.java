package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository //DAO��� Spring���� �˷���(�ڵ����� �޸� �Ҵ�)
public class EmpDAO {
	// Mapper => �������̽�: ������ Ŭ������ �޴´�
	@Autowired
	private EmpMapper mapper;
	
	public List<EmpVO> empListData()
	{
		return mapper.empListData();
	}
}