<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.row3{
	width: 100%;
}
</style>
<link rel="stylesheet" href="../layout/styles/food.css">
</head>
<body>
<div class="wrapper row3 rows">
  <main class="container clear">
    <div class="row">
      <table class="table">
        <tr>
          <td class="inline">
            <input type="text" size="25" class="input-sm" ref="ss" v-model="ss"> <!-- v-model=>값 채워줌 -->
            <input type="button" value="검색" class="btn btn-sm btn-primary" v-on:click="find()">
          </td>
        </tr>
      </table>
    </div>
    <div style="height: 20px"></div>
    <div class="row3">
      <div id="a">
        <img :src="'../images/map/1111.png'" id="seoul_1">
        <c:forEach var="i" begin="1" end="25">
          <img src="../images/map/gu_${i }_off.png"id="gu${i }"
            onmouseover="this.src='../images/map/gu_${i }_on.png'"
            onmouseout="this.src='../images/map/gu_${i }_off.png'"
            v-on:click="imageClick(${i })"
        >
        </c:forEach>
      </div>
    </div>
    <div style="height: 20px"></div>
    <div class="row3">
      <div class="col-md-3" v-for="vo in food_list"> <!-- 한줄에 4개 출력 -->
        <div class="thumbnail">
          <a :href="'../food/food_location_detail.do?fno='+vo.fno">
            <img :src="vo.poster" alt="Lights" style="width:100%">
            <div class="caption">
              <p style="font-size: 8px">{{vo.name}}&nbsp;<span style="color: orange">{{vo.score}}</span></p>
            </div>
          </a>
        </div>
     </div>
    </div>
    <div class="row">
        <ul class="pagination" style="text-align: center;width: 100%">
		  <li v-if="startPage>1"><span class="btn btn-xs btn-default" v-on:click="prev()">&lt;</span></li>
		  <li v-for="i in range(startPage,endPage)"><span class="btn btn-xs btn-default" v-on:click="pageChange(i)">{{i}}</span></li> <!-- i => 1~10까지 -->
		  <li v-if="totalpage>endPage"><span class="btn btn-xs btn-default" v-on:click="next()">&gt;</span></li>
		</ul>
    </div>
  </main>
</div>
<script>
new Vue({
     el:'.rows',
     data:{ //vue의 멤버변수 들어가는 부분
        food_list:[],
        curpage:1,
        totalpage:0,
        startPage:0,
        endPage:0,
        ss:'역삼',
        guList:["전체", "강서구", "양천구", "구로구", "마포구", "영등포구", "금천구",
			"은평구", "서대문구", "동작구", "관악구", "종로구", "중구", "용산구", "서초구", "강북구",
			"성북구", "도봉구", "동대문구", "성동구", "강남구", "노원구", "중랑구", "광진구", "송파구",
			"강동구"]
     },
     mounted:function(){
        this.getData();
     },
     methods:{
        //사용자 정의 =>페이징기법 뷰 사용
        getData:function(){
           let _this=this;
           axios.get("http://localhost/web/food/food_find_vue.do",{
               params:{
                 page:this.curpage,
                 address:this.ss
              }  
           }).then(function(response){
              console.log(response.data)
              _this.food_list=response.data;
              _this.curpage=response.data[0].curpage
              _this.totalpage=response.data[0].totalpage
              _this.startPage=response.data[0].startPage
              _this.endPage=response.data[0].endPage
           })
        },
        range:function(min,max){
           let array=[],
           j=0;
           for(let i=min;i<=max;i++){
              array[j]=i;
              j++;
           }
           return array;
        },
        pageChange:function(page){
           this.curpage=page;
           this.getData();
        },
        find:function(){
           this.curpage=1;
           this.getData();
        },
        next:function(){
           this.curpage=this.endPage+1
           this.getData()
        },
        prev:function(){
           this.curpage=this.startPage-1
           this.getData()
        },
        imageClick:function(no){
        	this.curpage=1;
        	this.ss=this.guList[no];
        	this.getData();
        }
        
        
     }
})
</script>

</body>
</html>