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
<title>WRITE</title>

<link rel="stylesheet" type="text/css"href="<%=cp%>/boards/css/boardsStyle.css" />
<link rel="stylesheet" type="text/css"href="<%=cp%>/boards/css/writeStyle.css" />

<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		str = f.subject.value;
		str = str.trim();
		if(!str){
			alert("\n제목을 입력하세요.");
			f.subject.focus();
			return;
		}
		f.subject.value = str;

		str = f.content.value;
		str = str.trim();
		if(!str){
			alert("\n내용을 입력하세요.");
			f.content.focus();
			return;
		}
		f.content.value = str;
		
		if(f.mode.value=="create")
			f.action="<%=cp %>/boards/write.action";
		else if(f.mode.value=="update")
			f.action="<%=cp %>/boards/update.action";
		else if(f.mode.value=="reply")
			f.action="<%=cp %>/boards/reply.action";
			
		f.submit();
		
	}

</script>

</head>
<body>
	<div id="content" align="center">
		<form action="" method="post" name="myForm">
			<div style="display: inline-block; margin: auto;" align="left">
				<div class="box row" style="margin: 0px; border-top: 1px solid #DBDBDB;">
					<div class="box label"><label for="subject">SUBJECT</label></div>
					<div class="box input"><input type="text" name="subject" value="${dto.subject }"></div>
				</div>
				<div class="box row" style="margin: 0px;">
					<div class="box label"><label for="userId">ID</label></div>
					<div class="box text">${dto.userId }</div>
				</div>
				<div class="box row">
					<label for="userId">CONTENT</label><br/>
					<div class="box input">
						<textarea name="content">${dto.content }</textarea>
					</div>
				</div>
				<div class="box row" align="center">
					<!-- UPDATE -->
					<input type="hidden" name="boardNum" value="${dto.boardNum }">
					<input type="hidden" name="pageNum" value="${pageNum }">
										
					<!-- REPLY -->
					<input type="hidden" name="groupNum" value="${dto.groupNum }"/>
					<input type="hidden" name="orderNum" value="${dto.orderNum }"/>
					<input type="hidden" name="depth" value="${dto.depth }"/>
					<input type="hidden" name="parent" value="${dto.boardNum }"/>
					
					<input type="hidden" name="searchParam" value="${searchParam }">
					<input type="hidden" name="mode" value="${mode }">
					<!-- WRITE -->
					<c:if test="${mode == 'write' }">-
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 10px;">
							<a href="javascript:sendIt();">REGISTER</a>
						</div>
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 10px;">
							<input type="reset" value="RETYPE" class="btn2" onclick="document.myForm.subject.focus();"/>
						</div>
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 30px;">
							<a href="#none" onclick="location.href='<%=cp%>/boards/list.action?pageNum=${pageNum }';">CANCEL</a>
						</div>
					</c:if>
					<!-- UPDATE -->
					<c:if test="${mode == 'update' }">
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 10px;">
							<a href="javascript:sendIt();">REGISTER</a>
						</div>
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 30px;">
							<a href="#none" onclick="history.back();">CANCEL</a>
						</div>
					</c:if>
					<!-- REPLY -->
					<c:if test="${mode == 'reply' }">
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 10px;">
							<a href="javascript:sendIt();">REGISTER</a>
						</div>
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 10px;">
							<input type="reset" value="RETYPE" class="btn2" onclick="document.myForm.subject.focus();"/>
						</div>
						<div style="border: 1px solid; width: 50px; padding: 5px; float: right; margin-right: 30px;">
							<a href="#none" onclick="history.back();">CANCEL</a>
						</div>
					</c:if>
				</div>
			</div>
		</form>
	</div>
</body>
</html>