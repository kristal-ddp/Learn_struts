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

	function sendIt() {
		var f = document.searchForm;
		
		f.action = "<%=cp %>/shop/boards/noticeView.do";
		f.submit();
	}

</script>
</head>
<body>
	<div id="content" align="center">
		<!-- board -->
		<div class="boardList">
			<!-- title -->
			<div class="boardTitle" align="center">VIEW</div>
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
				<div class="button">
					<div class="elementLeft">
							<a href="<%=cp %>/shop/boards/notice.do">LIST</a>
					</div>
					<c:if test="${dto.userId == userId }">
						<div class="elementRight">
							<a href="<%=cp %>/shop/boards/noticeUpdate.do?boardNum=${dto.boardNum }&${params }';"
							class="element">EDIT</a>
							<a href="<%=cp %>/shop/boards/noticeDelete_ok.do?boardNum=${dto.boardNum }&${params }';"
							class="element">DELETE</a>
						</div>
					</c:if>
				</div>
			</div>
	
		</div>
	</div>
</body>
</html>