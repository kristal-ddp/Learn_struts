<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=cp %>/css/shopStyle.css"/>
<script type="text/javascript" src="<%=cp%>/main/header.js"></script>

<div id="header" class="txt">
	<div id="header_bg"></div>
	<div class="cboth">
		<!-- <div class="left_area">
				<a onclick="display2();">BORAD</a>
				<div id="display2" class="display">
					<div class="display_elements">
						<a href="/sub/shop/boards/notice.do">NOTICE</a>
						<a href="/sub/shop/boards/qna.do">Q/A</a>
						<a href="/sub/shop/boards/review.do">REVIEW</a>
					</div>
				</div>
		</div> -->
		<div class="right_area">
			<c:choose>
				<c:when test="${empty userId }">
					<a href="/member.do?method=login">LOGIN</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="${!empty userId }">
					<a href="/member.do?method=myPage">MYPAGE</a>&nbsp;&nbsp;
					<a href="/member.do?method=logout_ok">LOGOUT</a>&nbsp;&nbsp;
				</c:when>
			</c:choose>
		</div>
	</div>
</div>