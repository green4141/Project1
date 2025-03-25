<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="root" value="${pageContext.request.contextPath}/" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
  </head>
  <body>
    <script>
    	alert("성공적으로 업로드 되었습니다.");
    	location.href="${root}board/read?board_id=${writeBoardDTO.board_id}&idx=${writeBoardDTO.idx}";
    </script>
  </body>
</html>