package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//메모리할당

import com.sist.mapper.SeoulMapper;
import java.util.*;
@Repository
public class SeoulDAO {
	@Autowired //자동주입
	private SeoulMapper mapper;
	public List<SeoulVO> seoulListData()
	{
		return mapper.seoulListData();
	}
}
