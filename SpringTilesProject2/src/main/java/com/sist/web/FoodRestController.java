package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
/*
 *  JSON / JSONP / => JSONP, 크로져
 *   VueJS VS ReactJS => 생명주기,(동작과정, 장점,단점)
 *   
 *   JSON => 자바스크립트의 객체 표현법 ==> 모바일(Kotlin)
 *   ------------------------------
 *   {키:값,키:값..} => Object => VO
 *   [{},{},{}...] => Array => List
 */
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping(value="food/food_search_vue.do",produces = "text/plain;charset=UTF-8")
	// JavaScript,HTML,일반문자열=>text/html, JSON=> text/plain
	public String food_search(String page,String addr)
	{
		if(addr==null)
			addr="역삼";
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("start", (curpage*20)-19);
		map.put("end", curpage*20);
		map.put("addr", addr);
		List<FoodVO> list=dao.foodSearchData(map);
		int totalpage=dao.foodSearchTotalPage(map);
		// JavaScript에서 인식하는 프로그램으로 변경
		// fno,name,poster
		JSONArray arr=new JSONArray(); //List와 동일
		int i=0;
		for(FoodVO vo:list)
		{
			JSONObject obj=new JSONObject(); //VO와 동일
			obj.put("fno", vo.getFno());
			obj.put("name", vo.getName());
			String poster=vo.getPoster();
			poster=poster.substring(0, poster.indexOf("^"));
			poster=poster.replace("#", "&");
			obj.put("poster", poster);
			// {fno:1,name"",poster:""} => 자바스크립트에서의 VO
			if(i==0)
			{
				obj.put("curpage", curpage);
				obj.put("totalpage", totalpage);
			}
			arr.add(obj);
			i++;
		}
		System.out.println(arr.toJSONString());
		return arr.toJSONString(); //arr=> JSONarray넘어감
		
	}
	 @GetMapping(value = "food/location_detail_vue.do" ,produces = "text/plain;charset=utf-8")
	   public String food_location_detail(int fno)
	   {
	      FoodVO vo=dao.foodLocationDetailData(fno);
	      JSONObject obj=new JSONObject();
	      obj.put("fno", vo.getFno());
	      obj.put("name", vo.getName());
	      obj.put("poster", vo.getPoster());
	      obj.put("tel", vo.getTel());
	      obj.put("address", vo.getAddress());
	      obj.put("parking", vo.getParking());
	      obj.put("time", vo.getTime());
	      obj.put("price", vo.getPrice());
	      obj.put("type", vo.getType());
	      obj.put("menu", vo.getMenu());
	      return obj.toJSONString();
	   }
}
