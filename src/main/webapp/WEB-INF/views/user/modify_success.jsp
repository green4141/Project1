<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }/" />
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">    
    <title>Insert title here</title>
    
    <!-- 파비콘 -->
	<c:import url="/WEB-INF/views/include/favicon.jsp" />

	<!-- 커스텀 CSS 추가 -->
	<link rel="stylesheet" href="/css/style.css"/>
    
  </head>
  <body>
    <script>
      alert("회원 정보가 수정되었습니다");
      location.href="${root}user/modify";
    </script> 
  </body>
</html>