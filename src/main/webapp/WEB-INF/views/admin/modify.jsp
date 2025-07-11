<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
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

</head>
<body>

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<div class="board-container">
	<div class="board-layout">
		<div class="box">
			<form action="modify_user.html" method="post">
				<div class="form-block">
					<label for="user_name">이름</label>
					<input type="text" id="user_name" name="user_name" value="홍길동" disabled="disabled"/>
				</div>
				<div class="form-block">
					<label for="user_id">아이디</label>
					<input type="text" id="user_id" name="user_id" value="abc" disabled="disabled"/>
				</div>
				<div class="form-block">
					<label for="user_pw">비밀번호</label>
					<input type="password" id="user_pw" name="user_pw" value="1234"/>
				</div>
				<div class="form-block">
					<label for="user_pw2">비밀번호 확인</label>
					<input type="password" id="user_pw2" name="user_pw2" value="1234"/>
				</div>
				<div class="form-block">
					<div class="text-right">
						<button type="submit" class="btn btn-primary">정보수정</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>