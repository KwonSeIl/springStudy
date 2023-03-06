<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px; 
}
.row{
	margin: 0px auto;
	width: 100%;
}
h1{
	text-align: center
}
.ddd:hover{
	cursor: pointer;
}
</style>
</head>
<body>
  <div class="container-fluid">
    <div class="col-sm-8">
      <div class="col-md-3" v-for="vo in jeju_list">
	      <div class="thumbnail">
	        <img :src="vo.poster" style="width: 200px;height: 150px" v-on:click="change">
	        <div class="caption">
	          <p>{{vo.title }}</p>
	        </div>
	      </div>
	    </div>
    </div>
    <div class="col-sm-4">
    <div class="col-sm-5" v-show="isShow">
      <table class="table">
          <tr>
            <td colspan="2">
              <h3>{{vo2.title }}&nbsp;<span style="color:orange">{{vo2.score }}</span></h3>
            </td>
          </tr>
          <tr>
            <th width="10%">주소</th>
            <td width="90%">{{vo2.addr }}</td>
          </tr>
          <tr>
            <th width="10%">전화</th>
            <td width="90%">{{vo2.tel }}</td>
          </tr>
          <tr>
            <th width="10%">음식종류</th>
            <td width="90%">{{vo2.type }}</td>
          </tr>
          <tr>
            <th width="10%">주차</th>
            <td width="90%">{{vo2.parking }}</td>
          </tr>
          <tr>
            <th width="10%">영업시간</th>
            <td width="90%">{{vo2.time }}</td>
          </tr>
          
            <tr v-if="vo2.menu!='no'"> <!-- if문 -->
              <th width="10%">메뉴</th>
              <td width="90%">
                <ul>  
                    <li v-for="m in menus">{{m }}원</li>
                </ul>
              </td>
            </tr>
            
        </table>
        </div>
    </div>
  </div>
  <script>
    new Vue({
    	el:'.container-fluid',
    	data:{
    		jeju_list:[],
    		curpage:1,
    		totalpage:0 /* 아직 정해지지 않았기 때문에 0 */,
    		vo2:{},
    		menus:[],
    		isShow:false
    	},
    	mounted:function(){
    		let _this=this
    		axios.get('http://localhost/web/jeju/jeju_list_vue.do',{
    			params:{
    				page:_this.curpage
    			}
    		}).then(function(response){
    			console.log(response.data)
    			_this.jeju_list=response.data;
    		})
    	},
    	jejuDetail:function(no){
    		this.isShow=true
    		let _this=this
    		axios.get('http://localhost/web/jeju/jeju_detail_vue.do',{
    			params:{
    				no:no
    			}
    		}).then(function(response){
    			console.log(response.data)
    			_this.vo2=response.data;
    			_this.menus=_this.vo2.menu.split("원")
    		})
    	}
    	
    })
  </script>
</body>
</html>