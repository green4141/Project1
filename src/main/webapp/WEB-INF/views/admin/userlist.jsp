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
<div class="container" style="margin-top:100px">
	<div class="card shadow">
		<div class="card-body">
			<h4 class="card-title">${name }</h4>
			<table class="table table-hover" id='board_list'>
				<thead>
					<tr>
						<th class="text-center d-none d-md-table-cell">번호</th>
						<th class="text-center d-none d-md-table-cell">아이디</th>
						<th class="text-center d-none d-md-table-cell">이름</th>
						<th class="text-center d-none d-md-table-cell">닉네임</th>
						<th class="text-center d-none d-md-table-cell">등급</th>
						<th class="text-center d-none d-md-table-cell">수정하기</th>

					</tr>
				</thead>
				<tbody>
                    <c:forEach var="userDTO" items="${userList }" >
    					<tr>
    						<td class="text-center d-none d-md-table-cell">${userDTO.idx }</td>
    						<td class="text-center d-none d-md-table-cell">${userDTO.id }</td>
    						<td class="text-center d-none d-md-table-cell">${userDTO.name }</td>
    						<td class="text-center d-none d-md-table-cell">${userDTO.username }</td>
                            <td class="text-center d-none d-md-table-cell">
                            <c:choose>
                            	<c:when test="${userDTO.role eq 0 }">학생</c:when>
                            	<c:when test="${userDTO.role eq 1 }">선생님</c:when>
                            	<c:otherwise>관리자</c:otherwise>
                            </c:choose>
                            </td>
                            <td class="text-center d-none d-md-table-cell"><button type="button" onclick="location.href='/admin/userdetail?idx=${userDTO.idx }'">수정하기</button>
    					</tr>
                    </c:forEach>
				</tbody>
			</table>
			
			<div class="d-none d-md-block">
				<ul class="pagination justify-content-center">

					<c:choose> 
                      <c:when test="${pageDTO.previousPage <= 0 }">   
					    <li class="page-item disabled">
						  <a href="${root }admin/user?page=${pageDTO.previousPage}" class="page-link">이전</a>
					    </li>
                        </c:when>
                      <c:otherwise>
                        <li class="page-item">
                          <a href="${root }admin/user?page=${pageDTO.previousPage}" class="page-link">이전</a>
                        </li>
                      </c:otherwise>
                    </c:choose>
                    <c:forEach var="idx" begin="${pageDTO.min }" end="${pageDTO.max }">
                    
                      <c:choose>
                        <c:when test="${idx == pageDTO.currentPage }">
    				      <li class="page-item active">
    					    <a href="${root }admin/user?page=${idx}" class="page-link">${idx}</a>
    				      </li>
                        </c:when>
                        <c:otherwise>
    				      <li class="page-item">
    				   	    <a href="${root }admin/user?page=${idx}" class="page-link">${idx}</a>
    				      </li>
                        </c:otherwise>
                      </c:choose>
                    </c:forEach>
					
					<c:choose> 
                      <c:when test="${pageDTO.max >= pageDTO.pageCount }">   
					    <li class="page-item disabled">
						  <a href="${root }admin/user?page=${pageDTO.nextPage}" class="page-link">다음</a>
					    </li>
                        </c:when>
                      <c:otherwise>
                        <li class="page-item">
                          <a href="${root }admin/user?page=${pageDTO.nextPage}" class="page-link">다음</a>
                        </li>
                      </c:otherwise>
                    </c:choose>
				</ul>
			</div>

		</div>
	</div>
</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>