<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top:50px;
}
.row{
   width: 800px;
   margin: 0px auto;
}
h1{
   text-align: center;
}
#chatArea{
   height: 450px;
   overflow-y:auto;
   border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
function connection(){
   //웹 소캣 생성
   websocket=new WebSocket("ws://localhost/web/site/chat/chat-ws") //ChatServerdml serverEndPoint와 동일해야함
   websocket.onopen=onOpen;//callback함수
   websocket.onclose=onClose;
   websocket.onmessage=onMessage;
   name=$('#startBtn').attr("data-name")
}
function onOpen(event){
   alert("채팅 서버 접속")
}
function onClose(event){
   alert("채팅 서버 접속 해제")
}
function onMessage(event){
   let data=event.data;
   if(data.substring(0,4)=="msg:")//명령어가 roomin => 방들어가기 makeroom => 방만들기 roomout => 방나가기 만듦 ==> 하나씩 따져봐야함
   {
      appendMessage(data.substring(4));   
   }
}
function disConnection(){
   websocket.close(); //onClose() 호출 -> 종료
}
//서버로 채팅문자열 전송
function send(){
   let msg=$('#sendMsg').val()
   if(msg.trim()=="")
   {
      $('#sendMsg').focus();
      return 
   }
   //서버로 전송
   websocket.send("msg:["+name+"]"+msg); //onMessage
   $('#sendMsg').val("");
   $('#sendMsg').focus();
}
//서버로부터 채팅 문자열 받았을 때 처리
function appendMessage(msg){
   $('#recvMsg').append(msg+"<br>")
   let ch=$('#chatArea').height()
   let m=$('#recvMsg').height()-ch;
   $('#chatArea').scrollTop(m);//출력하는 위치에 스크롤 출력
}
//제어
$(function(){
   $('#startBtn').click(function(){//입력 enter눌렀을 때 처리
      connection();//keycode=13 => enter
   })
   $('#endBtn').click(function(){
      disConnection();
   })
   $('#sendBtn').click(function(){
      send();
   })
   $('#sendMsg').keydown(function(key){
      if(key.keyCode==13)
      {
         send()
      }
   })
})
</script>
</head>
<body>
   <div class="container">
     <h1>WebSocket 채팅</h1>
     <div class="row">
      <table class="table">
        <tr>
          <td>
            <input type="button" id="startBtn" value="입장" class="btn btn-sm btn-danger" data-name="${sessionScope.name }">
            <input type="button" id="endBtn" value="퇴장" class="btn btn-sm btn-primary">
            <a href="../main/main.do" class="btn btn-sm btn-warning">메인</a>
          </td>
        </tr>
        <tr>
          <td>
            <div id="chatArea">
              <div id="recvMsg"></div>
            </div>
          </td>
        </tr>
        <tr>
          <td>
            <p>[${sessionScope.name }]&nbsp;<input type=text id="sendMsg" size=105 class="input-sm">
            <input type=button id="sendBtn" value="전송" class="btn btn-sm btn-success"></p>
          </td>
        </tr>
      </table>
     </div>
   </div>
</body>
</html>