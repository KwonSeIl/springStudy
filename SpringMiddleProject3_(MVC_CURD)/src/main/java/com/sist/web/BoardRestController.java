package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
// 데이터 전송 역할(javascript,json) => 수정, 삭제
@RestController
// vue,ajax,react => @ResponseBody => Rest API
/*
 *  @Controller: 화면 변경(return => 화면(forward,redirect))
 *  @RestController: 데이터를 전송(json,javascript,ajax) => 문자열
 *  @Repository: 데이터베이스 연결
 *  @Component: 일반 클래스 => Jsoup, Manager...(Open API)
 *  @Service:DAO여러개를 통합해서 사용 (board=reply) ==> BI
 *  ------------------------------------------------
 *  DAO vs Service 차이점
 */
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	  
	@RequestMapping(value="board/update_ok.do",produces = "text/html;charset=UTF-8")
	public String board_update_ok(BoardVO vo)
	{
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true)
		{
			result="<script>"
					+"location.href=\"detail.do?no="+vo.getNo()+"\""
					+"</script>";
		}
		else
		{
			result="<script>"
					+"alert(\"비밀번호가 틀립니다!!\");"
					+"history.back();"
					+"</script>";
		}
		return result;
	}
	@RequestMapping(value="board/delete_ok.do",produces = "text/html;charset=UTF-8")
	public String board_delete(int no,String pwd)
	{
		String result="";
		boolean bCheck=dao.boardDelete(no, pwd);
		if(bCheck==true)
		{
			result="<script>"
					+"location.href=\"list.do\""
					+"</script>";
		}
		else
		{
			result="<script>"
					+"alert(\"비밀번호가 틀립니다!!\");"
					+"history.back();"
					+"</script>";
		}
		return result;
	}
}
