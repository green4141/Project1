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
<link rel="stylesheet" href="css/style.css"/>

</head>

<body>

<!-- 상단 메뉴 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<div class="page-content">
	<div class="main-layout">
		<!-- 왼쪽: 이미지 영역 -->
		<div class="left-image">
	    	<img id="slide-img" src="/images/20220818_155357.jpg" alt="학원 내부 이미지">
	    </div>
	    <!-- 오른쪽: 게시판 두 개 -->
		<div class="right-board">
		
				<!-- 자유게시판 -->
				<div class="board-box">
					<h5>${boardInfoList[0].name }</h5>
					<ul class="board-list">
                        <c:forEach var="boards" items="${boardDTOList }">
    						<li>
    							<a href="${root}board/read?board_id=${boardInfoList[idx.index].board_id}&idx=${boards.idx }&page=1">[${boards.idx }] ${boards.title }</a>
                                <fmt:formatDate value="${boards.date}" pattern="yyyy-MM-dd" var="formattedDate" />
    		           			<span class="date">${formattedDate }</span>
    		         		</li>
                        </c:forEach>
					</ul>
							
					<div class="more-wrap">
		          		<a href="${root}board/main?board_id=0" class="more-link">더보기</a>
		        	</div>
				</div>
		</div>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

<!-- 자바스크립트 -->
<script src="/js/background.js"></script>

</body>
</html>