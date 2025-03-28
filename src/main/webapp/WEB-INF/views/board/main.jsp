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
<body class="page-wrapper">

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<!-- 게시글 리스트 -->
<div class="page-content">
	<div class="container" style="margin-top:100px">
		<h4>${name }</h4>
		<table id='board_list'>
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
	    				<td><a href="${root }board/read?board_id=${board_id}&idx=${boardDTO.idx}">${boardDTO.title }</a></td>
	    				<td>${boardDTO.username }</td>
	    				<td><fmt:formatDate value="${boardDTO.date }" pattern="yyyy-MM-dd" /></td>
	                    <td>${boardDTO.hits }</td>
	    			</tr>
	        	</c:forEach>
			</tbody>
		</table>
		<div>
			<ul>
				<li><a href="#">이전</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">6</a></li>
				<li><a href="#">7</a></li>
				<li><a href="#">8</a></li>
				<li><a href="#">9</a></li>
				<li><a href="#">10</a></li>
				<li><a href="#">다음</a></li>
			</ul>
		</div>
		<div>
			<ul>
				<li><a href="#">이전</a></li>
				<li><a href="#">다음</a></li>
			</ul>
		</div>
		<div style="text-align: right;">
			<a href="${root }board/write?board_id=${board_id}">글쓰기</a>
		</div>		
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>