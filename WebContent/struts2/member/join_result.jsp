<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=cp%>/struts2/member/css/shopStyle.css" />
</head>
<body>
	<div id="content" align="center">
		<br/><br/><br/><br/><br/><br/>
		<h3>${userName }님반갑습니다</h3><br/>
		<b>kristal 회원가입을 진심으로 환영합니다.</b><br/><br/><br/>
		<div align="center">
			<button class="add_button" type="button" style="width: 170px;" onclick="javascript:location.href='#';">
				<span style="font-size: 8px; color: #484848;">MAIN</span>
			</button>
			<button class="add_button" type="button" style="width: 170px;" onclick="javascript:location.href='<%=cp%>/member/login.action';">
				<span style="font-size: 8px; color: #484848;">LOGIN</span>
			</button>
		</div>
	</div>
</body>
</html>