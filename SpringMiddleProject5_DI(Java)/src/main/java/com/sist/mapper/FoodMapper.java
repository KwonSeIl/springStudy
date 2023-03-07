package com.sist.mapper;
import com.sist.vo.*;
import java.util.*;

import org.apache.ibatis.annotations.Select;
public interface FoodMapper {
	@Select("SELECT fno,name,tel,address,type FROM project_food "
			+"WHERE cno=#{cno}")
	public List<FoodVO> foodListData(int cno);
}
