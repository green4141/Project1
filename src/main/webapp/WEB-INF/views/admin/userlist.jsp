<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="board-container">
	<div class="board-layout">
				<h4 class="board-title">${name }</h4>
				<table id="board-list">
					<thead>
						<tr>
							<th>번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>닉네임</th>
							<th>등급</th>
							<th>수정하기</th>
							<th>삭제하기</th>
						</tr>
					</thead>
					<tbody>
	                    <c:forEach var="userDTO" items="${userList }" >
	    					<tr>
	    						<td>${userDTO.idx }</td>
	    						<td>${userDTO.id }</td>
	    						<td>${userDTO.name }</td>
	    						<td>${userDTO.username }</td>
	                            <td>
	                            <c:choose>
	                            	<c:when test="${userDTO.role eq 0 }">학생</c:when>
	                            	<c:when test="${userDTO.role eq 1 }">선생님</c:when>
	                            	<c:otherwise>관리자</c:otherwise>
	                            </c:choose>
	                            </td>
	                            <td><button type="button" class="btn btn-info" onclick="location.href='/admin/userdetail?idx=${userDTO.idx }'">수정하기</button></td>
	                            <td><button type="button" class="btn btn-danger" onclick="location.href='/admin/userdelete?idx=${userDTO.idx }'">삭제하기</button></td>
	    					</tr>
	                    </c:forEach>
					</tbody>
				</table>
				
					<ul class="page-list">
	
						<c:choose> 
	                      <c:when test="${pageDTO.previousPage <= 0 }">   
						    <li class="disabled">
							  <a href="${root }admin/user?page=${pageDTO.previousPage}">이전</a>
						    </li>
	                        </c:when>
	                      <c:otherwise>
	                        <li>
	                          <a href="${root }admin/user?page=${pageDTO.previousPage}">이전</a>
	                        </li>
	                      </c:otherwise>
	                    </c:choose>
	                    <c:forEach var="idx" begin="${pageDTO.min }" end="${pageDTO.max }">
	                    
	                      <c:choose>
	                        <c:when test="${idx == pageDTO.currentPage }">
	    				      <li class="active">
	    					    <a href="${root }admin/user?page=${idx}">${idx}</a>
	    				      </li>
	                        </c:when>
	                        <c:otherwise>
	    				      <li>
	    				   	    <a href="${root }admin/user?page=${idx}">${idx}</a>
	    				      </li>
	                        </c:otherwise>
	                      </c:choose>
	                    </c:forEach>
						
						<c:choose> 
	                      <c:when test="${pageDTO.max >= pageDTO.pageCount }">   
						    <li class="disabled">
							  <a href="${root }admin/user?page=${pageDTO.nextPage}">다음</a>
						    </li>
	                        </c:when>
	                      <c:otherwise>
	                        <li>
	                          <a href="${root }admin/user?page=${pageDTO.nextPage}">다음</a>
	                        </li>
	                      </c:otherwise>
	                    </c:choose>
					</ul>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>