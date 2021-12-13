<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>doB</h3>
<h4>age : <%=request.getParameter("age") %></h4>
<h4>name : ${name}</h4>  
			<%-- ${} : request.setAttribute() or session.setAttribute()에 담았을 때 가능 --%>
			<%-- 여기에서는 session.setAttribute()에 잠시 담은 것이다. --%>
<h4><a href="/doC">이동</a></h4>    <%-- http://localhost:8080/doC --%>
</body>
</html>