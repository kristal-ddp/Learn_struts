<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
	
	String sessionUserId = (String) session.getAttribute("userId");
	
	boolean login = false;
	if (sessionUserId != null && !sessionUserId.equals("")){
		login = true;
	}
%>
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/shopStyle.css"/>

<div id="header" class="txt">
	<div align="center">
		<a href="<%=cp%>/member/main.action">
			<img src="<%=cp%>/struts2/layout/image/mainlogo.png" width="150px;">
		</a>
	</div>
	
	<div class="cboth">
		<div class="left_area">
			<a href="<%=cp%>/boards/list.action">BORAD</a>
		</div>
		
		<div class="right_area">
			<c:choose>
				<c:when test="<%=!login %>">
					<a href="<%=cp%>/member/terms.action">JOIN</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/login.action">LOGIN</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="<%=login %>">
					<a href="<%=cp%>/member/myPage.action">MYPAGE</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/logout.action">LOGOUT</a>&nbsp;&nbsp;
				</c:when>
			</c:choose>
		</div>
	</div>
</div>