package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.SeoulMapper;
import com.sist.vo.FoodVO;
import com.sist.vo.JejuFoodVO;
import com.sist.vo.SeoulLocationVO;
@Repository
public class SeoulDAO {
	@Autowired
	private SeoulMapper mapper;
	/*@Select("SELECT no,title,poster,msg,address,hit,num "
			+"FROM (SELECT no,title,poster,msg,address,rownum as num "
			+"FROM (SELECT no,title,poster,msg,address "
			+"FROM seoul_location ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")*/
	public List<SeoulLocationVO> seoulLocationListData(Map map)
	{
		return mapper.seoulLocationListData(map);
	}
	
	/*@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_location")*/
	  public int seoulTotalPage()
	  {
		  return mapper.seoulTotalPage();
	  }
	
	/*@Select("SELECT * FROM seoul_location WHERE no=#{no}")*/
	  public SeoulLocationVO seoulDetailData(int no)
	  {
		  return mapper.seoulDetailData(no);
	  }
	  
	/*@Select("SELECT fno,name,poster,score,rownum "
		 		+"FROM food_location "
				+"WHERE rownum<=4 AND addr2 LIKE '%'||#{addr}||'%'")*/
	  public List<FoodVO> seoulFoodData(Map map)
	  {
		  return mapper.seoulFoodData(map);
	  }
	  public List<SeoulLocationVO> SeoulFindData(Map map)
	  {
		  return mapper.seoulFindData(map);
	  }
}
