<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>doB</h3>
<h4>age : <%=request.getParameter("age") %></h4>
<h4>name : ${name}</h4>  
			<%-- ${} : request.setAttribute() or session.setAttribute()�� ����� �� ���� --%>
			<%-- ���⿡���� session.setAttribute()�� ��� ���� ���̴�. --%>
<h4><a href="/doC">�̵�</a></h4>    <%-- http://localhost:8080/doC --%>
</body>
</html>