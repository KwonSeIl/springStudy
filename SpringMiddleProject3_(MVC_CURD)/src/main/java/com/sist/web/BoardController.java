package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
//Model
//화면 전환 = 리턴형 (jsp,do)
@Controller
@RequestMapping("board/")
/*
 * 	사용자 요청 ==> .do ==> 요청 처리 ==> @RequestMapping()
 * 
 */
public class BoardController {
	@Autowired
	private BoardDAO dao;
	
	/*
	 * 	?page=1&no=1
	 * public String board_list(String page,int no)
	 *  => 모든 데이터형(매개변수)
	 *  	1) 해당 데이터형
	 *  	2) null = String
	 */
	@RequestMapping("list.do")
	public String board_list(String page,Model model) //Model 전송 객체 => request전송. 값 안들어올 때는 페이지만, 값 있을 때는 int no => 상세보기
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		map.put("start", start); //#{start}
		map.put("end", end); //#{end}
		List<BoardVO> list=dao.boardListData(map);
		int totalpage=dao.boardTotalPage();
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("list",list); //request.setAttribute("list",list);
		return "board/list"; //jsp생략
	}
	@RequestMapping("insert.do")
	public String board_insert() //보내줄 값이 없어서 model 사용X => 전송할 값이 있을 때 model 사용
	{
		return "board/insert";
	}
	@RequestMapping("insert_ok.do")
	public String board_insert_ok(BoardVO vo)
	{
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	@RequestMapping("detail.do")
	public String board_detail(int no,Model model)
	{
		BoardVO vo=dao.boardDetailData(no);
		model.addAttribute("vo",vo);
		return "board/detail";
	}
	// <a href="update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a>
	@RequestMapping("update.do") //if(uri.equals("update.do")) Annotation은 조건문 대신 사용
	public String board_update(int no,Model model)
	{
		BoardVO vo=dao.boardUpdateData(no);
		String s=vo.getSubject();
		s=s.replace("\"", "");
		vo.setSubject(s);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	//<a href="delete.do?no=${vo.no }" class="btn btn-xs btn-info">삭제</a>
	@RequestMapping("delete.do")
	public String board_delete(int no,Model model)
	{
		model.addAttribute("no", no);
		return "board/delete";
	}
	@RequestMapping("find.do")
	public String board_find(String fs,String ss,Model model)
	{
		Map map=new HashMap();
		map.put("fs", fs);
		map.put("ss", ss);
		List<BoardVO> list=dao.boardFindData(map);
		int count=dao.boardFindCount(map);
		// 데이터를 JSP로 전송
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "board/find";
	}
}
