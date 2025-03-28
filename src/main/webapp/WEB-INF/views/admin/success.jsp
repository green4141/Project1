<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
alert("작업이 완료되었습니다.")
<c:if test="${work == 'userUpdate'}">
location.href='/admin/userdetail?idx=${idx}'
</c:if>
</script>
</html>