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

<!-- 파비콘 -->
<c:import url="/WEB-INF/views/include/favicon.jsp" />

<!-- 커스텀 CSS 추가 -->
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body>

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<div class="board-container">
	<div class="main-layout">
		<div class="box">
			<form:form action="${root }user/modifyProcedure" modelAttribute="modifyUserDTO" method="post">
				<div class="form-group">
					<form:label path="name">실명</form:label>
					<form:input path="name" readonly="true"/>
				</div>
				
				<div class="form-group">
					<form:label path="username">닉네임</form:label>
					<form:input path="username" readonly="true"/>
				</div>
				
				<div class="form-group">
					<form:label path="username2">신규닉네임</form:label>
					<form:input path="username2"/>
					<span class="error"><form:errors path="username2" /></span>
				</div>
				
				<div class="form-group">
					<form:label path="role">지위</form:label>
					<div>
						<form:radiobutton path="role" value="0" disabled="true" /> 학생
						<form:radiobutton path="role" value="1" disabled="true" /> 교사
					</div>
				</div>
				
				<div class="form-group">
					<form:label path="id">아이디</form:label>
					<form:input path="id" readonly="true"/>
				</div>
				
				<div class="form-group">
					<form:label path="password">비밀번호</form:label>
					<form:password path="password" />
					<span class="error"><form:errors path="password" /></span>
				</div>
				
				<div class="form-group">
					<form:label path="password2">비밀번호 확인</form:label>
					<form:password path="password2" />
					<span class="error"><form:errors path="password2" /></span>
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
