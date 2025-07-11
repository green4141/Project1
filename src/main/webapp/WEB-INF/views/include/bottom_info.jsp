<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- footer -->
<div id="footer" class="container-fluid-footer bg-dark text-white">
	<div class="container-footer">
		<h3>(주)더조은</h3>
		<p>대표이사 : 더조은 | 주소 : 서울특별시 종로구 우정국로2길 21 9층 | 사업자번호 : 123-45-67890 | 통신판매신고번호 : 2025-서울종로-4003</p>
        <p>제휴&마케팅 문의 : tjoeun@tjoeun.com</p>
	</div>
</div>

<style>

  /* 전체 페이지 높이 설정 */
  html, body {
    height: 100%;
    margin: 0;
    padding: 0;
  }

  /*body를 flex로 설정하여 footer를 항상 하단에 위치*/
  body {
    display: flex;
    flex-direction: column;
    min-height: 100%;
  }

  /*container 내용이 적을 때 footer가 하단에 고정되도록*/
  .container {
    flex-grow: 1;
  }

  /* footer 스타일 */
  #footer {
    width: 100%;
    height: 125px;
    background-color: #030066;
    color: white;
    display: flex;
    align-items: center;
    justify-content: left;
    padding-left: 30px;
    font-size: 14px;
    flex-shrink: 0;
    padding-top: 30px;
    padding-bottom: 30px;
    margin-top: auto;
    box-sizing: border-box;
    overflow-x: hidden;  /* 화면을 넘지 않도록 숨김 */
    overflow-y: hidden;
  }

</style>