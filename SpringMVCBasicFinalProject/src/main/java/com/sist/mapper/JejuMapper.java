package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.JejuFoodVO;
import com.sist.vo.JejuLocationVO;
public interface JejuMapper {
	// 목록(여행지)
	@Select("SELECT no,title,poster,price,num "
			+"FROM (SELECT no,title,poster,price,rownum as num "
			+"FROM (SELECT no,title,poster,price "
			+"FROM jejuLocation ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<JejuLocationVO> jejuLocationListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM jejuLocation")
	public int jejuTotalPage();
	
	@Select("SELECT * FROM jejuLocation WHERE no=#{no}")
	public JejuLocationVO jejuDetailData(int no);
	
	@Select("SELECT no,title,poster,score,rownum "
			+"FROM jejuFood "
			+"WHERE rownum<=4 AND addr2 LIKE '%'||#{addr}||'%'")
	public List<JejuFoodVO> jejuFoodData(Map map);
}
