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


<link rel="stylesheet" type="text/css" href="<%=cp%>/css/shopStyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=cp%>/member/css/memberListStyle.css" />





</head>
<body>

	<div id="content" align="center">
		<br> <br> <br> <br> <br> <br>
		<h1>회원탈퇴가 완료되었습니다.</h1>


		<b>그동안 kristal을 이용해주셔서 감사합니다.</b> <br> <br> <br> <br>
		<br>

		<div align="center">
			<button style="background-color: black; width: 280px; height: 50px;"
				type="button" onclick="javascript:location.href='<%=cp%>/shop/main/main.do';">
				<b style="font-size: 17px; color: white;">메인으로</b>
			</button>
		</div>



	</div>



</body>
</html>