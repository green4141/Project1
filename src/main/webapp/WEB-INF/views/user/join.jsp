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
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
  .error { color:red; font-size:12px; font-weight:bold; }
</style>
<script>
  function checkUserId(){
    let id = $("#id").val();
    
    if(id.length === 0){
      alert("아이디를 입력해 주세요");
      return;
    }
    
    $.ajax({
      url : "${root}user/checkUserId/" + id,
      type : "get",
      dataType : "text",
      success : function(result){
        if(result.trim() === "true"){
          alert("사용할 수 있는 아이디입니다");
          $("#userIdExist").val("true");
        }else{
          alert("이미 존재하는 아이디입니다");
          $("#userIdExist").val("false");
        }
      }
    })
    
  } // checkUserId
  
  function resetUserIdExist(){
  	$("#userIdExist").val("false");
 // resetUserIdExist
 
 
  } 
  
</script>
</head>

<body>

<!-- 상단부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp" ></c:import>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action="${root }user/join_procedure" modelAttribute="joinUserDTO" method="post">
                        <form:hidden path="userIdExist" />
						<div class="form-group">
							<form:label path="username">닉네임</form:label>
							<form:input type="text" path="username" class="form-control"/>
                            <span class="error"><form:errors path="username" /></span>
						</div>
                      <div class="form-group">
                            <form:label path="name">실명</form:label>
                            <form:input type="text" path="name" class="form-control"/>
                            <span class="error"><form:errors path="name" /></span>
                      </div>
                      <div class="form-group">
                          <form:label path="role">역할 선택</form:label>
                          <div>
                              <form:radiobutton path="role" value="0" id="student"/>
                              <label for="student">학생</label>
                      
                              <form:radiobutton path="role" value="1" id="teacher"/>
                              <label for="teacher">교사</label>
                          </div>
                          <span class="error"><form:errors path="role" /></span>
                      </div>
						<div class="form-group">
							<form:label path="id">아이디</form:label>
							<div class="input-group">
								<form:input type="text" path="id" id="id" class="form-control" onkeypress="resetUserIdExist()" />
								<div class="input-group-append">
									<button type="button" class="btn btn-primary" onclick="checkUserId()">중복확인</button>         
								</div>
							</div>
                            <span class="error"><form:errors path="id" /></span>
						</div>
						<div class="form-group">
							<form:label path="password">비밀번호</form:label>
							<form:password path="password" class="form-control"/>
                            <span class="error"><form:errors path="password" /></span>
						</div>
						<div class="form-group">
							<form:label path="password2">비밀번호 확인</form:label>
							<form:password path="password2" class="form-control"/>
                            <span class="error"><form:errors path="password2" /></span>
						</div>
						<div class="form-group">
							<div class="text-right">
								<form:button class="btn btn-primary">회원가입</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
<!-- footer -->  
<c:import url="/WEB-INF/views/include/bottom_info.jsp" ></c:import>

</body>
</html>


