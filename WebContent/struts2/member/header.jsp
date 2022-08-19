<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/shopStyle.css"/>
<%-- <script type="text/javascript" src="<%=cp%>/main/header.js"></script> --%>

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
				<c:when test="${(empty dto.userId) && (empty userId) }">
					<a href="<%=cp%>/member/terms.action">JOIN</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/login.action">LOGIN</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="${!((empty dto.userId) && (empty userId)) }">
					<a href="<%=cp%>/member/myPage.action">MYPAGE</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/logout.action">LOGOUT</a>&nbsp;&nbsp;
				</c:when>
			</c:choose>
		</div>
	</div>
</div>