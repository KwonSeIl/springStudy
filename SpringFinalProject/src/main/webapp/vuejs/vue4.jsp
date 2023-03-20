<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
denamepackage com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface ClassMapper {
	
	/*
	 * 	CNO                   NOT NULL NUMBER        
		TITLE                 NOT NULL VARCHAR2(300) 
		IMAGE                          VARCHAR2(4000) 
		PLACE                          VARCHAR2(4000) 
		LOCATION                       VARCHAR2(4000) 
		SCHEDULE                       CLOB          
		NOTICE                         CLOB          
		TIME                           VARCHAR2(100) 
		PERPRICE              NOT NULL VARCHAR2(150) 
		TOTALPRICE            NOT NULL VARCHAR2(200) 
		JJIM_COUNT                     NUMBER        
		CATENO                         NUMBER        
		TNO                            NUMBER        
		DETAIL_CATENO                  NUMBER        
		SUMMARY                        CLOB          
		TARGET                         CLOB          
		TUTOR_INTRO                    CLOB          
		CLASS_INTRO                    CLOB          
		CLASS_CURRI                    CLOB          
		CLASS_VIDEO                    CLOB          
		ONOFF                          VARCHAR2(150) 
		INWON                          VARCHAR2(30)  
		TUTOR_INFO_NICKNAME           VARCHAR2(100) 
		TUTOR_INFO_IMG                 VARCHAR2(500) 
		TUTOR_INFO_GRADE_TOTAL          NUMBER(2,1)   
	 */
	@Select("SELECT cateno,catename FROM ch_category_2_3")
	public List<CategoryVO> classCateData();
	@Select("SELECT cateno,detail_cateno,detail_catename FROM ch_category_detail_2_3 "
			+ "WHERE cateno=#{cateno}")
	public List<CategoryDetailVO> classCateDetailData(int cateno);
	
	@Select("SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname,num "
			+ "FROM (SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname,rownum as num "
			+ "FROM (SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname "
			+ "FROM ch_classdetail_2_3 where cateno=#{cateno} and detail_cateno=#{detail_cateno})) "
			+ "WHERE num between #{start} and #{end}")
	public List<ClassDetailVO> classListData(Map map);
	
	@Select("SELECT cno,title,image,place,location,schedule,notice,time,perprice,totalprice, "
	         + "summary,target,tutor_intro,class_intro,class_curri,class_video,onoff,inwon,tutor_info_nickname,tutor_info_img, "
	         + "jjim_count FROM CH_CLASSDETAIL_2_3 "
	         + "WHERE cno=#{cno}")
	 public ClassDetailVO classDetailData(int cno);
	
	@Select("SELECT CEIL(COUNT(*)/16.0) FROM ch_classdetail_2_3")
	public int classTotalPage();
	
	@Select("SELECT COUNT(*) FROM ch_classdetail_2_3 "
			+ "WHERE cateno=#{cateno} AND detail_cateno=#{detail_cateno}") 
	public String classRowCount(Map map);
	
	//검색
	@Select("SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname,rownum"
			+ "FROM (SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname "
			+ "FROM ch_classdetail_2_3 where title LIKE '%'||#{find}||'%' "
			+ "WHERE rownum between #{start} and #{end}")
	public List<ClassDetailVO> classFindList(Map map);
	
	//검색결과 총개수
	@Select("SELECT COUNT(*) FROM (SELECT cno,title,image,location,perprice,jjim_count,cateno,detail_cateno,onoff,tutor_info_nickname "
			+"FROM ch_classdetail_2_3) "
			+"WHERE title LIKE '%'||#{find}||'%'")
	public int classFindCount();
	
	//검색결과 총페이지
	@Select("SELECT CEIL(count(*)/16.0) FROM ch_classdetail_2_3 "
			+"WHERE title LIKE '%'||#{find}||'%'")
	public int classFindTotalPage(String find);
	

//	@Select("SELECT rno,content,heart,comment_count,curriculum,preparation,kindness,delivery,time,id,cno,TO_CHAR(regdate,'YYYY-MM-DD') as dbday "
//			+"FROM (SELECT /*+(ch_review_2_3 ch_rv_rno_pk_2_3)*/rno,content,heart,comment_count,curriculum,preparation,kindness,delivery,time,id,cno,regdate "
//			+"FROM ch_review_2_3) "
//			+"WHERE cno=#{cno}")
//	public List<ReviewVO> classReview(int cno);
}

:<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width: 1300px;
}
h1{
   text-align: center;
}
.images:hover{
   cursor:pointer;
}
</style>
</head>
<body>
   <div class="container">
     <h3>믿고보는 맛집 리스트</h3>
     <div class="row">
       <category1 v-bind:cate1="cate_list1"></category1>
     </div>
     <div style="height: 20px"></div>
     <h3>지역별 맛집 리스트</h3>
     <div class="row">
       <category2 v-bind:cate2="cate_list2"></category2>
     </div>
     <div style="height: 20px"></div>
     <h3>메뉴별 맛집 리스트</h3>
     <div class="row">
       <category3 v-bind:cate3="cate_list3"></category3>
     </div>
     
     <b-modal ref="my-modal" hide-footer title="category_info.title" id="modal-lg" size="lg">
        <div class="text-center">
           <table class="table">
            <tbody>
             <tr>
               <td>
                 <table class="table" v-for="vo in food_list">
                   <tr>
                     <td width=30% class="text-center" rowspan="4"></td>
                     <td width=70%><h3>{{vo.name}} &nbsp; <span style="color:orange">{{vo.score}}</span></h3></td>
                   </tr>
                   <tr>
                     <td width=70%>{{vo.address}}</td>
                   </tr>
                   <tr>
                     <td width=70%>{{vo.tel}}</td>
                   </tr>
                   <tr>
                     <td width=70%>{{vo.type}}</td>
                   </tr>
                 </table>
               </td>
             </tr>
            </tbody>
           </table>
        </div>
      </b-modal>
   </div>
   
<script>
   let eventBus=new Vue();
   Vue.component('category1',{
      props:['cate1'],
      template:'<div><div class="col-md-3" v-for="vo in cate1">'
                +'<div class="thumbnail">'
                +'<img :src="vo.poster" alt="Lights" style="width:100%" class="images">'
                +'<div class="caption">'
                +'<p>{{vo.title}}</p>'
                +'<p><button class="btn btn-sm btn-danger" v-on:click="showFoodList(vo.cno,true)">맛집보기</button></p>'
                +'</div>'
                +'</div>'
                +'</div></div>',
      methods:{
         showFoodList:function(cno,bool){
            eventBus.$emit("showFoodListEvent",cno,bool)
         }
      }
   }),
   Vue.component('category2',{
      props:['cate2'],
      template:'<div><div class="col-md-4" v-for="vo in cate2">'
             +'<div class="thumbnail">'
             +'<img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="showFoodList(vo.cno,true)>'
             +'<div class="caption">'
             +'<p>{{vo.title}}</p>'
             +'</div>'
             +'</div>'
             +'</div></div>',
           methods:{
            showFoodList:function(cno,bool){
               eventBus.$emit("showFoodListEvent",cno,bool)
            }
         }
   })
   Vue.component('category3',{
      props:['cate3'],
      template:'<div><div class="col-md-3" v-for="vo in cate3">'
             +'<div class="thumbnail">'
             +'<img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="showFoodList(vo.cno,true)>'
             +'<div class="caption">'
             +'<p>{{vo.title}}</p>'
             +'</div>'
             +'</div>'
             +'</div></div>',
           methods:{
            showFoodList:function(cno,bool){
               eventBus.$emit("showFoodListEvent",cno,bool)
            }
         }
   })
   new Vue({
      el:'.container',
      data:{
         cate_list1:[],
         cate_list2:[],
         cate_list3:[],
         category:{},
         food_list:[],
         category_info:{}
      },
      mounted:function(){
         let _this=this;
         axios.get("http://localhost/web/food/food-category_vue.do").then(function(response){
            console.log(response.data)
            _this.category=response.data;
            _this.cate_list1=_this.category.cate1
            _this.cate_list2=_this.category.cate2
            _this.cate_list3=_this.category.cate3
         })
      },
      updated:function(){
         let_this=this
         eventBus.$on('showFoodListEvent',function(cno,bool){
            axios.get('http://localhost/web/food/category_info_vue.do',{
               params:{
                  cno:cno
               }
            }).then(function(response){
               console.log(response.data)
               this.category_info=response.data
            })
            
            axios.get('http://localhost/web/food/food_list_vue.do',{
               params:{
                  cno:cno
               }
            }).then(function(response){
               this.food_list=response.data
            })
            
         })
         this.showModal();
      },
      methods:{
         showModal:function(){
            this.$refs['my-modal'].show()
         }
      }
   })
</script>
</body>
</html>