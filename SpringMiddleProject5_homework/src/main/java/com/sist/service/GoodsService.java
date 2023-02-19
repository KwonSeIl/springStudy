package com.sist.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//DAO여러개를 통합 (BI)
//DAO와 Service차이점

import com.sist.mapper.CategoryMapper;
import com.sist.mapper.GoodsMapper;
import com.sist.vo.CategoryVO;
import com.sist.vo.GoodsVO;
@Service
public class GoodsService {
	@Autowired
	private GoodsMapper gMapper;
	@Autowired
	private CategoryMapper cMapper;
	
	public List<CategoryVO> categoryListData(){
		return cMapper.categoryListData();
	}
	
	public List<GoodsVO> goodsListData(Map map){
		return gMapper.goodsListData(map);
	}
}