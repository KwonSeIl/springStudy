package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
// 요청 ==> Controller => JSP
public class JejuController {
   @Autowired
   private JejuDAO dao;
   
   @GetMapping("jeju/list.do")
   //jeju/list.do?page=1&no=10
   public String jeju_list(String page,Model model,HttpServletRequest request)
   {
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=20;
	   int start=(rowSize*curpage)-(rowSize-1);
	   int end=rowSize*curpage;
	   map.put("start", start);
	   map.put("end", end);
	   List<JejuLocationVO> list=dao.jejuLocationListData(map);
	   for(JejuLocationVO vo:list)
	   {
		   String title=vo.getTitle();
		   if(title.length()>19)
		   {
			   title=title.substring(0,16)+"...";
			   vo.setTitle(title);
		   }
		   vo.setTitle(title);
	   }
	   int totalpage=dao.jejuTotalPage();
	   
	   final int BLOCK=5;
	   int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	   int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	   // 1 2 3 4 5
	   // 6 7 8 9 10
	   if(endPage>totalpage)
		   endPage=totalpage;
	   
	   model.addAttribute("curpage", curpage);
	   model.addAttribute("totalpage", totalpage);
	   model.addAttribute("startPage", startPage);
	   model.addAttribute("endPage", endPage);
	   model.addAttribute("list", list);
	   
	   List<JejuLocationVO> cList=new ArrayList<JejuLocationVO>();
	   //쿠키 관련
	   Cookie[] cookies=request.getCookies();
	   if(cookies!=null)
	   {
		   for(int i=cookies.length-1;i>=0;i--)
		   {
			   if(cookies[i].getName().startsWith("jeju"))
			   {
				   String no=cookies[i].getValue();
				   JejuLocationVO vo=dao.jejuDetailData(Integer.parseInt(no));
				   cList.add(vo);
			   }
		   }
	   }
	   model.addAttribute("cList",cList);
	   return "jeju/list";
   }
   //jeju/detail_before.do?no=${vo.no }
   @GetMapping("jeju/detail_before.do")
   public String jeju_detail_before(String no,HttpServletResponse response,RedirectAttributes ra) 
   {
	   Cookie cookie=new Cookie("jeju"+no, no);
	   cookie.setPath("/");
	   cookie.setMaxAge(60*60*24);
	   //브라우저로 전송
	   response.addCookie(cookie);
	   ra.addAttribute("no",no);
	   return "redirect:detail.do?";
   }
   //jeju/detail.do?no=${vo.no }
   @GetMapping("jeju/detail.do")
   public String jeju_detail(int no,Model model)
   {
	   JejuLocationVO vo=dao.jejuDetailData(no);
	   String poster=vo.getInfo();
	   int i=poster.indexOf("^");
	   System.out.println("i="+i);
	   if(i>=0)
	   {
		   poster=poster.substring(0,poster.indexOf("^"));
		   System.out.println("poster="+poster);
		   
	   }
	   vo.setInfo(poster);
	   String addr=vo.getAddr();
	   String[] addrs=addr.split(" ");
	   Map map=new HashMap();
	   map.put("addr", addrs[1].trim());
	   List<JejuFoodVO> list=dao.jejuFoodData(map);
	   model.addAttribute("vo", vo);
	   model.addAttribute("list", list);
	   return "jeju/detail";
   }
}
