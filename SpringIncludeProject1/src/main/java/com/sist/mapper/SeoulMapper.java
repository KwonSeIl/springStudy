package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.SeoulLocationVO;
public interface SeoulMapper {
	@Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(seoul_location sl_no_pk)*/no,title,poster "
			+"FROM seoul_location)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulLocationVO> seoulLocationListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_location")
	public int seoulTotalPage();
	
	@Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(seoul_nature sn_no_pk)*/no,title,poster "
			+"FROM seoul_nature)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulLocationVO> seoulNatureListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_nature")
	public int seoulNatureTotalPage();
	
	@Select("SELECT no,title,poster,num "
			+"FROM (SELECT no,title,poster,rownum as num "
			+"FROM (SELECT /*+ INDEX_ASC(seoul_shop ss_no_pk)*/no,title,poster "
			+"FROM seoul_shop)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<SeoulLocationVO> seoulShopListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM seoul_nature")
	public int seoulShopTotalPage();
}
