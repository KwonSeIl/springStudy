package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sist.dao.*;
import java.util.*;
import com.sist.vo.*;
@RestController
public class FoodRestController {
   @Autowired
   private FoodDAO dao;
   /*
    * axios.get("http://localhost/web/food/category_info_vue.do",{
    * 		===> GetMapping
			  params:{
				  cno:_this.cno ==> 매개변수
			  }
		  }).then(function(response){
			  console.log(response.data)
			  _this.cate_info=response.data ==> return값
		  })
    */
   @GetMapping(value="food/food_main_vue.do",produces = "text/plain;charset=UTF-8")
   public String food_main_vue()
   {
      List<CategoryVO> list=dao.categoryListData();
      JSONArray arr=new JSONArray();
      for(CategoryVO vo:list)
      {
         JSONObject obj=new JSONObject();
         obj.put("cno", vo.getCno());
         obj.put("title", vo.getTitle());
         obj.put("poster", vo.getPoster());
         arr.add(obj);
      }
      return arr.toJSONString();
   }
   @GetMapping(value="food/category_info_vue.do",produces = "text/plain;charset=UTF-8")
   public String category_info_vue(int cno)
   {
	   CategoryVO vo=dao.categoryInfoData(cno);
	   JSONObject obj=new JSONObject();
	   obj.put("title", vo.getTitle());
	   obj.put("subject", vo.getSubject());
	   return obj.toJSONString();
   }
   //fno,name,poster,score,tel,address,type
   @GetMapping(value="food/food_list_vue.do",produces = "text/plain;charset=UTF-8")
   public String food_list_vue(int cno)
   {
	   List<FoodVO> list=dao.foodListData(cno);
	   JSONArray arr=new JSONArray(); //javascript로 보낼 때 list가 array로 바뀜 []
	   for(FoodVO vo:list)
	   {
		   JSONObject obj=new JSONObject(); //VO 만듦 => {}
		   obj.put("fno", vo.getFno());
		   obj.put("name", vo.getName());
		   obj.put("score", vo.getScore());
		   obj.put("tel", vo.getTel());
		   obj.put("type", vo.getType());
		   String addr=vo.getAddress();
		   addr=addr.substring(0,addr.lastIndexOf("지"));
		   obj.put("address", addr.trim());
		   String poster=vo.getPoster();
		   poster=poster.substring(0,poster.indexOf("^"));
		   poster=poster.replace("#", "&");
		   obj.put("poster", poster);
		   arr.add(obj);
	   }
	   return arr.toJSONString();
   }
}