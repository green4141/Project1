<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<c:set var="nextHitsOrder" value="${sort eq 'hits' and order eq 'asc' ? 'desc' : sort eq 'hits' and order eq 'desc' ? '' : 'asc'}"/>
<c:set var="nextFavoriteOrder" value="${sort eq 'favorite' and order eq 'asc' ? 'desc' : sort eq 'favorite' and order eq 'desc' ? '' : 'asc'}"/>

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
        <link rel="stylesheet" href="/css/search.css"/>

		<script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
		<script src="${root }js/board.js"></script>
	</head>
	<body>
	
	<!-- 상단 부분 -->
	<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
	
	<!-- 게시글 리스트 -->
	
	
	<div class="board-container">
		<div class="board-layout">
			<h4 class="board-title">${name }</h4>
			<table id="board-list">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성날짜</th>
    					<th id=sorted-link>
    						<a href="${root}board/main?board_id=${board_id}&page=${page}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=hits&order=${nextHitsOrder}">조회수</a>
    						<c:if test="${sort eq 'hits'}">
    							<c:choose>
    								<c:when test="${order eq 'asc'}">▲</c:when>
    								<c:when test="${order eq 'desc'}">▼</c:when>
    							</c:choose>
    						</c:if>
    					</th>
    					<th id=sorted-link>
    						<a href="${root}board/main?board_id=${board_id}&page=${page}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=favorite&order=${nextFavoriteOrder}">좋아요</a>
    						<c:if test="${sort eq 'favorite'}">
    							<c:choose>
    								<c:when test="${order eq 'asc'}">▲</c:when>
    								<c:when test="${order eq 'desc'}">▼</c:when>
    							</c:choose>
    						</c:if>
    					</th>
					</tr>
				</thead>
				<tbody>
					<!-- 공지사항 출력 -->
					<c:forEach var="notice" items="${topNotices}">
						<tr class="notice-row">
							<td>${notice.idx}</td>
							<td>
								<a href="${root}board/read?board_id=${board_id}&idx=${notice.idx}&page=1">
								<strong style="color: crimson;">[공지] ${notice.title}</strong>
								</a>
							</td>
							<td>${notice.username}</td>
							<td><fmt:formatDate value="${notice.date}" pattern="yyyy-MM-dd" /></td>
							<td>${notice.hits}</td>
							<td>${notice.favorite}</td>
						</tr>
					</c:forEach>
					<c:forEach var="boardDTO" items="${boardDTOList }" >
		    			<tr>
		    				<td>${boardDTO.idx }</td>
		    				<td><a href="${root}board/read?board_id=${board_id}&idx=${boardDTO.idx}&page=${page}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}">${boardDTO.title }</a></td>
		    				<td>${boardDTO.username }</td>
		    				<td><fmt:formatDate value="${boardDTO.date }" pattern="yyyy-MM-dd" /></td>
		    				<td>${boardDTO.hits }</td>
	                        <td>${boardDTO.favorite }</td>
		    			</tr>
		    		</c:forEach>
				</tbody>
			</table>
	
			<!-- 페이지네이션 -->
			<div class="pagination-and-write">
		    	<ul class="page-list">	    	
		    		<!-- 이전 버튼 -->
		    		<c:choose>
		    			<c:when test="${pageDTO.previousPage <= 0 }">

		    				<li class="disabled"><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.previousPage}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}">이전</a></li>
		    			</c:when>
		    			<c:otherwise>
		    				<li><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.previousPage}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}">이전</a></li>
		    			</c:otherwise>
		    		</c:choose>
		    		
		    		<!-- 페이지 숫자 -->
		    		<c:forEach var="index" begin="${pageDTO.min }" end="${pageDTO.max }">
		    			<c:choose>
		    				<c:when test="${index == pageDTO.currentPage }">
		    					<li class="active"><span>${index}</span></li>
		    				</c:when>
		    				<c:otherwise>
		    					<li onclick="location.href='${root }board/main?board_id=${board_id}&page=${index}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}'">${index }</li>
		    				</c:otherwise>
		    			</c:choose>
		    		</c:forEach>
		    		
		    		<!-- 다음 버튼 -->
		    		<c:choose>
		    			<c:when test="${pageDTO.max >= pageDTO.pageCount }">
		    				<li class="disabled"><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.nextPage}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}">다음</a></li>
		    			</c:when>
		    			<c:otherwise>
		    				<li><a href="${root }board/main?board_id=${board_id}&page=${pageDTO.nextPage}&title=${title}&username=${username}&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}">다음</a></li>
		    			</c:otherwise>
		    		</c:choose>
		    	</ul>
			</div>
		
	   		<!-- 검색 -->
	    	<div class="search">
				<select id="searchfield">
					<option value="title" selected>제목</option>
					<option value="username">작성자</option>
					<option value="date">작성일</option>
				</select> 
				<input type="text" id="search"/>
				<input type="date" id="startdate" style="display: none;"/><input type="date" id="enddate" style="display: none;"/>
				<button type="button" id="search-btn">검색</button>
	    	</div>
	  	
	    	<!-- 글쓰기 버튼 -->
	    	<div class="write-button-div" >
	    		<a href="${root }board/write?board_id=${board_id}" class="write-button">글쓰기</a>
	    	</div>	
		</div>    
	</div>	
	
	<!-- footer -->
	<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>
	
	</body>
</html>