<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	1. 생명주기
	2. 디렉티브
		v-if / v-else / v-for / v-show / v-hide / v-bind / v-model...(제어문)
	3. 외부데이터 읽어서 제어 : axios 이용
	4. 컴포넌트 제작 방법
	------ 이벤트 처리 v-on:click
	5. 기타 속성
	6. 라우터
	
	7. 배포 ..(javascript를 따로 제작) => 웹스톰(NodeJS)
	----------------------------------------------- VueJS / vuex / vue3 (react)
	
	====================> new Vue({}) => 인스턴스/객체
	생명주기 함수
	beforeCreate() : 인스턴스 초기화 전(생성 전)
	created() : 객체 생성 => 메모리 할당
	beforeMount() : 가상돔(임시 저장장소) => HTML을 올리기 전 상태
	mounted() : HTML이 올라가서 브라우저 출력 ====================> window.onload
	beforeUpdate() : 수정 전 상태
	updated() : 수정 완료
	beforeDestory() : 페이지 이동 전 상태
	destoryed() : 종료
	
	=======================> state / props(변경이 안되는 변수) ==> 속성값
							------- 유지 변수(변경이 가능)
	new Vue({
		el: ==> 제어하는 영역(CSS) => id="#", class="."
		data:{
			멤버변수 => 종료시까지 유지하는 변수
		}
	})
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px; 
}
.row{
	margin: 0px auto;
	width: 800px;
}
h1{
	text-align: center
}
</style>
</head>
<body>
  <div class="container">
    <h1>Vue 생명주기</h1>
    <div class="row">
      <input type="text" size=30 class="input-sm" v-model="message"> <!-- view가 갖고있는 변수와 v-model이 가진 변수가 일치 -->
      <h3>{{message}}</h3>
    </div>
  </div>
  <script>
    new Vue({
    	el:'.container',
    	data:{
    		message:'Hello VueJS'
    	},
    	// 생명주기 함수
    	beforeCreate:function(){
    		console.log("beforeCreate Call..: 인스턴스 초기화 전: 이벤트 등록")
    		// componentWillCreate()
    	},
    	created:function(){
    		console.log("created Call..: 인스턴스 생성 완료: 메모리 할당 완료")
    		// componentDidCreate()
    	},
    	beforeMount:function(){
    		console.log("beforeMount Call..: HTML을 저장할 가상 메모리 확인: 서버에서 데이터 읽기 = 변수")
    		// componentWillMount()
    	},
    	mounted:function(){
    		console.log("mounted Call..: HTML을 저장할 가상 메모리 저장 완료: 실제 돔 비교 => 브라우저에 출력")
    		// componentDiMount()
    		// 다른 라이브러리 연동 => Ajax, Jquery .. => $(function(){})
    	},
    	beforeUpdate:function(){
    		console.log("beforeUpdate Call..: 수정 전 상태: 메모리 할당 완료")
    		// componentWillUpdate()
    	},
    	updated:function(){
    		console.log("updated Call..: 수정 완료 => mounted로 다시 넘어감")
    	},
    	beforeDestory:function(){
    		console.log("beforeDestory Call..: 메모리 해제 전 상태")
    	},
    	destoryed:function(){
    		console.log("destoryed Call..: 메모리 해제 상태")
    	}
    })
  </script>
</body>
</html>