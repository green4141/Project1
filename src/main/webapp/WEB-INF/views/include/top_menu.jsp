<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />

<!-- 상단 메뉴 부분 -->
<div id="top-menu">
	<!-- 로고와 메뉴 왼쪽 묶기 -->
	<div class="menu-start">
		<!-- 더조은컴퓨터아카데미 로고 -->
		<div class="logo">
			<a href="${root}main">
				<img src="/images/head_mcl_logo.jpg" alt="로고" style="height: 26px; vertical-align: middle;">
			</a>
		</div>
		
		<!-- 자유 게시판, 선생 게시판 -->
		<ul class="menu-left">
	    	<c:forEach var="menu" items="${topMenuList}">
	        	<li>
	            	<a href="${root }board/main?board_id=${menu.board_id}">${menu.name}</a>
	        	</li>
	        </c:forEach>
	        <c:if test="${loginUserDTO.role == 2 }">
	            <li>
	                <a href="${root }admin/user">유저 목록</a>
	            </li>
	            <li>
	                <a href="${root }admin/board">게시글 목록</a>
	            </li>
	        </c:if>
		</ul>
	</div>
	
	<!-- 로그인, 회원가입 -->
	<ul class="menu-right">
    	<c:choose>
        	<c:when test="${loginUserDTO.userLogin == true }">
            	<li><a href="${root }user/modify">정보수정</a></li>
                <li><a href="${root }user/logout">로그아웃</a></li>
            </c:when>
            <c:otherwise>
            	<li><a href="${root }user/login">로그인</a></li>
                <li><a href="${root }user/join">회원가입</a></li>
            </c:otherwise>            
        </c:choose>
	</ul>
</div>