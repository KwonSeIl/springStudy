package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@RestController
public class RecipeRestController {
   @Autowired
   private RecipeService service;
   
   @GetMapping(value = "recipe/recipe_list_vue.do",produces = "text/plain;charset=utf-8")  // params: page:this.curpage
   public String recipe_list_vue(int page)   // String 말고 int로 VueJS에서 int로 curpage를 받아서 
   {                                         // String 이 아니여서 조건문으로 null값 처리 안해도 됨
      Map map = new HashMap();
      map.put("start", (page*20)-19);
      map.put("end", (page*20));
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
      for(RecipeVO vo:list)
      {
         JSONObject obj=new JSONObject();
         obj.put("no", vo.getNo());
         obj.put("title", vo.getTitle());
         obj.put("poster", vo.getPoster());
         obj.put("chef", vo.getChef());
         if(i==0)
         {
            obj.put("curpage",page );
            obj.put("totalpage",totalpage );
            obj.put("startPage",startPage );
            obj.put("endPage",endPage );
            obj.put("count",count );
         }
         
         arr.add(obj);
         i++;
      }
      return arr.toJSONString();
   }
   @GetMapping(value="recipe/chef_list_vue.do",produces = "text/plain;charset=utf-8")
   public String chef_list_vue(int page)
   {
      Map map=new HashMap();
      map.put("start", (page*50)-49);
      map.put("end", (page*50));
      List<ChefVO> list=service.chefListData(map);
      int totalpage=service.chefTotalPage();
      
      int i=0;
      JSONArray arr=new JSONArray();
      for(ChefVO vo: list)
      {
         JSONObject obj=new JSONObject();
         obj.put("chef",vo.getChef());
         obj.put("poster",vo.getPoster());
         obj.put("mc1",vo.getMem_cont1() );
         obj.put("mc2",vo.getMem_cont2() );
         obj.put("mc3",vo.getMem_cont3() );
         obj.put("mc7",vo.getMem_cont7() );
         
         if(i==0)
         {
            obj.put("curpage",page );
            obj.put("totalpage",totalpage );
         }
         arr.add(obj);
         i++;
         
      }
      return arr.toJSONString();
   }
   
   @GetMapping(value = "recipe/chef_make_vue.do",produces = "text/plain;charset=utf-8")  // params: page:this.curpage
   public String chef_make_vue(String chef)   // String 말고 int로 VueJS에서 int로 curpage를 받아서 
   {                                         // String 이 아니여서 조건문으로 null값 처리 안해도 됨
      List<RecipeVO> list=service.chefMakeRecipeData(chef);
      JSONArray arr=new JSONArray();
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
}





















