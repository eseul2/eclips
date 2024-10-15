<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    
//    System.out.println("안녕하세요");    
//   out.println("웹페이지에서 안녕하세요?");
    
String msg =(String)session.getAttribute("msg");
session.setAttribute("msg","");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/style.css" type="text/css" rel="stylesheet">
<script type = "text/javascript">
<%
if (!msg.equals("")) {
%>
alert('<%=msg%>');
<%
	}
%>

</script>
</head>
<body>

<div class= "main">환영합니다. 메인 페이지입니다.</div>

</body>
</html>
