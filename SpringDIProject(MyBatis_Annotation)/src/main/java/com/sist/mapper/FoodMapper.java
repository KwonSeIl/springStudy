package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT fno,name,type,rownum "
			+"FROM (SELECT fno,name,type FROM food_location ORDER BY fno ASC) "
			+"WHERE rownum<=30 ")
	public List<FoodVO> foodListData();
	//	   ------------        		--
	//		resultType				parameterType
}
