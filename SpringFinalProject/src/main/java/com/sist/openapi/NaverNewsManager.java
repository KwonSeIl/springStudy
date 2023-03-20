package com.sist.openapi;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.sist.vo.NewsVO;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
@Component
public class NaverNewsManager {
	
	/*public static void main(String[] args) {
		NaverNewsManager n=new NaverNewsManager();
		n.newsData("레시피");
	}*/
	/*
	 * {	"lastBuildDate":"Wed, 08 Mar 2023 14:20:31 +0900",	"total":507234,	"start":1,	"display":10,	
	 * "items":[		{			"title":"가전 매장, 스토어 변신 &quot;코딩부터 쿠킹까지&quot;",	
	 * 		"originallink":"https:\/\/www.ebn.co.kr\/news\/view\/1569621\/?sc=Naver",			"link":"https:\/\/www.ebn.co.kr\/news\/view\/1569621\/?sc=Naver",			"description":"음식 <b>레시피<\/b>를 포함해 조리 시 사용하는 밀레 주방 가전의 기능적 장점도 자세히 설명한다. 금액은 2인 기준 15만원으로 네이버 예약 결제를 통해 밀레 익스피리언스 센터 한남에서 주 1회 11시 30분부터 1시까지 실시한다.... ",			"pubDate":"Wed, 08 Mar 2023 14:18:00 +0900"		},		{			"title":"[포토] 딜리셔스 페스티벌 방문한 정용진 신세계 부회장",			"originallink":"https:\/\/biz.newdaily.co.kr\/site\/data\/html\/2023\/03\/08\/2023030800111.html",			"link":"https:\/\/biz.newdaily.co.kr\/site\/data\/html\/2023\/03\/08\/2023030800111.html",			"description":"뉴데일리 정상윤 기자 = 정용진 신세계그룹 부회장이 8일 오전 서울 서초구 aT센터에서 열린 &apos;2023 이마트24 상품전시회 딜리셔스 페스티벌&apos;에서 경영주 <b>레시피<\/b> 콘테스트 출품 <b>레시피<\/b>를 시식해보고 있다. (사진... ",			"pubDate":"Wed, 08 Mar 2023 14:18:00 +0900"		},		{			"title":"하이볼 전성시대, 캔 하이볼 ‘로얄오크 프리미엄’ 편의점서 판매",			"originallink":"https:\/\/lady.khan.co.kr\/khlady.html?mode=view&artid=202303081415001&code=4",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/145\/0000018216?sid=103",			"description":"일본 정통 하이볼의 <b>레시피<\/b>를 그대로 담아내 청량감을 극대화하면서도 단맛을 최소화해 어떤 음식과도 잘 어울린다. 실제로 국내 시장에서 큰 인기를 누리는 하이볼의 매력은 치킨, 삼겹살, 족발 등 다양한 안주와 함께... ",			"pubDate":"Wed, 08 Mar 2023 14:16:00 +0900"		},		{			"title":"쿠팡, 임정식 프리미엄 양곰탕 론칭 ",			"originallink":"http:\/\/www.bigtanews.co.kr\/news\/articleView.html?idxno=13666",			"link":"http:\/\/www.bigtanews.co.kr\/news\/articleView.html?idxno=13666",			"description":"<b>레시피<\/b>는 진지와 합동 개발했으며, 임 셰프가 제조 전 과정을 검수했다. 진지는 이번 제품을 시작으로 임 셰프와 지속적으로 협업해 평양냉면, 곰탕 등 한국 고유 전통을 지키는 프리미엄 가정간편식을 지속해서 선보일... ",			"pubDate":"Wed, 08 Mar 2023 14:12:00 +0900"		},		{			"title":"에산군 &apos;잘 나갈 때 조심!&apos;…예산상설시장 긴급 점검",			"originallink":"http:\/\/www.dtnews24.com\/news\/articleView.html?idxno=741603",			"link":"http:\/\/www.dtnews24.com\/news\/articleView.html?idxno=741603",			"description":"이어진 시장 주변 국숫집 대표 9명과의 간담회에서는 시장 내 &apos;파기름 국수&apos;의 가격이 저렴한 것에 대한 인근 업소의 불만을 해소하고, 주변 국숫집도 <b>레시피<\/b>를 제공받되 가격을 맞춰 예산국수가 서민의 대표 메뉴로 한층... ",			"pubDate":"Wed, 08 Mar 2023 14:12:00 +0900"		},		{			"title":"예산군, 예산상설시장 활성화 위해 &apos;민관 투톱&apos;이 뭉쳤다",			"originallink":"https:\/\/www.ccdailynews.com\/news\/articleView.html?idxno=2190455",			"link":"https:\/\/www.ccdailynews.com\/news\/articleView.html?idxno=2190455",			"description":"이어진 시장 주변 국숫집 대표 9명과의 간담회에서는 시장 내 &apos;파기름 국수&apos;의 가격이 저렴한 것에 대한 인근 업소의 불만을 해소하고 주변 국숫집도 <b>레시피<\/b>를 제공받되 가격을 맞춰 예산국수가 서민의 대표 메뉴로 한층... ",			"pubDate":"Wed, 08 Mar 2023 14:10:00 +0900"		},		{			"title":"코스맥스, 맞춤 화장품 플랫폼 구축",			"originallink":"http:\/\/www.seoulfn.com\/news\/articleView.html?idxno=481387",			"link":"http:\/\/www.seoulfn.com\/news\/articleView.html?idxno=481387",			"description":"쓰리와우 웹사이트나 공식 응용 프로그램(앱)에서 1대1 문진하고 1260만가지 조합 가운데 소비자한테 맞춘 처방(<b>레시피<\/b>) 하나를 만들 수 있다. 개인마다 다른 처방은 최소주문수량(MOQ)인 한 개까지 생산 가능한 설비에서... ",			"pubDate":"Wed, 08 Mar 2023 14:09:00 +0900"		},		{			"title":"트레디움, GS25서 &apos;일본 정통 <b>레시피<\/b>&apos; 로얄 하이볼 출시",			"originallink":"http:\/\/moneys.mt.co.kr\/news\/mwView.php?no=2023030813553690286",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/417\/0000901753?sid=101",			"description":"트레디움은 편의점 GS25를 통해 국내 최초로 일본 정통 하이볼 <b>레시피<\/b>를 그대로 담은 &apos;로얄 오크 프리미엄... 로얄 하이볼은 특유의 드라이함으로 인기를 얻고 있는 일본 정통 하이볼 <b>레시피<\/b>를 그대로 담았다. 하이볼은... ",			"pubDate":"Wed, 08 Mar 2023 14:03:00 +0900"		},		{			"title":"최재구 예산군수, 백종원 대표와 &apos;예산상설시장 활성화&apos; 간담회",			"originallink":"http:\/\/www.shinailbo.co.kr\/news\/articleView.html?idxno=1671225",			"link":"http:\/\/www.shinailbo.co.kr\/news\/articleView.html?idxno=1671225",			"description":"이어진 시장 주변 국숫집 대표 9명과의 간담회에서는 시장 내 ‘파기름 국수’의 가격이 저렴한 것에 대한 인근 업소의 불만을 해소하고 주변 국숫집도 <b>레시피<\/b>를 제공받되 가격을 맞춰 예산국수가 서민의 대표 메뉴로... ",			"pubDate":"Wed, 08 Mar 2023 14:02:00 +0900"		},		{			"title":"시식하는 정용진 부회장",			"originallink":"http:\/\/www.newsis.com\/view\/?id=NISI20230308_0019816169",			"link":"https:\/\/n.news.naver.com\/mnews\/article\/003\/0011730038?sid=101",			"description":"정용진 신세계그룹 부회장이 8일 서울 서초구 aT센터에서 열린 &apos;2023 이마트24 상품전시회 딜리셔스 페스티벌&apos;에서 경영주 <b>레시피<\/b> 콘테스트 출품 식품을 시식하고 있다. (공동취재사진) 2023.03.08. photo@newsis.com... ",			"pubDate":"Wed, 08 Mar 2023 14:01:00 +0900"		}	]}

	 */

	public List<NewsVO> newsListData(String ss)
	{
		List<NewsVO> list=new ArrayList<NewsVO>();
		String json=newsData(ss); //json으로 들어온다
		try
		{
			JSONParser jp=new JSONParser();
			JSONObject root=(JSONObject)jp.parse(json);
			JSONArray arr=(JSONArray)root.get("items");
			for(int i=0;i<arr.size();i++)
			{
				JSONObject obj=(JSONObject)arr.get(i); //[]안에서 하나씩 꺼냄 
				NewsVO vo=new NewsVO();
				vo.setTitle(obj.get("title").toString()); //->문자열로 변환
				vo.setDescription(obj.get("description").toString()); 
				vo.setLink(obj.get("link").toString()); 
				String day=obj.get("pubDate").toString();
				String newsday=new SimpleDateFormat("yyyy-MM-dd").format(new Date(day));
				vo.setPubDate(newsday); 
				list.add(vo);
			}
		}catch(Exception ex) {}
		return list;
	}
	public String newsData(String ss)
	{
		
		String clientId = "zfjVWRbL1JozXvDJYnbp"; //애플리케이션 클라이언트 아이디값"
        String clientSecret = "FWQCC3IwmH"; //애플리케이션 클라이언트 시크릿값"


        String text = null;
        try {
            text = URLEncoder.encode(ss, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/news.json?display=100&query=" + text;    // json 결과
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);


        //System.out.println(responseBody);
        return responseBody;
	}
	private String get(String apiUrl, Map<String, String> requestHeaders){
	    HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }
	
	
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	}
	
	
	    private  HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }
	
	
	    private String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);
	
	
	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();
	
	
	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }
	
	
	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }
}
