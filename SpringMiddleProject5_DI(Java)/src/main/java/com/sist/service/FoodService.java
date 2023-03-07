package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//DAO 여러개를 통합(BI) => service
// 기술면접: 99.9% => DAO VS service의 차이점

import com.sist.mapper.CategoryMapper;
import com.sist.mapper.FoodMapper;
import com.sist.vo.CategoryVO;
import com.sist.vo.FoodVO;
@Service
public class FoodService {
	@Autowired
	private FoodMapper fMapper;  //private인데 Autowired가능 => 어떻게 값을 집어넣을까? => private 해제해서 넣는 방법 존재함
	@Autowired
	private CategoryMapper gMapper;
	
	public List<CategoryVO> categoryListData()
	{
		return gMapper.categoryListData();
	}
	
	public List<FoodVO> foodListData(int cno)
	{
		return fMapper.foodListData(cno);
	}
}
