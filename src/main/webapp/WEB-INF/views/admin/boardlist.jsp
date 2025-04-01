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
<link rel="stylesheet" href="/css/admin.css"/>

</head>
<body>

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<!-- 게시글 리스트 -->
<div class="board-container">
	<div class="admin-layout">
		<h4 class="admin-title">게시글 목록</h4>
				<table id='admin-list'>
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성날짜</th>
	                        <th>조회수</th>
	                        <th>게시판명</th>
	                        <th>삭제하기</th>
						</tr>
					</thead>
					<tbody>
	                    <c:forEach var="boardDTO" items="${boardDTOList }" >
	    					<tr>
	    						<td >${boardDTO.idx }</td>
	    						<td><a href="${root }admin/read?idx=${boardDTO.idx}&page=${pageDTO.currentPage }">${boardDTO.title }</a></td>
	    						<td>${boardDTO.username }</td>
	    						<td><fmt:formatDate value="${boardDTO.date }" pattern="yyyy-MM-dd" /></td>
	                            <td>${boardDTO.hits }</td>
	                            <td>
		                            <c:choose>
			                            <c:when test="${boardDTO.board_id == 0 }">자유 게시판</c:when>
			                            <c:otherwise>선생님 게시판</c:otherwise>
		                            </c:choose>
	                            </td>
	                            <td><button type="button" onClick="location.href='/admin/delete?idx=${boardDTO.idx}'">삭제하기</button></td>
	    					</tr>
	                    </c:forEach>
					</tbody>
				</table>
				
				<div>
    				<ul class="page-list">
                        <c:choose>
                          <c:when test="${pageDTO.previousPage <= 0 }">
        					<li class="disabled">
        						<a href="${root }admin/board?page=${pageDTO.previousPage}" class="page-link">이전</a>
        					</li>
                          </c:when>
                          <c:otherwise>
        					<li>
        						<a href="${root }admin/board?page=${pageDTO.previousPage}" class="page-link">이전</a>
        					</li>
                          </c:otherwise>
                        </c:choose>
              
                        <c:forEach var="index" begin="${pageDTO.min }" end="${pageDTO.max }">
                          <c:choose>
                            <c:when test="${index == pageDTO.currentPage }">
            					<li class="active">
            						<a href="${root }admin/board?page=${index}" class="page-link">${index}</a>
            					</li>
                            </c:when>
                            <c:otherwise>
            					<li>
            						<a href="${root }admin/board?page=${index}" class="page-link">${index}</a>
            					</li>
                            </c:otherwise>
                          </c:choose>
                        </c:forEach>
                        
                        <c:choose>
                          <c:when test="${pageDTO.max >= pageDTO.pageCount }">
        					<li class="disabled">
        						<a href="${root }admin/board?page=${pageDTO.nextPage}" class="page-link">다음</a>
        					</li>
                          </c:when>
                          <c:otherwise>
        					<li>
        						<a href="${root }admin/board?page=${pageDTO.nextPage}" class="page-link">다음</a>
        					</li>
                          </c:otherwise>
                        </c:choose>
    				</ul>
    			</div>
	</div>
</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>