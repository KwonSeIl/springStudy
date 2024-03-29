package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.MusicVO;
public interface MusicMapper {
  @Select("SELECT no,title,singer,poster,album "
		 +"FROM melon_cjw ORDER BY no ASC")
  public List<MusicVO> musicAllData();
  
  @Select("SELECT * FROM melon_cjw "
		 +"WHERE no=#{no}")
  public MusicVO musicDetailData(int no);
}