<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<div class="board-content">
			<h4 class="board-title">게시글 수정</h4>

		<form:form action="${root }board/modifyProcedure" modelAttribute="modifyBoardDTO" method="post" enctype="multipart/form-data">
			<form:hidden path="idx" />
			<form:hidden path="board_id" />
			<input type="hidden" name="page" value="${page }" />
			
			<!-- 작성자 -->

			<div class="form-block">

				<form:label path="username">작성자</form:label>
				<form:input path="username" class="form-control" readonly="true"/>
			</div>
			
			<!-- 작성날짜 -->

			<div class="form-block">

				<form:label path="date">작성날짜</form:label>
				<fmt:formatDate value="${modifyBoardDTO.date}" pattern="yyyy-MM-dd" var="formattedDate"/>
				<input type="text" class="form-control" value="${formattedDate}" readonly="true"/>
			</div>
			
			<!-- 제목 -->

			<div class="form-block">
				<form:label path="title">제목</form:label>
				<form:input path="title" class="form-control"/>
				<span class="error"><form:errors path="title" /></span>
			</div>
			
			<!-- 내용 -->
			<div class="form-block">
				<form:label path="content">내용</form:label>
				<form:textarea path="content" class="form-control" rows="10" style="resize:none" />
				<span class="error"><form:errors path="content" /></span>
			</div>
			
			<!-- 첨부 이미지 -->
			<div class="form-block">
				<form:label path="upload_file">첨부 이미지</form:label>
				<c:if test="${modifyBoardDTO.file != null }">
					<img src="${root }upload/${modifyBoardDTO.file}" width="100%"/>
					<form:hidden path="file" />
				</c:if>
				<form:input type="file" path="upload_file" class="form-control" accept="images/*"/>
			</div>
			
			<!-- 수정완료 -->
			<div class="form-block" style="text-align:right; margin-top:20px;">
				<form:button class="btn btn-primary">수정완료</form:button>
				<a href="${root }board/read?board_id=${board_id}&idx=${idx}&page=${page}" class="btn btn-info">취소</a>
			</div>

		</form:form>
	</div>
</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>