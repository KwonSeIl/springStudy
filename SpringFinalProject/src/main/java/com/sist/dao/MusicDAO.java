package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class MusicDAO {
   @Autowired
   private MusicMapper mapper;
   
   /*@Select("SELECT no,title,singer,poster,ablum "
			 +"FROM music_cjw ORDER BY no ASC")*/
	public List<MusicVO> musicAllData(){
		return mapper.musicAllData();
	}
	
	 /*@Select("SELECT * FROM melon_cjw "
			 +"WHERE no=#{no}")*/
	  public MusicVO musicDetailData(int no)
	  {
		  return mapper.musicDetailData(no);
	  }
}