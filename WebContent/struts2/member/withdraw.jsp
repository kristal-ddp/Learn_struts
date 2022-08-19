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
<title>탈퇴화면</title>

<link rel="stylesheet" type="text/css" href="<%=cp%>/struts2/member/css/shopStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp%>/struts2/member/css/memberListStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp%>/struts2/member/css/member.css" />

<script type="text/javascript">
	function deleteValue() {
		
		var f = document.deleteForm;
		
		if(!document.deleteForm.userPwd.value){
			alert("패스워드를 입력해 주세요");
			return false;
		}
		
		f.action = "<%=cp%>/member/withdraw.action";
		alert(" 정말로 회원 탈퇴 하시겠습니까?");
		f.submit();
		alert("회원 탈퇴 완료");

	}
</script>

</head>
<body>
	<jsp:include page="./header.jsp" />
	<div id="content" align="center">
		<h1><b>회 원 탈 퇴</b></h1>
		
		<!-- input -->
		<form action="" method="post" name="deleteForm" onsubmit="return deleteValue();">
			<div id="left_area" style="display: inline-block;">
				<div id="update_area">
					<!-- ID -->
					<div class="box row">
						<div class="box label">
							<label for="userName">ID</label>
						</div>
						<div class="box input">
							<input type="text" name="userId" id="userId" readonly="readonly" value="${dto.userId }" />
						</div>
					</div>
					<!-- NAME -->
					<div class="box row">
						<div class="box label">
							<label for="userName">NAME</label>
						</div>
						<div class="box input">
							<input type="text" name="userName" id="userId" readonly="readonly" value="${dto.userName }"/>
						</div>
					</div>
					<!-- PASSWORD -->
					<div class="box row">
						<div class="box label">
							<label for="userName">PASSWORD</label>
						</div>
						<div class="box input">
							<input maxlength="20" type="password" name="userPwd" id="userPwd" placeholder="비밀번호를 입력해주세요." />
						</div>
					</div>
				</div>
			</div>
		</form>
		<br/><br/>
			
		<!-- button -->
		<div align="center">
			<button class="add_button" type="button" style="width: 170px;" onclick="javascript:history.back();">
				<span style="font-size: 8px; color: #484848;">BACK</span>
			</button>
			<button class="add_button" type="button" style="width: 170px;" onclick="deleteValue();">
				<span style="font-size: 8px; color: #484848;">WITHDRAW</span>
			</button>
		</div>
		
	</div>
</body>
</html>