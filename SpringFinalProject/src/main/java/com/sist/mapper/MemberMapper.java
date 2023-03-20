package com.sist.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface MemberMapper {
	//로그인 처리
  @Select("SELECT COUNT(*) FROM spring_join "
		 +"WHERE id=#{id}") //id 중복체크 가능
  public int memberIdCheck(String id);
  
  @Select("SELECT id,pwd,name,admin FROM spring_join "
		 +"WHERE id=#{id}")
  public MemberVO memberLogin(String id);
  
  // JOIN
  @Insert("INSERT INTO spring_join VALUES("
		  +"#{id},#{pwd},#{name},'n')")
  public void memberInsert(MemberVO vo);
}