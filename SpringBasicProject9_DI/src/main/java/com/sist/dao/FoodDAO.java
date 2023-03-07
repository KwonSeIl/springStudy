package com.sist.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import java.util.*;
public class FoodDAO extends SqlSessionDaoSupport {
	/*
	 * <select id="foodListData" resultType="FoodVO">
	      SELECT fno,name,address,rownum
	      FROM food_location
	      WHERE rownum&lt;=50
	    </select>
	 */
	public List<FoodVO> foodListData()
    {
    	return getSqlSession().selectList("foodListData");
    	/*
    	 *  while(rs.next())
    	 *  {
    	 *      FoodVO vo=new FoodVO();
    	 *      vo.setFno(rs.getInt("fno"))
                ...
                ...
                list.add(vo);
    	 *  }
    	 */
    }
    /*
     *   <select id="foodDetailData" resultType="FoodVO" parameterType="int">
    SELECT fno,name,address,rownum
    FROM food_location
    WHERE fno=#{fno}
  </select>
     */
    public FoodVO foodDetailData(int fno)
    {
    	return getSqlSession().selectOne("foodDetailData", fno);
    	/*
    	 *   FoodVO vo=new FoodVO();
    	 *   vo.setFno(rs.getInt("fno"))
    	 *   ..
    	 *   ..
    	 */
    }
}
