<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

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

<!-- 게시글 리스트 -->
<div class="page-content">
	<div class="board-container">
		<h4>${name }</h4>
		<table id="board_list">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="boardDTO" items="${boardDTOList }" >
	    			<tr>
	    				<td>${boardDTO.idx }</td>
	    				<td><a href="${root }board/read?board_id=${board_id}&idx=${boardDTO.idx}&page=${page}">${boardDTO.title }</a></td>
	    				<td>${boardDTO.username }</td>
	    				<td><fmt:formatDate value="${boardDTO.date }" pattern="yyyy-MM-dd" /></td>
	    				<td>${boardDTO.hits }</td>
	    			</tr>
	    		</c:forEach>
			</tbody>
		</table>
		
		<!-- 페이지네이션 -->
    	<ul class="page-list">
    	
    		<!-- 이전 버튼 -->
    		<c:choose>
    			<c:when test="${pageDTO.previousPage <= 0 }">
    				<li class="disabled"><span>이전</span></li>
    			</c:when>
    			<c:otherwise>
    				<li><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.previousPage}">이전</a></li>
    			</c:otherwise>
    		</c:choose>
    		
    		<!-- 페이지 숫자 -->
    		<c:forEach var="index" begin="${pageDTO.min }" end="${pageDTO.max }">
    			<c:choose>
    				<c:when test="${index == pageDTO.currentPage }">
    					<li class="active"><span>${index}</span></li>
    				</c:when>
    				<c:otherwise>
    					<li><a href="${root }board/main?board_id=${board_id}&page=${index}">${index}</a></li>
    				</c:otherwise>
    			</c:choose>
    		</c:forEach>
    		
    		<!-- 다음 버튼 -->
    		<c:choose>
    			<c:when test="${pageDTO.max >= pageDTO.pageCount }">
    				<li class="disabled"><span>다음</span></li>
    			</c:when>
    			<c:otherwise>
    				<li><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.nextPage}">다음</a></li>
    			</c:otherwise>
    		</c:choose>
    	</ul>
    	
    	<!-- 글쓰기 버튼 -->
    	<div style="text-align: right; margin-top: 20px;">
    		<a href="${root }board/write?board_id=${board_id}" class="write-button">글쓰기</a>
    	</div>	
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>