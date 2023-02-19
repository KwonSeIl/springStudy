package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
//private String title,addr,price;
public interface LocationMapper {
	@Select("SELECT title,addr,price FROM jejuLocation")
	public List<JejuLocationVO> locationListData();
}
