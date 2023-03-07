package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.BoardVO;
public interface BoardMapper {
	// 목록 출력 ==> 페이징
	@Select("SELECT no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit,num "
			+"FROM(SELECT no,subject,name,regdate,hit,rownum as num "
			+"FROM (SELECT /*+INDEX_DESC(spring_board sb_no_pk) */no,subject,name,regdate,hit "
			+"FROM spring_board)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	// #{start => key이름} => map.get("start"), #{end} => map.get("end")
	public List<BoardVO> boardListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	/*
	 *  1. 매개변수는 반드시 1개만 설정할 수 있다 (int start,int end)
	 *  2. 여러개 매개변수가 있는 경우
	 *  	VO, MAP
	 *  3. 리턴형 => resultType => SQL문장을 실행한 결과값
	 *  	ROW 여러개 => List
	 *  	ROW 한개 => VO
	 *  
	 *  select pwd from spring_board => List<String>
	 *  select pwd from spring_board where no=1 => String
	 */
	// 데이터 추가
	@Insert("INSERT INTO spring_board(no,name,subject,content,pwd) "
			+"VALUES(sb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	// #{name} => vo.getName()
	public void boardInsert(BoardVO vo);
	// 상세보기
	@Update("UPDATE spring_board SET "
			+"hit=hit+1 "
			+"WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit "
			+"FROM spring_board "
			+"WHERE no=#{no}")
	public BoardVO boardDetailData(int no);
	
	@Select("SELECT pwd FROM spring_board WHERE no=#{no}")
	public String boardGetPassword(int no);
	// 데이터 수정
	@Update("UPDATE spring_board SET "
			+"name=#{name},subject=#{subject},content=#{content} "
			+"WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	// 데이터 삭제
	@Delete("DELETE FROM spring_board WHERE no=#{no}")
	public void boardDelete(int no);
	// 데이터 검색
	@Select("SELECT COUNT(*) FROM spring_board "
			+"WHERE ${fs} LIKE '%'||#{ss}||'%'")
	public int boardFindCount(Map map); //String 변수 두개 쓰면 에러 발생
	// name '홍'
	/*
	 * 	where name	 like 	'%홍%'
	 * 		  ----     		 -----
	 * 		''안들어간 부분: $   	''들어가는 부분:#
	 *      -------					실제 데이터값 => #{}
	 *      column / table => ${}		
	 */
	@Select("SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit FROM spring_board "
			+ "WHERE ${fs} LIKE '%'||#{ss}||'%'") /* #은 실제값 => ''붙어서 들어옴 $=> 테이블명 */
	public List<BoardVO> boardFindData(Map map);
}
