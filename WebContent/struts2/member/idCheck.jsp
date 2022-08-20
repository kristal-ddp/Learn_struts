<%@page import="com.util.dao.CommonDAOImpl"%>
<%@page import="com.util.dao.CommonDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();

	String userId = request.getParameter("userId");
	
	CommonDAO dao = CommonDAOImpl.getInstance();
	
	String idCheck = (String) dao.getReadData("member.idCheck", userId);
	
	String flag;
	String msg;

	if (idCheck == null || idCheck.equals("")) {
		flag = "true";
		msg = "사용가능한 아이디입니다.";
	} else {
		flag = "false";
		msg = "이미 존재하는 아이디입니다.";
	}
	String result = "[{"
				+ "\"idCheck\":\"" + userId + "\""
				+ ", \"flag\":\"" + flag + "\""
				+ ", \"msg\":\"" + msg + "\""
				+ "}]";

	out.print(result);
%>