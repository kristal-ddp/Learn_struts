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

<link rel="stylesheet" type="text/css"href="<%=cp%>/member/css/member_mini.css"/>

<script type="text/javascript">

function sendItJoin() {
	
	var f = document.myPageForm;
	
	f.action = "<%=cp%>/member.do?method=terms";
	f.submit();
}

function sendItProfile() {
	
	var f = document.myPageForm;
	
	f.action = "<%=cp%>/member.do?method=update";
	f.submit();
}

</script>

</head>
<body>

<form name="myPageForm" method="post">
	<div id="content" align="center">
		<div class="right_area">
		<c:choose>
			<c:when test="${empty userId }">
				<a href="<%=cp%>/member.do?method=login">LOGIN</a>&nbsp;&nbsp;
			</c:when>
			<c:when test="${!empty userId }">
				<a href="<%=cp%>/member.do?method=logout_ok">LOGOUT</a>&nbsp;&nbsp;
			</c:when>
		</c:choose>
		</div>
		<div>
			<button class="add_button" type="button" onclick="sendItJoin();">
				<span>
					<b>JOIN</b><br>
					회원가입 페이지로 이동합니다.
				</span>
			</button>
			<button class="add_button" type="button" onclick="sendItProfile();">
				<span>
					<b>PROFILE</b><br>
					회원정보 수정 페이지로 이동합니다.
				</span>
			</button>
		</div>
	</div>
</form>

</body>
</html>