package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class SeoulController {
	@Autowired
	private SeoulDAO dao;
	
	@GetMapping("seoul/list.do")
	   //seoul/list.do?page=1&no=10
	   public String seoul_list(String page,Model model,HttpServletRequest request)
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
		   List<SeoulLocationVO> list=dao.seoulLocationListData(map);
		   for(SeoulLocationVO vo:list)
		   {
			   String title=vo.getTitle();
			   if(title.length()>19)
			   {
				   title=title.substring(0,16)+"...";
				   vo.setTitle(title);
			   }
			   vo.setTitle(title);
		   }
		   int totalpage=dao.seoulTotalPage();
		   
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
		   
		   List<SeoulLocationVO> cList=new ArrayList<SeoulLocationVO>();
		   //쿠키 관련
		   Cookie[] cookies=request.getCookies();
		   if(cookies!=null)
		   {
			   for(int i=cookies.length-1;i>=0;i--)
			   {
				   if(cookies[i].getName().startsWith("seoul"))
				   {
					   String no=cookies[i].getValue();
					   SeoulLocationVO vo=dao.seoulDetailData(Integer.parseInt(no));
					   cList.add(vo);
				   }
			   }
		   }
		   model.addAttribute("cList",cList);
		   return "seoul/list";
	   }
		//seoul/detail_before.do?no=${vo.no }
	   @GetMapping("seoul/detail_before.do")
	   public String seoul_detail_before(String no,HttpServletResponse response,RedirectAttributes ra) 
	   {
		   Cookie cookie=new Cookie("seoul"+no, no);
		   cookie.setPath("/");
		   cookie.setMaxAge(60*60*24);
		   //브라우저로 전송
		   response.addCookie(cookie);
		   ra.addAttribute("no",no);
		   return "redirect:detail.do?";
	   }
	   //seoul/detail.do?no=${vo.no }
	   @GetMapping("seoul/detail.do")
	   public String seoul_detail(int no,Model model)
	   {
		   SeoulLocationVO vo=dao.seoulDetailData(no);
		   String addr=vo.getAddress();
		   String[] addrs=addr.split(" ");
		   Map map=new HashMap();
		   map.put("addr", addrs[2].trim());
		   List<FoodVO> list=dao.seoulFoodData(map);
		   model.addAttribute("list",list);
		   model.addAttribute("vo",vo);
		   return "seoul/detail";
	   }
	   //검색
	   @RequestMapping("seoul/list.do")//GET/POST
		public String seoul_find(String addr,String page,Model model)
		{
			System.out.println("addr="+addr);
			String s="";
			if(addr==null || addr.equals(""))
			{
				s="all";
			}
			else
			{
				s=addr;
			}
			if(page==null)
				page="1";
			int curpage=Integer.parseInt(page);
			int rowSize=20;
			int start=(rowSize*curpage)-(rowSize-1);
			int end=rowSize*curpage;
			Map map=new HashMap();
			map.put("start", start);
			map.put("end", end);
			map.put("ss", s);
			List<SeoulLocationVO> list=dao.SeoulFindData(map);
			model.addAttribute("list",list);
			return "seoul/list";
		}
	
}
