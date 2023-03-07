<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Vue.js -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width: 100%;
}
</style>
</head>
<body>

<div class="container-fluid">
 
     
      <div class="col-sm-6">
      
            <div class="col-md-3" v-for="vo in jeju_list">
             <div class="thumbnail">
               <a href="#">
                 <img :src="vo.poster" style="width:300px;height: 220px" v-on:click="foodDetail(vo.no)">
                 <div class="caption">
                   <p style="font-size: 8px">{{vo.title }}</p>
                 </div>
               </a>
             </div>
           </div>
        
        
         <div style="height: 10px"></div>
        <div class="row">
            <div class="text-center">
              <input type="button" class="btn btn-sm btn-warning" value="이전" @click="prev()">
                {{curpage}}페이지 / {{totalpage}}페이지
              <input type="button" class="btn btn-sm btn-info" value="다음" @click="next()">
            </div>
        </div>
      
       </div>
       
       
     <div class="col-sm-6" v-show="isShow">
     
           <table class="table">
              <tr>
                  <td><img :src="fvo.poster" style="width: 800px;height: 250px" v-on:click="foodDetail(fvo.no)"></td>
              </tr>
          </table>
           
           
           
           <div style="height: 20px"></div>
           <div class="row">
            <div class="col-sm-8">
              <table class="table">
               <tr>
                 <td colspan="2">
                  <h3>{{fvo.title }}&nbsp;<span style="color:orange">{{fvo.score }}</span></h3>
                 </td>
               </tr>
               <tr>
                 <th width=10%>주소</th>
                 <td width=90%>{{fvo.addr }}
                   <br>
                   <sub>지번:{{fvo.addr2 }}</sub>
                 </td>
               </tr>
               <tr>
                 <th width=10%>전화</th>
                 <td width=90%>{{fvo.tel }}</td>
               </tr>
               <tr>
                 <th width=10%>음식종류</th>
                 <td width=90%>{{fvo.type }}</td>
               </tr>
               <tr>
                 <th width=10%>주차</th>
                 <td width=90%>{{fvo.parking }}</td>
               </tr>
               <tr>
                 <th width=10%>영업시간</th>
                 <td width=90%>{{fvo.time }}</td>
               </tr>
              
                  <tr v-if="fvo.menu!='no'">
                    <th width=10%>메뉴</th>
                    <td width=90%>
                     {{fvo.menu}}
                    </td>
                  </tr>
               
            </table>
            </div>
            </div>
            
     
     </div>
  
</div>

<script>
  new Vue({
      el:'.container-fluid',
      data:{
         jeju_list:[],
         fvo:{},
         isShow:false,
           curpage:1,
           totalpage:0,
          menus:[]
      },
      mounted:function(){
         this.send()
         let _this=this
         axios.get('http://localhost/web/jeju/jeju_list_vue.do',{
            params:{
               page:_this.curpage
            }
         }).then(function(response){
            console.log(response.data)
            _this.jeju_list=response.data
         })
      },
      methods:{
         prev:function(){
                this.curpage=this.curpage>1?this.curpage-1:this.curpage;
             this.send();             
             },
             next:function(){
                this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage;
                this.send();
             },
         send:function(){
                 let _this=this;
                 axios.get("http://localhost/web/jeju/jeju_list_vue.do",{
                    params:{
                       page:_this.curpage,
                       addr:_this.addr
                    }
                 }).then(function(response){
                    console.log(response.data)
                    _this.jeju_list=response.data;
                    _this.curpage=response.data[0].curpage;
                    _this.totalpage=response.data[0].totalpage;
                 })
           },
           foodDetail:function(no){
             this.isShow=true
             let _this=this
             //get 방식
              axios.get("http://localhost/web/jeju/jeju_detail_vue.do",{
                 params:{
                    no:no
                 }
              }).then(function(response){ // .then() => Ajax의 succes:function(response)와 동일
                 //response => json
                 console.log(response.data)
                 _this.fvo=response.data
                 
                 //_this.menus=_this.fvo.menu.split("^")
              })
          }
      }
  })
</script>



</body>
</html>