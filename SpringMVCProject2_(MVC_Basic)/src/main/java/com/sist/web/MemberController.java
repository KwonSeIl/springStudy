package com.sist.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.MemberVO;

@Controller
public class MemberController {
	@RequestMapping("member/insert.do")
	public String member_insert(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String address=request.getParameter("address");
		String tel=request.getParameter("tel");

		MemberVO vo=new MemberVO();
		vo.setName(name);
		vo.setAddress(address);
		vo.setSex(sex);
		vo.setTel(tel);
		
		request.setAttribute("vo", vo);
		return "member/insert_ok";
	}
	
	@RequestMapping("member/insert2.do") //GET, POST 동시에 처리
	/*
	 * 	public void addAttribute(String key,Object obj)
	 *  {
	 *  	request.setAttribute(key,obj)
	 *  }
	 *  ?page=10
	 *  (int page) 
	 */
	// request를 필요한 경우가 아니면 가급적 사용하지 않도록 한다(클라이언트 ip가 있기 때문에 보안에 문제 있음) 
	public String member_insert2(MemberVO vo,Model model)
	{
		//전송 객체 => Model
		model.addAttribute("vo",vo);  //request.setAttribute("vo", vo);과 같음
		return "member/insert_ok2";
	}
}
