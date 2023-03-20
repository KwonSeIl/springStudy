package com.sist.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.food.dao.*;
import com.sist.food.vo.*;
@Service
public class FoodServiceImpl implements FoodService {
	@Autowired
	private FoodMapper mapper;
	
	@Override
	public List<CategoryVO> categoryListData() {
		// TODO Auto-generated method stub
		return mapper.categoryListData();
	}

}
