<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/member_mini.css"/>

</head>
<body>
	<jsp:include page="./header.jsp"/>
	<div id="content" align="center">
		<button class="add_button" type="button" onclick="javascript:location.href='<%=cp%>/member/join.action';">
			<span>
				<b>JOIN</b><br>
				회원가입 페이지로 이동합니다.
			</span>
		</button>
		<button class="add_button" type="button" onclick="javascript:location.href='<%=cp%>/member/update.action';">
			<span>
				<b>PROFILE</b><br>
				회원정보 수정 페이지로 이동합니다.
			</span>
		</button>
	</div>
</body>
</html>