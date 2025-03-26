<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:if test="${error eq 'adminloginerror'}">
<script>
alert("관리자로 로그인해 주십시오.");
location.href="/main";
</script>
</c:if>

</html>