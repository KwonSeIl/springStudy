package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Controller
@RequestMapping("member/") //경로명이 길 때 사용 -> 실행 시 앞에 member/붙여서 나옴
public class MemberFController {
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("join.do")
	public String member_join()
	{
		return "member/join";
	}
	
}
