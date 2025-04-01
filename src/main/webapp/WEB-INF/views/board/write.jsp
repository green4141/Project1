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

<style>
    .error { color:red; font-size:12px; font-weight:bold; }
</style>
</head>
<body>

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
<div class="board-container">
	<div class="main-layout">
		<div class="box">
					<form:form action="${root }board/writeProcedure" modelAttribute="writeBoardDTO" method="post" enctype="multipart/form-data">
                    <form:hidden path="board_id" />
					<div class="form-block">
						<form:label path="title">제목</form:label>
						<form:input path="title" class="form-control"/>
                        <span class="error"><form:errors path="title" /></span>
					</div>
					<div class="form-block">
						<form:label path="content">내용</form:label>
						<form:textarea path="content" class="form-control" rows="10" style="resize:none"></form:textarea>
                        <span class="error"><form:errors path="content" /></span>
					</div>
					<div class="form-block">
						<form:label path="upload_file">첨부 이미지</form:label>
						<form:input type="file" path="upload_file" class="form-control" accept="images/*"/>
					</div>
					<div class="form-block">
						<div class="text-right">
							<form:button class="btn btn-primary">작성하기</form:button>
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