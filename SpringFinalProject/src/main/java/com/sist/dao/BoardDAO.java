package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class BoardDAO {
   @Autowired
   private BoardMapper mapper;
   
      /*@Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
			 +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
			 +"FROM (SELECT /*+ INDEX_DESC(spring_board sb_no_pk)no,subject,name,regdate,hit "
			 +"FROM spring_board)) "
			 +"WHERE num BETWEEN #{start} AND #{end}")*/
	  public List<BoardVO> boardListData(Map map)
	  {
		  return mapper.boardListData(map);
	  }
	  
	  //@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	  public int boardTotalPage()
	  {
		  return mapper.boardTotalPage();
	  }
	  /*@SelectKey(keyProperty = "no",resultType = int.class , before = true,
			  statement = "SELECT NVL(MAX(no)+1,1) as no FROM spring_board")
	  @Insert("INSERT INTO spring_board VALUES("
			 +"#{no},#{name},#{subject},#{content},#{pwd},SYSDATE,0)")*/
	  public void boardInsert(BoardVO vo)
	  {
		  mapper.boardInsert(vo);
	  }
	  
	  /*@Update("UPDATE spring_board SET "
				 +"hit=hit+1 "
				 +"WHERE no=#{no}")
		public void boardHitIncrement(int no);
		  
		@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
				 +"FROM spring_board "
				 +"WHERE no=#{no}")*/
		public BoardVO boardDetailData(int no)
		{
			mapper.boardHitIncrement(no);
			return mapper.boardDetailData(no);
		}
		
		/*
		 *   @Select("SELECT pwd FROM spring_board "
					 +"WHERE no=#{no}")
			  public String boardGetPassword(int no);
			  
			  @Update("UPDATE spring_board SET "
					 +"name=#{name},subject=#{subject},content=#{content} "
					 +"WHERE no=#{no}")
			  public void boardUpdate(BoardVO vo);
			  
			  @Delete("DELETE FROM spring_board "
					 +"WHERE no=#{no}")
			  public void boardDelete(int no);
		 */
		public String boardUpdate(BoardVO vo)
		{
			String res="no";
			String db_pwd=mapper.boardGetPassword(vo.getNo());
			if(db_pwd.equals(vo.getPwd()))
			{
				mapper.boardUpdate(vo);
				res="yes";
			}
			return res;
		}
		
		public String boardDelete(int no,String pwd)
		{
			String res="no";
			String db_pwd=mapper.boardGetPassword(no);
			if(db_pwd.equals(pwd))
			{
				mapper.boardDelete(no);
				res="yes";
			}
			return res;
		}
}