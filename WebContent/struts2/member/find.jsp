<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/shopStyle.css"/>
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/memberListStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/find.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/member.css" />

<script type="text/javascript">

	function searchId(){
	
		var f = document.myForm;
		
		if(!f.userName.value){
			alert("성함을 입력해 주세요.");
			f.userName.focus();
			return;
		}
		
		if(!f.userTel.value){
			alert("전화번호를 입력해 주세요.");
			f.userTel.focus();
			return;
		}
		
		f.action = "<%=cp%>/member/find.action";
		f.submit();
		
	}
	
	function searchPwd(){
		
		var f = document.myForm;
		
		if(!f.userId.value){
			alert("아이디를 입력해 주세요.");
			f.userId.focus();
			return;
		}
		
		if(!f.userTel.value){
			alert("전화번호를 입력해 주세요.");
			f.userTel.focus();
			return;
		}
		
		f.action = "<%=cp%>/member/find.action";
		f.submit();
	}
</script>

</head>
<body>
	<jsp:include page="./header.jsp"/>
	<div id="content" align="center">
	
		<!-- 아이디 찾기 -->
		<c:if test="${mode == 'findId' }">
			<br><br>
			<h2>아이디 찾기</h2>
			<br><br>
			<!-- input -->
			<form action="" method="post" name="myForm">
				<div id="left_area" style="display: inline-block;">
					<div id="update_area">
						<!-- NAME -->
						<div style="width: 300px; height: 39;" class="box row">
							<div class="box label">
								<label for="userName">NAME</label>
							</div>
							<div class="box input">
								<input autofocus maxlength="10" type="text" name="userName" id="userName" placeholder="이름" />
							</div>
						</div>
						<!-- TEL -->
						<div style="width: 300px; height: 39;" class="box row">
							<div class="box label">
								<label for="userTel">TEL</label>
							</div>
							<div class="box input">
								<input maxlength="11" type="text" name="userTel" id="userTel" placeholder="(-)없이 숫자만 입력해주세요." style="width: 170px;" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</c:if>
	
		<!-- 비밀번호 찾기 -->
		<c:if test="${mode == 'findPwd' }">
			<br><br>
			<h2>비밀번호 찾기</h2>
			<br><br>
			<!-- input -->
			<form action="" method="post" name="myForm">
				<div id="left_area" style="display: inline-block;">
					<div id="update_area">
						<!-- NAME -->
						<div style="width: 300px; height: 39;" class="box row">
							<div class="box label">
								<label for="userId">ID</label>
							</div>
							<div class="box input">
								<input autofocus maxlength="10" type="text" name="userId" id="userId" placeholder="아이디" />
							</div>
						</div>
						<!-- TEL -->
						<div style="width: 300px; height: 39;" class="box row">
							<div class="box label">
								<label for="userTel">TEL</label>
							</div>
							<div class="box input">
								<input maxlength="11" type="text" name="userTel" id="userTel" placeholder="(-)없이 숫자만 입력해주세요." style="width: 170px;" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</c:if>
			
		<!-- message -->
		<div class="findResult">
			<b style="color: red;">${message }</b>
			<a href="<%=cp%>/member/login.action">
				<b>${lego }</b>
			</a>
		</div>
		
		<!-- button -->
		<div class="findButton">
			<button class="add_button" type="button" style="width: 170px;" onclick="location.href='<%=cp%>/member/login.action';">
				<span style="font-size: 8px; color: #484848;">BACK</span>
			</button>
			<button class="add_button" type="button" style="width: 170px;" onclick="searchId();">
				<span style="font-size: 8px; color: #484848;">SEARCH</span>
			</button>
		</div>
	</div>
</body>
</html>