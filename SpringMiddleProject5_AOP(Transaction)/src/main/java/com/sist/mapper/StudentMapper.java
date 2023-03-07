package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.dao.StudentVO;
public interface StudentMapper {
	@Select("SELECT hakbun,name,kor,eng,math FROM student")
	public List<StudentVO> studentListData();
	
	@Insert("INSERT INTO student VALUES(#{hakbun},#{name},#{kor},#{eng},#{math})")
	public void studentInsert(StudentVO vo);
}
