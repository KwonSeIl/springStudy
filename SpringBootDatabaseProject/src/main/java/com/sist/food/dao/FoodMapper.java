package com.sist.food.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.food.vo.*;
@Repository
@Mapper
public interface FoodMapper {
	public List<CategoryVO> categoryListData();
}
