package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@RestController
public class JejuRestController {
	@Autowired
	private JejuDAO dao;
	
	@GetMapping(value="jeju/jeju_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String jejuList_vue(String page)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		List<JejuFoodVO> sList=dao.jejuFoodListData(map);
		int totalpage=dao.jejuTotalPage();
		
		JSONArray arr=new JSONArray();//List
		int i=0;
		for(JejuFoodVO vo:sList)
		{
			   JSONObject obj=new JSONObject();//VO
			   obj.put("no", vo.getNo());
			   obj.put("title", vo.getTitle());
			  
			   obj.put("poster", vo.getPoster());
			   // {fno:1,name:"",poster:""}{} {}{}
			   if(i==0)
			   {
				   obj.put("curpage", page);
				   obj.put("totalpage", totalpage);
			   }
			   arr.add(obj);
			   i++;
		}
		return arr.toJSONString();
	}
	@GetMapping(value="jeju/jeju_detail_vue.do",produces = "text/plain;charset=UTF-8")
	   public String jeju_detail_vue(int no)
	   {
		   JejuFoodVO vo=dao.jejuDetailData(no);
		   
		   JSONObject obj=new JSONObject();
		   obj.put("no", vo.getNo());
		   obj.put("url", vo.getUrl());
		   obj.put("poster", vo.getPoster());
		   obj.put("image", vo.getImage());
		   obj.put("addr", vo.getAddr());
		   obj.put("addr2", vo.getAddr2());
		   obj.put("tel", vo.getTel());
		   obj.put("type", vo.getType());
		   obj.put("parking", vo.getParking());
		   obj.put("time", vo.getTime());
		   obj.put("menu", vo.getMenu());
		   obj.put("score", vo.getScore());
		   
		   return obj.toJSONString();
	   }
}
