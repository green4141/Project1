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
<link rel="stylesheet" href="/css/login.css"/>
</head>
<body>

<!-- 상단부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp" ></c:import>
<div class="board-container">
	<div class="board-layout">
		<div class="box">
                    <c:if test="${fail == true }" >
    					<div class="alert alert-danger">
    						<h3>로그인 실패</h3>
    						<p>아이디 비밀번호를 확인해주세요</p>
    					</div>
                    </c:if>
					<form:form action="${root }user/loginProcedure" modelAttribute="loginProcUserDTO" method="post">
						<div class="form-block">
							<form:input path="id" class="form-control" placeholder="아이디"/>
                            <span id="error"><form:errors path="id" /></span>
						</div>
						<div class="form-block">
							<form:password path="password" class="form-control" placeholder="비밀번호"/>
                            <span id="error"><form:errors path="password" /></span>
						</div>
						<div class="form-block login-buttons">
							<form:button class="btn btn-primary">로그인</form:button>
							<a href="${root }user/join" class="btn btn-danger">회원가입</a>
						</div>
					</form:form>
				</div>
			</div>
		</div>

<!-- footer -->  
<c:import url="/WEB-INF/views/include/bottom_info.jsp" ></c:import>


</body>
</html>