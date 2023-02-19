package com.sist.dao;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class GoodsDAO {
	@Autowired
	   private GoodsMapper mapper;
	   public List<GoodsVO> seoulListData(Map map)
	   {
		   return mapper.goodsListData(map);
	   }
}