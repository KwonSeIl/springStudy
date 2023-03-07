<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width:1200px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h3 class="text-center" style="color:gray">${vo.title }</h3>
    <div class="row">
      <table class="table">
        <tr>
          <td><img src="${vo.poster }" style="width: 700px;height:250px "></td>
        </tr>
        <tr>
          <td>${vo.address }</td>
        </tr>
        <tr>
          <td>${vo.msg }</td>
        </tr>
        <tr>
          <td>조회수:${vo.hit }</td>
        </tr>
      </table>
    </div>
    <div style="height: 10px;"></div>
    <div class="row">
     
    </div>
    <div style="height: 10px;"></div>
    <h3>인근 맛집</h3>
    <hr>
    <div class="row">
      <c:forEach var="fvo" items="${list }">
        <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="#">
		        <img src="${fvo.poster }" alt="Lights" style="width:200px;height: 150px;">
		        <div class="caption">
		          <p>${fvo.name }</p>
		        </div>
		      </a>
		    </div>
		  </div>
      </c:forEach>
    </div>
  </div>
</body>
</html>