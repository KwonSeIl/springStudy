package com.sist.mapper;
import java.util.*;

import com.sist.dao.SeoulLocationVO;
public interface SeoulMapper {
	// 매개변수를 두개 이상 사용하면 오류 발생
	/*
	 * 	1. ~VO
	 *  2. Map
	 */
   public List<SeoulLocationVO> seoulListData(Map map);
   public SeoulLocationVO seoulDetailData(Map map);
   //			resultType		id			parameterType
}