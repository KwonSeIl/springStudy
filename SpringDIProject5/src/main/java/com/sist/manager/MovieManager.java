package com.sist.manager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.sist.vo.*;
//https://www.kobis.or.kr/kobis/business/main/main.do
/*
 * 일일박스오피스
 * 실시간예매율
 * 좌석점유율순위
 * 온라인 상영관 일일
 */
@Component("mm")
public class MovieManager {
   /**public static void main(String[] args) {
      MovieManager m=new MovieManager();
      List<MovieVO> list=m.movieListData(1);
      for(MovieVO vo:list)
      {
    	  System.out.println(vo.getRank()+" "+vo.getTitle()+" "+vo.getGenre()+" "+vo.getDirector());
      }
   }*/
   private String[] strUrl= {"","searchMainDailyBoxOffice.do","searchMainRealTicket.do",
         "searchMainDailySeatTicket.do","searchMainOnlineDailyBoxOffice.do"};
   public List<MovieVO> movieListData(int no)
   {
      List<MovieVO> list=new ArrayList<MovieVO>();
      /*try
      {
         URL url=new URL("https://www.kobis.or.kr/kobis/business/main/"+strUrl[no]);
         HttpURLConnection conn=(HttpURLConnection)url.openConnection();
         if(conn!=null) //연결이 되었다면
         {
            BufferedReader br=
                  new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while(true)
            {
               String data=br.readLine();
               if(data==null) break;
               System.out.println(data);
            }
         }
      }catch(Exception ex) {}*/
      try
      {
    	  //[]
         Document doc=Jsoup.connect("https://www.kobis.or.kr/kobis/business/main/"+strUrl[no]).get();
         String data=doc.toString();
         data=data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
         data=data.replace("<", "");
         data=data.replace("<", "");
         System.out.println(data);
         
         JSONParser jp=new JSONParser();
         JSONArray arr=(JSONArray)jp.parse(data);
         /*
          * 	JSONArray	==> [] ==> ArrayList
          * 	JSONObject	==> {} ==> 객체
          * 	[{},{},{},{}...]
          */
         //System.out.println(doc.toString());
         // ["a","b","c"] => String 으로 받으면 된다	[{],{},{}] => JSONObject
         for(int i=0;i<arr.size();i++)
         {
        	 JSONObject obj=(JSONObject)arr.get(i);
        	 MovieVO vo=new MovieVO();
        	 String rank=String.valueOf((long)obj.get("rank"));
        	 vo.setRank(Integer.parseInt(rank));
        	 vo.setTitle((String)obj.get("movieNm"));
        	 vo.setDirector((String)obj.get("director"));
        	 vo.setGenre((String)obj.get("genre"));
        	 list.add(vo);
         }
      }catch(Exception ex) {ex.printStackTrace();}
      return list;
   }
}