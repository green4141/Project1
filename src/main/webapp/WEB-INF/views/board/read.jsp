<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}/" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<div class="page-content">
	<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<div class="form-group">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" class="form-control" value="${readBoardDTO.username }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label>
						<fmt:formatDate value="${readBoardDTO.date}" pattern="yyyy-MM-dd" var="formattedDate" />
                        <input type="text" id="board_date" name="board_date" class="form-control" value="${formattedDate}" disabled="disabled" />
					</div>
                    <div class="form-group">
                      <label for="board_hits">조회수</label>
                      <input type="text" id="board_hits" name="board_hits" class="form-control" value="${readBoardDTO.hits }" disabled="disabled"/>
                    </div>
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control" value="${readBoardDTO.title }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<textarea id="board_content" name="board_content" class="form-control" rows="10" style="resize:none" disabled="disabled">${readBoardDTO.content }</textarea>
					</div>
                    <c:if test="${readBoardDTO.file != null }">
    					<div class="form-group">
    						<label for="board_file">첨부 이미지</label>
    						<img src="${root}upload/${readBoardDTO.file }" width="100%"/>						
    					</div>
                    </c:if>
					<div class="form-group">
						<div class="text-right">
							<a href="${root }board/main?board_id=${board_id}&page=${page}" class="btn btn-primary">목록보기</a>
                            <c:if test="${readBoardDTO.user == loginUserDTO.idx}" >
							  <a href="${root }board/modify?board_id=${board_id}&idx=${idx}&page=${page}" class="btn btn-info">수정하기</a>
							  <a href="${root }board/delete?board_id=${board_id}&idx=${idx}" class="btn btn-danger">삭제하기</a>
                            </c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
</div>
<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
</html>
