<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TJOEUN</title>
<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1&family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<!-- 파비콘 -->
<c:import url="/WEB-INF/views/include/favicon.jsp" />

<!-- 커스텀 CSS 추가 -->
<link rel="stylesheet" href="/css/common.css"/>
<link rel="stylesheet" href="/css/top_menu.css"/>
<link rel="stylesheet" href="/css/board.css"/>
<link rel="stylesheet" href="/css/modifyUser.css"/>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


</head>
<body>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
  function checkUserName2(){
    let username = $("#username2").val();

    if(username.length === 0){
      alert("닉네임을 입력해 주세요");
      return;
    }

    $.ajax({
      url : "${root}user/checkUserName/" + username,
      type : "get",
      dataType : "text",
      success : function(result){
        if(result.trim() === "true"){
          alert("사용할 수 있는 닉네임입니다");
          $("#userName2Exist").val("true");
        }else{
          alert("이미 존재하는 닉네임입니다");
          $("#userName2Exist").val("false");
        }
      },
      error: function(){
        alert("서버 통신 오류 발생");
      }
    });
  }

  function resetUserName2Exist(){
    $("#userName2Exist").val("false");
  }
</script>
<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
        <div class="board-container">
        	<div class="board-layout">
        		<div class="box">
					<form:form action="${root }user/modifyProcedure" modelAttribute="modifyUserDTO" method="post">
					<div class="form-group">
						<form:label path="name">실명</form:label>
						<form:input path="name" class="form-control" readonly="true"/>
					</div>
                    <div class="form-group">
                      <form:label path="username">닉네임</form:label>
                      <form:input path="username" class="form-control" readonly="true"/>
                    </div>
                    <div class="form-group">
                    <form:label path="username2">신규닉네임</form:label>
                    <div class="input-group">
                      <form:input path="username2" id="username2" class="form-control" onkeypress="resetUserName2Exist()" />
                      <div class="input-group-append">
                        <button type="button" class="btn btn-primary" onclick="checkUserName2()">중복확인</button>         
                      </div>
                    </div>
                    <span id="error"><form:errors path="username2" /></span>
                  </div>
                  <form:hidden path="userName2Exist" />
                    <div class="form-group">
                        <form:label path="role">지위</form:label>
                        <div>
                            <form:radiobutton path="role" value="0" disabled="true" /> 학생
                            <form:radiobutton path="role" value="1" disabled="true" /> 교사
                        </div>
                    </div>
					<div class="form-group">
						<form:label path="id">아이디</form:label>
						<form:input path="id" class="form-control" readonly="true"/>
					</div>
					<div class="form-group">
						<form:label path="password">비밀번호</form:label>
						<form:password path="password" class="form-control" />
						<span id="error"><form:errors path="password" /></span>
					</div>
					<div class="form-group">
						<form:label path="password2">비밀번호 확인</form:label>
						<form:password path="password2" class="form-control"/>
						<span id="error"><form:errors path="password2" /></span>
					</div>
					<div class="form-group">
						<div class="text-right">
							<form:button class="btn btn-primary">정보수정</form:button>
						</div>
					</div>
					</form:form>
				</div>
			</div>
		</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>