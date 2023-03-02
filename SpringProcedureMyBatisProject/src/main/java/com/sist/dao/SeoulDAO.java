package com.sist.dao;
import com.sist.mapper.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	
	public List<SeoulVO> seoulListData(Map map)
	{
		mapper.seoulListData(map);
		return (List<SeoulVO>)map.get("pResult");
	}
	
	public SeoulVO seoulDetailData(Map map)
	{
		mapper.seoulDetailData(map);
		return (SeoulVO)map.get("pResult");
	}
	
	public Integer seoulTotalPage(Map map)
	{
		return mapper.seoulTotalPage();
	}
}
