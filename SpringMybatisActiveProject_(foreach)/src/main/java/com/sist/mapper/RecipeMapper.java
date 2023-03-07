package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Select;
/*
 *    SELECT * FROM emp
 *    WHERE ename='a' OR ename='b' OR ename='c';
 *    
 *    SELECT * FROM emp
 *    WHERE ename IN('a','b','c')
 *    
 *    SELECT * FROM emp
 *    WHERE REGEXP_LIKE(ename,'a|b|c')
 */

import com.sist.dao.RecipeVO;
public interface RecipeMapper {
  @Select("SELECT title,chef,poster,rownum "
		 +"FROM recipe "
		 +"WHERE rownum<=40 AND REGEXP_LIKE(title,#{menu})")
  public List<RecipeVO> recipeFindData(String menu);
}