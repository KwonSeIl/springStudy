package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;

@Repository
public class DataBoardDAO {
   @Autowired
   private DataBoardMapper mapper;
   
       /*@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,filecount,num "
			  +"FROM (SELECT no,subject,name,regdate,hit,filecount,rownum as num "
			  +"FROM (SELECT /*+ INDEX_DESC(spring_databoard sd_no_pk)no,subject,name,regdate,hit,filecount "
			  +"FROM spring_databoard)) "
			  +"WHERE num BETWEEN #{start} AND #{end}")*/
	   public List<DataBoardVO> databoardListData(Map map)
	   {
		   return mapper.databoardListData(map);
	   }
	   
	   //@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_databoard")
	   public int databoardTotalPage()
	   {
		   return mapper.databoardTotalPage();
	   }
	   
	   //2.추가 
	   //Sequence
	   /*@SelectKey(keyProperty = "no",resultType = int.class, before = true,
			      statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_databoard")
	   
	   @Insert("INSERT INTO spring_databoard(no,name,subject,content,pwd,filename,filesize,filecount) "
			  +"VALUES(#{no},#{name},#{subject},#{content},#{pwd},#{filename},#{filesize},#{filecount})")*/
	   public void databoardInsert(DataBoardVO vo)
	   {
		   mapper.databoardInsert(vo);
	   }
	   
	  //3. 상세보기 
	   /*@Update("UPDATE spring_databoard SET "
			  +"hit=hit+1 "
			  +"WHERE no=#{no}")*/
	   /*@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,"
			  +"filename,filesize,filecount "
			  +"FROM spring_databoard "
			  +"WHERE no=#{no}")*/
	   public DataBoardVO databoardDetailData(int no)
	   {
		   mapper.hitIncrement(no);
		   return mapper.databoardDetailData(no);
	   }
	   
	   //4. 삭제
	   //@Select("SELECT pwd FROM spring_databoard WHERE no=#{no}")
	   //public String databoardGetPassword(int no);
	   
	   //@Select("SELECT filename,filesize,filecount FROM spring_databoard WHERE no=#{no}")
	   public DataBoardVO databoardFileInfoData(int no)
	   {
		   return mapper.databoardFileInfoData(no);
	   }
	   
	   //@Delete("DELETE FROM spring_databoard WHERE no=#{no}")
	   public boolean databoardDelete(int no,String pwd)
	   {
		   boolean bCheck=false;
		   String db_pwd=mapper.databoardGetPassword(no);
		   if(db_pwd.equals(pwd))
		   {
			  bCheck=true;
			  mapper.databoardDelete(no);
		   }
		   return bCheck;
	   }
	   
	   // 수정 데이터 
	   public DataBoardVO databoardUpdateData(int no)
	   {
		   return mapper.databoardDetailData(no);
	   }
	   
	   public boolean pwdCheck(int no,String pwd)
	   {
		   boolean bCheck=false;
		   String db_pwd=mapper.databoardGetPassword(no);
		   if(db_pwd.equals(pwd))
			   bCheck=true;
		   return bCheck;
	   }
	   
	   /*
	    *  @Update("UPDATE spring_databoard SET "
		  +"name=#{name},subject=#{subject},content=#{content} "
		  +"WHETE no=#{no}")
   public void databoardUpdate(DataBoardVO vo);
	    */
	   public void databoardUpdate(DataBoardVO vo)
	   {
		   mapper.databoardUpdate(vo);
	   }
	   
	   public int FindCount(Map map)
	   {
		   return mapper.FindCount(map);
	   }
	   
	   public List<DataBoardVO> databoardFindData(Map map)
	   {
		   return mapper.databoardFindData(map);
	   }
}