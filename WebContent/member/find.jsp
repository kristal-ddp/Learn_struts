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
<link rel="stylesheet" type="text/css" href="<%=cp%>/css/shopStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp%>/member/css/memberListStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp%>/member/css/find.css" />
<link rel="stylesheet" type="text/css"href="<%=cp%>/member/css/member.css" />

<script type="text/javascript">

	function searchid(){
	
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
		
		f.action = "<%=cp%>/member.do?method=find_ok.do";
		f.submit();
		
	}
	
	function searchpw(){
		
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
		
		f.action = "<%=cp%>/member.do?method=find_ok.do";
			f.submit();
	}

</script>

</head>
<body>
<!-- 아이디 찾기 -->
<div id="content" align="center">

		<br> <br>
		<h2>아이디 찾기</h2>

		<br> <br>
		<form action="" method="post" name="myForm">
			<div id="left_area" style="display: inline-block;">
				<div id="update_area" >
					<div style="width: 300px; height: 39; " class="box row">
						<div class="box label">
							<label for="userName">
								<span>
									<a>NAME</a>
								</span>
							</label>
						</div>
						<div class="box input">
							<input autofocus maxlength="10" type="text" name="userName" id="userName" placeholder="이름" />
						</div>
					</div>
					<div  style="width: 300px; height: 39;" class="box row">
						<div class="box label">
							<label for="userTel">
								<span>
									<a>TEL</a>
								</span>
							</label>
						</div>
						<div class="box input">
							<input maxlength="11" type="text" name="userTel" id="userTel" placeholder="(-)없이 숫자만 입력해주세요."
							style="width: 170px;"/>
						</div>
					</div>
				</div>
			</div>
			
			<%-- <div class="button" align="center">
				<button style=" cursor:pointer; border:1px solid #BDBDBD; background-color: #FFFFFF; width: 200px; line-height: 48px;" type="button" 
					onclick="location.href='<%=cp%>/shop/member/login.do';" onmouseover=this.style.backgroundColor='#F0F0F0' onmouseout=this.style.backgroundColor='#FFFFFF'>
					<a style=" font-size: 8px; color: #484848;" >BACK</a>
				</button>	
				
				<button   style=" cursor:pointer; border:1px solid #BDBDBD; background-color: #000000; width: 200px; line-height: 48px;" type="button" 
					onclick="searchid();" onmouseover=this.style.backgroundColor='#303030' onmouseout=this.style.backgroundColor='#000000'>
					<a style=" font-size: 8px; color: #FFFFFF;" >SEARCH</a>
				</button>
			</div> --%>
			
			<div class="findButton">
				<div style="float: left; padding-left: 370px; padding-right: 10px;">	
					<button class="add_button" type="button" style="width: 170px;" onclick="location.href='<%=cp%>/shop/member/login.do';">
						<a style=" font-size: 8px; color: #484848;">BACK</a>
					</button>
				</div>
				
				<div style="float: left;">
					<button class="add_button" type="button" style="width: 170px;" onclick="searchid();">
						<a style=" font-size: 8px; color: #484848;">SEARCH</a>
					</button>	
				</div>
			</div>
			
			<div class="findResult">
				<table width="200;">
					<tr height="30">
						<td colspan="2" align="center"><font color="red">
							<b style="color: red;">${message }</b></font>
						</td>
					</tr>
				</table>
				<table style="margin: auto;">
					<tr height="30">
						<td colspan="2" align="center"><a href="<%=cp%>/shop/member/login.do"><b>${lego }</b></a>
						</td>
					</tr>
				</table>
			</div>
		</form>
</div>

<!-- 비밀번호 찾기 -->
<div id="content" align="center">

		<br>
		<br>

		<h2>비밀번호 찾기</h2>

		<br> <br>
		<form action="" method="post" name="myForm">
			
			<div id="left_area" style="display: inline-block;">
				<div id="update_area" >
					<div style="width: 300px; height: 39; " class="box row">
						<div class="box label">
							<label for="userName">
								<span>
									<a>ID</a>
								</span>
							</label>
						</div>
						<div class="box input">
							<input autofocus maxlength="10" type="text" name="userId" id="userId" placeholder="아이디" />
						</div>
					</div>
					<div  style="width: 300px; height: 39;" class="box row">
						<div class="box label">
							<label for="userTel">
								<span>
									<a>TEL</a>
								</span>
							</label>
						</div>
						<div class="box input">
							<input maxlength="11" type="text" name="userTel" id="userTel" placeholder="(-)없이 숫자만 입력해주세요."
							style="width: 170px;"/>
						</div>
					</div>
				</div>
			</div>
		<br>			
			<div class="findButton">
				<div style="float: left; padding-left: 370px; padding-right: 10px;">	
					<button class="add_button" type="button" style="width: 170px;" onclick="location.href='<%=cp%>/shop/member/login.do';">
						<a style=" font-size: 8px; color: #484848;">BACK</a>
					</button>
				</div>
				
				<div style="float: left;">
					<button class="add_button" type="button" style="width: 170px;" onclick="searchpw();">
						<a style=" font-size: 8px; color: #484848;">SEARCH</a>
					</button>	
				</div>
			</div>


			<table>
				<tr height="30">
					<td colspan="2" align="center"><font color="red"><b style="color: red;">
								${message }</b></font></td>
				</tr>
			</table>
			<table style="margin: auto;">
				<tr height="30">
					<td colspan="2" align="center"><a
						href="<%=cp%>/shop/member/login.do"><b>${lego }</b></a></td>
				</tr>
			</table>


		</form>
</div>

</body>
</html>