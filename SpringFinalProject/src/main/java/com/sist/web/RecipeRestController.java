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
public class RecipeRestController {
  @Autowired
  private RecipeService service;
  
  @GetMapping(value="recipe/recipe_list_vue.do",produces = "text/plain;charset=UTF-8")
  public String recipe_list_vue(int page)// String 말고 int로 VueJS에서 int로 curpage를 받아서 
  {											// String 이 아니여서 조건문으로 null값 처리 안해도 됨
	  Map map=new HashMap();
	  map.put("start", (page*20)-19);
	  map.put("end", page*20);
	  List<RecipeVO> list=service.recipeListData(map);
	  int totalpage=service.recipeTotalPage();
	  String count=service.recipeRowCount();
	  
	  final int BLOCK=10;
	  int startPage=((page-1)/BLOCK*BLOCK)+1;
	  int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
	  if(endPage>totalpage)
		  endPage=totalpage;
	  
	  int i=0;
	  JSONArray arr=new JSONArray();
	  // [{no:,name:..},{sex:,...},{}]
	  // [{no:1,title:'',poster:'',chef:'',curpage:1,totalpage:4000,count:16....},{no:1,title:'',poster:'',chef:''},{no:1,title:'',poster:'',chef:''},{}.....]
	  for(RecipeVO vo:list)
	  {
		  JSONObject obj=new JSONObject();
		  obj.put("no", vo.getNo());
		  obj.put("title", vo.getTitle());
		  obj.put("poster", vo.getPoster());
		  obj.put("chef", vo.getChef());
		  if(i==0)
		  {
			  obj.put("curpage", page);
			  obj.put("totalpage", totalpage);
			  obj.put("startPage", startPage);
			  obj.put("endPage", endPage);
			  obj.put("count", count);
		  }
		  
		  arr.add(obj);
		  i++;
	  }
	  return arr.toJSONString();
  }
  
  @GetMapping(value="recipe/chef_list_vue.do",produces = "text/plain;charset=UTF-8")
  public String chef_list_vue(int page)
  {
	  Map map=new HashMap();
	  map.put("start", (page*50)-49);
	  map.put("end",page*50);
	  List<ChefVO> list=service.chefListData(map);
	  int totalpage=service.chefTotalPage();
	  
	  int i=0;
	  JSONArray arr=new JSONArray();
	  for(ChefVO vo:list)
	  {
		  JSONObject obj=new JSONObject();
		  obj.put("chef", vo.getChef());
		  obj.put("poster", vo.getPoster());
		  obj.put("mc1", vo.getMem_cont1());
		  obj.put("mc2", vo.getMem_cont2());
		  obj.put("mc3", vo.getMem_cont3());
		  obj.put("mc7", vo.getMem_cont7());
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
  
  @GetMapping(value="recipe/chef_make_vue.do",produces = "text/plain;charset=UTF-8")
  public String chef_make_vue(String chef)
  {
	  List<RecipeVO>list=service.chefMakeRecipeData(chef);
	  JSONArray arr=new JSONArray();
	  // [{no:,name:..},{sex:,...},{}]
	  // [{no:1,title:'',poster:'',chef:'',curpage:1,totalpage:4000,count:16....},{no:1,title:'',poster:'',chef:''},{no:1,title:'',poster:'',chef:''},{}.....]
	  for(RecipeVO vo:list)
	  {
		  JSONObject obj=new JSONObject();
		  obj.put("no", vo.getNo());
		  obj.put("title", vo.getTitle());
		  obj.put("poster", vo.getPoster());
		  obj.put("chef", vo.getChef());
		  arr.add(obj);
	  }
	  return arr.toJSONString();
  }
  @GetMapping(value="recipe/goods_price_vue.do",produces = "text/plain;charset=UTF-8")
  public String goods_price_vue(String goods_name)
  {
	  // [0-9A-Za-z]
	  String name=goods_name.replaceAll("[^가-힣]","");
	  List<GoodsVO> list=service.goodsListData(name);
	  JSONArray arr=new JSONArray();
	  for(GoodsVO vo:list)
	  {
		  JSONObject obj=new JSONObject();
		  obj.put("no", vo.getNo());
		  obj.put("goods_poster", vo.getGoods_poster());
		  String gname=vo.getGoods_name();
		  if(gname.length()>10)
		  {
			  gname=gname.substring(0,10)+"...";
			  
		  }
		  obj.put("goods_name", gname);
		  obj.put("goods_price", vo.getGoods_price());
		  
		  arr.add(obj);
	  }
	  return arr.toJSONString();
  }
  
}