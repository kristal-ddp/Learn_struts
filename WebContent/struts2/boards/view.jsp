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
<title>VIEW</title>

<link rel="stylesheet" type="text/css"href="<%=cp%>/boards/css/boardsStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=cp %>/boards/css/viewStyle.css"/>

<script type="text/javascript">

function sendData(value) {
	
	var boardNum = "${dto.boardNum }";
	var pageNum = "${dto.pageNum }";
	
	var url = "<%=cp %>/boards/";
	
	if(value=="update")
		url += "update.action?";
	else if(value=="delete")
		url += "delete.action?";
	else if(value=="reply")
		url += "reply.action?";
	
	url += "boardNum=" + boardNum;
	url += "&${params }";//검색을 했을 경우 searchKey, searchValue가 붙음
	
	location.replace(url);
	
}

</script>

</head>
<body>
	<div id="content" align="center">
		<!-- board -->
		<div class="boardList">
			<!-- title -->
			<div class="boardTitle" align="center">BOARD</div>
			<!-- content -->
			<div class="boardContent" align="center">
				<div class="">
					<table border="0">
						<colgroup>
							<col style="width: 130px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr class="viewTitle">
								<td colspan="3">
									<!-- SUBJECT -->
									<div class="name">SUBJECT
										<p>${dto.subject }</p>
									</div>
									<!-- userID -->
									<div class="name">ID
										<p>${dto.userId }</p>
									</div>
									<!-- POSTDATE -->
									<div class="name">DATE
										<p>${dto.postDate }</p>
									</div>
									<!-- HITCOUNT -->
									<div class="name">HITS
										<p>${dto.hitCount }</p>
									</div>
								</td>
							</tr>
							<tr class="viewContents">
								<td colspan="3">
									<div class="">
										<!-- CONTENT -->
										<div class="viewContent">${dto.content }</div>
									</div>
								</td>
							</tr>
							<!-- <tr class="attach displaynone">
								<th scope="row">첨부파일</th>
								<td></td>
							</tr> -->
							<!-- <tr class="displaynone ">
								<th scope="row">비밀번호</th>
								<td><input id="password" name="password"
									onkeydown="if (event.keyCode == 13 || event.which == 13) { return false; }"
									value="" type="password">
									<span class="">수정 및 삭제하려면 비밀번호를 입력하세요.</span></td>
							</tr> -->
						</tbody>
					</table>
				</div>
				<div class="bbsArticle_bottomLine">
					BEFORE:
					<c:if test="${!empty preSubject }">
						<a
							href="<%=cp %>/boards/view.action?${params}&boardNum=${preBoardNum}">
							${preSubject } </a>
					</c:if>
				</div>
				<div class="bbsArticle_noLine">
					AFTER:
					<c:if test="${!empty nextSubject }">
						<a
							href="<%=cp %>/boards/view.action?${params}&boardNum=${nextBoardNum}">
							${nextSubject } </a>
					</c:if>
				</div>
				<div class="button">
					<input type="hidden" name="searchParam" value="${searchParam }">
					<div class="elementLeft">
						<a href="#none" class="element"
						onclick="location.href='<%=cp%>/boards/list.action?${params }';">LIST</a>
						<a href="#none" onclick="sendData('reply')" class="element">REPLY</a>
					</div>
					<%-- <c:if test="${dto.userId == userId }"> --%>
						<div class="elementRight">
							<a href="#none" onclick="sendData('update')" class="element">EDIT</a>
							<a href="#none" onclick="sendData('delete')" class="element">DELETE</a>
						</div>
					<%-- </c:if> --%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>