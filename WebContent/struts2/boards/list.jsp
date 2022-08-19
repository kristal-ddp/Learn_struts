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
<title>BOARD</title>

<link rel="stylesheet" type="text/css"href="<%=cp%>/boards/css/boardsStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/boards/css/listStyle.css"/>
<script type="text/javascript">

	function sendIt() {
		var f = document.searchForm;
		
		f.action = "/mini/boards/list.action";
		f.submit();
	}

</script>

</head>
<body>
<div class="right_area">
			<c:choose>
				<c:when test="${(empty dto.userId) && (empty userId) }">
					<a href="<%=cp%>/member/terms.action">JOIN</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/login.action">LOGIN</a>&nbsp;&nbsp;
				</c:when>
				<c:when test="${!((empty dto.userId) && (empty userId)) }">
					<a href="<%=cp%>/member/myPage.action">MYPAGE</a>&nbsp;&nbsp;
					<a href="<%=cp%>/member/logout.action">LOGOUT</a>&nbsp;&nbsp;
				</c:when>
			</c:choose>
		</div>
<div id="content" align="center">
	<!-- board -->
	<div class="boardList">
		<!-- title -->
		<div class="boardTitle" align="center">BOARD</div>
		<!-- content -->
		<table class="boardContent">
			<colgroup>
				<col style="width: 80px;">
				<col style="width: auto;">
				<col style="width: 100px;">
				<col style="width: 100px;">
				<col style="width: 80px;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col">SUBJECT</th>
					<th scope="col">ID</th>
					<th scope="col">DATE</th>
					<th scope="col">HITS</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty lists }">
					<tr>
						<td colspan="5" class="empty">등록된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="dto" items="${lists }">
					<tr>
						<!-- NO -->
						<td>${dto.boardNum }</td>
						<!-- SUBJECT -->
						<td><a href="${urlView }&boardNum=${dto.boardNum}">${dto.subject }</a></td>
						<!-- ID -->
						<td>${dto.userId }</td>
						<!-- DATE -->
						<td>${dto.postDate }</td>
						<!-- HITS -->
						<td>${dto.hitCount }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<!-- paging -->
		<div id="paging">
			<p>
				<c:if test="${totalDataCount!=0 }">${pageIndexList }</c:if>
			</p>
		</div>
		
		<!-- search -->
		<div id="sub_wrap">
			<div id="sub">
				<form action="" method="post" name="searchForm">
					<div class="search">
						<fieldset>
							<select name="searchKey" class="selectField">
								<option value="subject">SUBJECT</option>
								<option value="userId">ID</option>
								<option value="content">CONTENT</option>
							</select>
							<input type="text" name="searchValue" class="textField">
							<input type="button" value="SEARCH" class="button" onclick="sendIt();"/>
						</fieldset>
					</div>
				</form>
			</div>			
		</div>
		
		<div class="elementRight" style="float: right;">
			<a href="<%=cp %>/boards/write.action" class="element">WRITE</a>
		</div>
	</div>
</div>
</body>
</html>