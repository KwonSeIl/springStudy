package com.sist.mapper;
import java.util.*;

import com.sist.dao.EmpVO;
/*
 * <trim>
 * <foreach>
 * <if>
 * <choose>
 * <when>
 */
public interface EmpMapper {
	/*
	 * 	<select id="empNameListData" resultType="string">
	 * 				---------------	 ------------------
	 * 					Metod명			리턴형
		  SELECT DISTINCT ename FROM emp
		</select>
		@Select("SELECT DISTINCT ename FROM emp")
	 */
	public List<String> empNameListData();
	/*
	 *  <select id="empInfoData" resultType="EmpVO" parameterType="hashmap">
     	SELECT * FROM emp
	 */
	public List<EmpVO> empInfoData(Map map);
	/*
	 * 	@Select({"<script>"
	 * 		+"<trim prefix="WHERE ename IN(\" suffix=\")\" suffixOverrides=\")\" prefixOverrides=\"(\">"
	 * 		+"<foreach collection=\"names\" item=\"name\" open=\"(\" close=\")\" separator=\",\">"
	 * 		+"#{name}"
	 * 		+"</foreach>"
	 * 		+"</trim>"
	 * 		+"</script>"
	 */
	

}
