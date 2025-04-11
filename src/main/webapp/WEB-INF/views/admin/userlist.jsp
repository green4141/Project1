<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<c:set var="nextNameOrder" value="${sort eq 'name' and order eq 'asc' ? 'desc' : sort eq 'name' and order eq 'desc' ? '' : 'asc'}"/>
<c:set var="nextRoleOrder" value="${sort eq 'role' and order eq 'asc' ? 'desc' : sort eq 'role' and order eq 'desc' ? '' : 'asc'}"/>

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
    <link rel="stylesheet" href="/css/admin_search.css"/>
	<script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
	
	<script src="${root }js/adminuserlist.js"></script>
</head>
<body>

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>

<!-- 게시글 리스트 -->
<div class="board-container">
	<div class="admin-layout">
		<h4 class="admin-title">유저 목록</h4>
		<table id='admin-list'>
			<thead>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th id=sorted-link>
						<a href="${root}admin/user?page=${pageDTO.currentPage}&sort=name&order=${nextNameOrder}&id=${id}&name=${name}&username=${username}&search_role=${search_role}">이름</a>
						<c:if test="${sort eq 'name'}">
							<c:choose>
								<c:when test="${order eq 'asc'}">▲</c:when>
								<c:when test="${order eq 'desc'}">▼</c:when>
							</c:choose>
						</c:if>
					</th>
					<th>닉네임</th>
					<th id=sorted-link>
						<a href="${root}admin/user?page=${pageDTO.currentPage}&sort=role&order=${nextRoleOrder}&id=${id}&name=${name}&username=${username}&search_role=${search_role}">등급</a>
						<c:if test="${sort eq 'role'}">
							<c:choose>
								<c:when test="${order eq 'asc'}">▲</c:when>
								<c:when test="${order eq 'desc'}">▼</c:when>
							</c:choose>
						</c:if>
					</th>
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
                          <td><button type="button" onclick="location.href='/admin/userdetail?idx=${userDTO.idx }&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}'">수정하기</button>
                          <td><button type="button" onclick="location.href='/admin/userdelete?idx=${userDTO.idx }&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}'">삭제하기</button>
  					</tr>
                  </c:forEach>
			</tbody>
		</table>
		<div class="pagination-and-write">
			<ul class="page-list">
				<c:choose> 
                    <c:when test="${pageDTO.previousPage <= 0 }">   
					    <li class="disabled">
						  <a href="${root }admin/user?page=${pageDTO.previousPage}&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}">이전</a>
					    </li>
                      </c:when>
                    <c:otherwise>
                        <li>
                          <a href="${root }admin/user?page=${pageDTO.previousPage}&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}">이전</a>
                        </li>
                    </c:otherwise>
                 </c:choose>
                 <c:forEach var="idx" begin="${pageDTO.min }" end="${pageDTO.max }">
	                   <c:choose>
		                     <c:when test="${idx == pageDTO.currentPage }">
			 				      <li class="active">
			 					    <span>${idx}</span>
			 				      </li>
		                     </c:when>
		                     <c:otherwise>	
			 				      <li>
			 				   	    <a href="${root }admin/user?page=${idx}&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}">${idx}</a>
			 				      </li>
		                     </c:otherwise>
	                   </c:choose>
                 </c:forEach>
				
				<c:choose> 
                     <c:when test="${pageDTO.max >= pageDTO.pageCount }">   
					    <li class="disabled">
						  <a href="${root }admin/user?page=${pageDTO.nextPage}&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}">다음</a>
					    </li>
                     </c:when>
                     <c:otherwise>
                       <li>
                         <a href="${root }admin/user?page=${pageDTO.nextPage}&id=${id}&name=${name}&username=${username}&search_role=${search_role}&sort=${sort}&order=${order}">다음</a>
                       </li>
                     </c:otherwise>
                </c:choose>
			</ul>
			<!-- 검색 -->
	    	<div class="search">
				<select id="searchfield">
					<option value="id" selected>아이디</option>
					<option value="name">이름</option>
					<option value="username">닉네임</option>
					<option value="search_role">등급</option>
				</select> 
				<input type="text" id="search"/>
				<select id="search_role" style="display:none;">
					<option value="0">학생</option>
					<option value="1">선생님</option>
					<option value="2">관리자</option>
				</select>
				<button type="button" id="search-btn">검색</button>
	    	</div>
		</div>
	</div>
</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>