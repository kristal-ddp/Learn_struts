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
<title>로그인</title>

<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/memberListStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/shopStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/struts2/member/css/member.css" />

<script type="text/javascript">
	function login(){
	
		var f = document.myForm;
		
		if(!f.userId.value){
			alert("아이디를 입력해 주세요.");
			f.userId.focus();
			return;
		}
		
		if(!f.userPwd.value){
			alert("패스워드를 입력해 주세요.");
			f.userPwd.focus();
			return;
		}
		
		f.action = "<%=cp%>/member/login.action";
		f.submit();
		
	}
	
</script>

</head>
<body>
	<jsp:include page="./header.jsp"/>
	<div id="content" align="center">
		<br> <br>
			<h2>로그인</h2>
			<br> <br>
		<form action="" method="post" name="myForm" class="loginForm">
			<div id="left_area" style="display: inline-block;" >
	         	<div id="update_area">
	                <div class="box row">
		                <div class="box label">
		                	<label for="userId" >
		                		<span>
		                       		<a>MEMBER ID</a>
		                       	</span>
		                	</label>
		                 </div>
		                 <div class="box input">
		                 	<input maxlength="20" type="text" name="userId" id="userId" autofocus/>
		                 </div>
	                 </div>
					 <div class="box row">
		                 <div class="box label">
		                    <label for="userPwd" >
			                    <span>
			                     	<a>PASSWORD</a>
		                     	</span>
	                     	</label>
		                 </div>
		                 <div class="box input">
		                    <input maxlength="20" type="password" name="userPwd" id="userPwd"/>
		                 </div>
	                 </div>
				</div>
			</div>
			
			<div class="button" align="center">			
				<button style="cursor:pointer; border:1px solid #BDBDBD; background-color: #FFFFFF; width: 170px; line-height: 48px;" type="button" onclick="login();" 
						onmouseover="this.style.backgroundColor='#F0F0F0';" onmouseout="this.style.backgroundColor='white';">
						<span style="font-size: 8px; color: #484848;">SIGN IN</span>
				</button>				
			</div>
			
			<div>
				<b style="color: red;">${message }</b>
			</div>
				
			<div class="search" style="font-size: 11px; margin-top: 10px; margin-left: 10px; text-align:center; ">
				<a href="<%=cp%>/member/find.action?mode=findId">SEARCH ID</a> <span>&nbsp;/&nbsp;</span> 
				<a href="<%=cp%>/member/find.action?mode=findPwd">PASSWORD</a>
			</div>
		</form>
	</div>
</body>
</html>