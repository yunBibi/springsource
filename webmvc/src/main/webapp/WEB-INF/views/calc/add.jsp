<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>����</h3>
<form action="" method="post">
	<div>
		<input type="text" name="num1" id="" value="${addDTO.num1}"/>
		+
		<input type="text" name="num2" id="" value="${addDTO.num2}"/>
		<input type="submit" value="����" />
	</div>
	<div>
		<label fot="result">���</label>
		<input type="text" name="result" id="" value="${result}" />
	</div>
</form>
</body>
</html>