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
<link rel="stylesheet" href="/css/common.css"/>
<link rel="stylesheet" href="/css/top_menu.css"/>
<link rel="stylesheet" href="/css/main.css"/>

</head>

<body>

<!-- 상단 메뉴 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<div class="page-content">
	<div class="main-layout">
		<!-- 왼쪽: 이미지 영역 -->
		<div class="main-left-image">
	    	<img id="slide-img" src="/images/20220818_155357.jpg" alt="학원 내부 이미지">
	    </div>
	    <!-- 오른쪽: 게시판 두 개 -->
		<div class="main-right-board">
		

                <c:forEach var="boards" items="${boardDTOList }" varStatus="status">
					<h5>${boardInfoList[status.index].name }</h5>
					<ul class="main-board-list">
					<c:forEach var="board_item" items="${boards }">
    						<li>
    							<a href="${root}board/read?board_id=${boardInfoList[status.index].board_id}&idx=${board_item.idx }&page=1">[${board_item.idx }] ${board_item.title }</a>
                                <fmt:formatDate value="${board_item.date}" pattern="yyyy-MM-dd" var="formattedDate" />
    		           			<span class="date">${formattedDate }</span>
    		         		</li>
    		         		</c:forEach>
					</ul>
										<div class="main-more-wrap">
		          		<a href="${root}board/main?board_id=${status.index}" class="main-more-link">더보기</a>
		        	</div>
                </c:forEach>
			
							

				</div>
		</div>
	</div>


<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

<!-- 자바스크립트 -->
<script src="/js/background.js"></script>

</body>

</html>