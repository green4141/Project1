<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
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
<link rel="stylesheet" href="/css/style.css"/>

</head>
<body class="page-wrapper">
	
<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
<div class="board-container">
	<div class="board-layout">
	<div class="box">
					<div class="form-block">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" value="${readBoardDTO.username }" disabled="disabled"/>
					</div>
					<div class="form-block">
						<label for="board_date">작성날짜</label>
						<fmt:formatDate value="${readBoardDTO.date}" pattern="yyyy-MM-dd" var="formattedDate" />
                        <input type="text" id="board_date" name="board_date" value="${formattedDate}" disabled="disabled" />
					</div>
                    <div class="form-block">
                      <label for="board_hits">조회수</label>
                      <input type="text" id="board_hits" name="board_hits" value="${readBoardDTO.hits }" disabled="disabled"/>
                    </div>
					<div class="form-block">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" value="${readBoardDTO.title }" disabled="disabled"/>
					</div>
					<div class="form-block">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" rows="10" style="resize:none" disabled="disabled">${readBoardDTO.content }</textarea>
					</div>
                    <c:if test="${readBoardDTO.file != null }">
    					<div class="form-block">
    						<label for="board_file">첨부 이미지</label>
    						<img src="${root}upload/${readBoardDTO.file }" width="100%"/>						
    					</div>
                    </c:if>
					<div class="form-block">
						<div class="text-right">
							<a href="${root }admin/board?page=${page}" class="btn btn-primary">목록보기</a>
                            
							  
							  <a href="${root }admin/delete?idx=${idx}" class="btn btn-danger">삭제하기</a>
                            
						</div>
					</div>
	</div>
</div>
</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>