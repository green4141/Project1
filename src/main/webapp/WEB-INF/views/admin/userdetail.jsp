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
</head>

<body>

<!-- 상단부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp" ></c:import>

<div class="board-container">
	<div class="board-layout">
		<div class="box">
			<form:form action="${root }admin/updateproc" modelAttribute="joinUserDTO" method="post">
	                     <form:hidden path="userIdExist" />
	                     <form:hidden path="idx" value="${user.idx }"/>
				<div class="form-group">
					<form:label path="username">닉네임</form:label>
					<form:input type="text" path="username" class="form-control" readonly="true" value="${user.username }"/>
	                         <span class="error"><form:errors path="username" /></span>
				</div>
	                   <div class="form-group">
	                         <form:label path="name">실명</form:label>
	                         <form:input type="text" path="name" class="form-control" readonly="true" value="${user.name }"/>
	                         <span class="error"><form:errors path="name" /></span>
	                   </div>
	                   <div class="form-group">
	                       <form:label path="role">역할 선택</form:label>
	                       <div>
	                           <form:radiobutton path="role" value="0" id="student" checked="${user.role == 0? 'checked':'' }"/>
	                           <label for="student">학생</label>
	                   
	                           <form:radiobutton path="role" value="1" id="teacher" checked="${user.role == 1? 'checked':'' }"/>
	                           <label for="teacher">선생님</label>
	                           <form:radiobutton path="role" value="2" id="teacher" checked="${user.role == 2? 'checked':'' }"/>
	                           <label for="teacher">관리자</label>
	                       </div>
	                       <span class="error"><form:errors path="role" /></span>
	                   </div>
				<div class="form-group">
					<form:label path="id">아이디</form:label>
					<div class="input-group">
						<form:hidden path="id" id="id" value="${user.id }"/>
						<span>${user.id }</span>
					<!-- 	<div class="input-group-append">
							<button type="button" class="btn btn-primary" onclick="checkUserId()">중복확인</button>         
						</div> -->
					</div>
	                        <!--  <span class="error"><form:errors path="id" /></span> -->
				</div>
				<div class="form-group">
					<form:label path="password">비밀번호</form:label>
					<form:password path="password" class="form-control"/>
	                <span id="error"><form:errors path="password" /></span>
				</div>
				<div class="form-group">
					<form:label path="password2">비밀번호 확인</form:label>
					<form:password path="password2" class="form-control"/>
	                <span id="error"><form:errors path="password2" /></span>
				</div>
				<div class="form-group">
					<div class="text-right">
						<form:button class="btn btn-primary">수정하기</form:button>
                        <button class="btn btn-primary" type="button" onclick="location.href='/admin/user?page=${param.page}&id=${param.id}&name=${param.name}&username=${param.username}&role=${param.role}&sort=${param.sort}&order=${param.order}'">목록으로</button>
				</div>
            </div>
			</form:form>
		</div>
	</div>
</div>
<!-- footer -->  
<c:import url="/WEB-INF/views/include/bottom_info.jsp" ></c:import>


</body>
</html>

