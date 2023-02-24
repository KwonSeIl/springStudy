package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.*;
/*
 *    1. 목록 => 페이징 (총페이지) => 인라인뷰 
 *    2. 데이터 추가 => 파일업로드 => List
 *    3. 데이터 수정 
 *    4. 데이터 삭제 => 파일 삭제 
 *    5. 데이터 상세보기 => 다운로드 => 리턴형 void (Controller : String ,void)
 *    6. 데이터 검색 => MyBatis (동적쿼리) 
 *                   trim , foreach , choose , when ....
 */
public interface DataBoardMapper {
   //1.목록 
   @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,filecount,num "
		  +"FROM (SELECT no,subject,name,regdate,hit,filecount,rownum as num "
		  +"FROM (SELECT /*+ INDEX_DESC(spring_databoard sd_no_pk)*/no,subject,name,regdate,hit,filecount "
		  +"FROM spring_databoard)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<DataBoardVO> databoardListData(Map map);
   
   @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
   public int databoardTotalPage();
   
   //2.추가 
   //Sequence
   @SelectKey(keyProperty = "no",resultType = int.class, before = true,
		      statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
   
   @Insert("INSERT INTO spring_databoard(no,name,subject,content,pwd,filename,filesize,filecount) "
		  +"VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")
   public void databoardInsert(DataBoardVO vo);
   
   //3. 상세보기 
   @Update("UPDATE spring_databoard SET "
		  +"hit=hit+1 "
		  +"WHERE no=#{no}")
   public void hitIncrement(int no);
   
   @Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,"
		  +"filename,filesize,filecount "
		  +"FROM spring_databoard "
		  +"WHERE no=#{no}")
   public DataBoardVO databoardDetailData(int no);
   
   //4. 삭제
   @Select("SELECT pwd FROM spring_databoard WHERE no=#{no}")
   public String databoardGetPassword(int no);
   
   @Select("SELECT filename,filesize,filecount FROM spring_databoard WHERE no=#{no}")
   public DataBoardVO databoardFileInfoData(int no);
   
   @Delete("DELETE FROM spring_databoard WHERE no=#{no}")
   public void databoardDelete(int no);
   
   @Update("UPDATE spring_databoard SET "
		  +"name=#{name},subject=#{subject},content=#{content} "
		  +"WHERE no=#{no}")
   public void databoardUpdate(DataBoardVO vo);
   
   //<select id="databoardFindData" resultType="DataBoardVO" parameterType="hashmap">
   public List<DataBoardVO> databoardFindData(Map map);
   
   @Select({
	      "<script>"
		  +"SELECT COUNT(*) FROM spring_databoard "
		  +"WHERE "
		  +"<trim prefixOverrides=\"OR\">"
		  +"<foreach collection=\"fsArr\" item=\"fd\">"
          +"<trim prefix=\"OR\">"
          +"<choose>"
          +"<when test=\"fd=='N'.toString()\">"
          +"name LIKE '%'||#{ss}||'%'"
          +"</when>"
          +"<when test=\"fd=='S'.toString()\">"
          +"subject LIKE '%'||#{ss}||'%'"
          +"</when>"
          +"<when test=\"fd=='C'.toString()\">"
          +"content LIKE '%'||#{ss}||'%'"
          +"</when>"
          +"</choose>"
          +"</trim>"
          +"</foreach>"
          +"</trim>"
          +"</script>"})
   public int FindCount(Map map);
          
}