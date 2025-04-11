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
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<!-- 파비콘 -->
<c:import url="/WEB-INF/views/include/favicon.jsp" />

<!-- 커스텀 CSS 추가 -->
<link rel="stylesheet" href="/css/style_board_read.css"/>
<link rel="stylesheet" href="/css/top_menu.css"/>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${root }js/board.js"></script>
<script>
    function toggleFavorite(isFavorite, board_idx) {
        const url = "/board/toggleFavBoard?board_idx=" + board_idx;
        $.ajax({
            type: "GET",
            url: url,
            success: function(response) {
                if (response) {
                    $('#favoriteImage').attr('src', '/images/heart_full.png');
                    $('#favoriteImage').attr('onclick', 'toggleFavorite(true, ' + boardIdx + ')');
                } else {
                    $('#favoriteImage').attr('src', '/images/heart_empty.png');
                    $('#favoriteImage').attr('onclick', 'toggleFavorite(false, ' + boardIdx + ')');
                }
            }
        });
    }
</script>

</head>
<body class="page-wrapper">

<!-- 상단 부분 -->
<c:import url="/WEB-INF/views/include/top_menu.jsp"></c:import>
<div class="page-content">
    <div class="container">
        <div class="board-container">
            <div class="board-card">
                <div class="board-header" style="display: flex; justify-content: space-between; align-items: center;">
                	<div>
	                    <h2>
	                    	${readBoardDTO.title}
	                    	<!-- 공지사항 표시 -->
	                    	<c:if test="${readBoardDTO.is_notice == 1}">
	                    		<span style="font-size: 1rem; color: crimson; margin-left: 10px;">📢</span>
	                    	</c:if>
	                    </h2>
	                </div>
                    <!-- 관리자만 보이는 공지 설정 폼 -->
                    <c:if test="${loginUserDTO.role == 2}">
                    	<form action="${root}admin/toggleNotice" method="post" style="margin-top: 10px;">
                    		<input type="hidden" name="idx" value="${readBoardDTO.idx}" />
                    		<input type="hidden" name="page" value="${page}" />
                    		<label>
                    			<input type="checkbox" name="is_notice" value="1"
                    				onchange="this.form.submit()"
                    				<c:if test="${readBoardDTO.is_notice == 1}">checked</c:if> />
                    			이 글을 공지로 설정
                    		</label>
                    	</form>
                    </c:if>
                </div>
                <div class="board-info">
                    <span class="board-writer">${readBoardDTO.username}</span>
                    <span class="board-date">
                        <fmt:formatDate value="${readBoardDTO.date}" pattern="yyyy-MM-dd" />
                    </span>
                    <span class="board-hits">조회수 ${readBoardDTO.hits}</span>
                </div>
                <div class="board-content">
                    <pre>${readBoardDTO.content}</pre>
                </div>
                <c:if test="${hasFile}">
                    <div class="board-image">
                        <img src="${root}board/requestimage?board_idx=${readBoardDTO.idx}" alt="첨부 이미지">
                    </div>
                </c:if>
                <br>
                   
                    <div class="form-group" id="reply">
                    	<table>
	                    	<tbody>
	                    	</tbody>
	                    	<c:if test="${loginUserDTO.userLogin }">
	                    	<tfoot class="reply_useronly">
		                    	<!-- tr class="reply_yd">
		                    		<td><span id="reply_username">${loginUserDTO.username }</span></td>
		                    		<td><input type="text" id="reply_content"/></td>
		                    		<td><button type="button" id="reply_commit" onclick="replyCommit()">댓글쓰기</button>
		                    		<td></td>
		                    	</tr-->
	                    	</tfoot>
	                    	</c:if>
                    	</table>
                    </div>
                    <br>
					<div class="form-group">
						<div class="text-right">
							<a href="${root }admin/board?page=${page}&title=${title}&username=${username }&startdate=${startdate}&enddate=${enddate}&sort=${sort}&order=${order}" class="btn btn-primary">목록보기</a>
							<a href="${root }admin/delete?idx=${idx}" class="btn btn-danger">삭제하기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>

<!-- footer -->
<c:import url="/WEB-INF/views/include/bottom_info.jsp"></c:import>

</body>
<script>
const loadReply = () => {
	$.ajax({
		url: "${root}reply/select?board_idx=${readBoardDTO.idx}",
		type: "GET",
		success: (arg) => {
			const $tbody = $("#reply tbody")
			$tbody.empty()
			let html = "";
			arg.forEach((item) => {
				html += `<tr class="tr-\${item.idx}">
							<td>\${item.username}</td>
							<td><span id="content-\${item.idx}">\${item.content}</span></td>
					<td class='reply_useronly'><button type='button' class='reply_delete_btn' onclick='replyDelete(\${item.idx})'>삭제하기</button></td>`
				html += `</tr>`
			})	
			$tbody.append(html)

		}
	})
}
$(document).ready(() => {
	$("#reply_content").on('change keydown paste input', function(){
        if(this.value.length > this.maxLength){
          this.value = this.value.slice(0, this.maxLength);
        }
      });
	loadReply()
})
const replyCommit = () => {
const content = $("#reply_content").val()
	const data = {
		user_idx: ${loginUserDTO.idx },
		board_idx: ${readBoardDTO.idx},
		content: content
	}
	$.ajax({
		url: "${root}reply/insert",
		type: "POST",
		data: JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		success: (arg) => {
			$("#reply_content").val("");
			loadReply();
		}
	})
}
const replyupdate = (idx) => {
	const $span = $(`#content-\${idx}`)
	const content = $span.text();
	const $td = $span.parent();
	$td.html(`<input type="text" id="input-content-\${idx}" value="\${content}" maxlength="300"/>`)
	$(`#reply_update_\${idx}`).attr("onclick", `replyupdateproc(\${idx})`)
}

const replyupdateproc = (idx) => {

	if(confirm("정말로 수정하시겠습니까?")) {
		const content = $(`#input-content-\${idx}`).val();
		const data = {
			content: content,
			idx: idx
		}
		
		$.ajax({
			url: "${root}reply/update",
			type: "POST",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			success: () => {
				alert("수정에 성공했습니다.")
				loadReply();
			}
		})
	} else return false
}

const replyDelete = (idx) => {
if(confirm("정말로 삭제하시겠습니까?"))
	$.ajax({url: "${root}reply/delete",
		type: "POST",
		data: JSON.stringify({idx: idx}),
		contentType: "application/json; charset=utf-8",
		success: () => {
			alert("삭제에 성공했습니다.")
			loadReply();
		}
	})
	else return false;
}
</script>
</html>
