<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<%@page import = "mvc.vo.*" %>
    
 <%
 ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");
 //System.out.println("alist==>" + alist);
 PageMaker pm = (PageMaker)request.getAttribute("pm"); //2-39. 뭐야...
 
 
 %>   
 <!-- 22. 이거 설정해주셔야 해요  -->   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글목록</title>
<link href="../css/style2.css" rel="stylesheet">
</head>
<body>
<header>
	<h2 class="mainTitle">글목록</h2>
	<form class="search">
		<select>
			<option>제목</option>
			<option>작성자</option>
		</select>
		<input type="text">
		<button class="btn">검색</button>
	</form>
</header>

<section>	
	<table class="listTable">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회</th>
			<th>날짜</th>
		</tr>   <!--스크립틀릿 : 서버에서 실행될 Java 코드를 JSP 페이지에 삽입하여 동적으로 HTML을 생성하는 역할 -->
		<%for(BoardVo bv : alist) { %>  <!-- 23. 향상된 for문. alist라는 리스트를 순회하면서 각 요소를 변수에 담아 반복 작업을 수행 -->
		<tr>
			<td><%=bv.getBidx()%></td>
			<td class="title"><a href="./detail.html"><%=bv.getSubject()%></a></td>
			<td><%=bv.getWriter()%></td>	<!-- 24. dao 생성한 메소드 여기에 넣어줘야 한다. -->		
			<td><%=bv.getViewcnt()%></td>
			<td><%=bv.getWriteday()%></td>
		</tr>
		<% }%>
	
	</table>
	
	<div class="btnBox">
		<a class="btn aBtn" href="<%request.getContextPath();%>/board/boardWrite.aws">글쓰기</a>
	</div>
	
	 <!--  2-40. 이거 다 하면 보드Dao로 가세요 -->
	<div class="page">
		<ul>
		<%if (pm.isPrev()==true) { %>
		<li><a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getStartPage()-1%>">←</a></li>
		<% } %>
		
		<% for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++) { %>  
			<li <%if (i==pm.getCri().getPage()) {%> class="on"<%}%> > 
			<a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=i%>"><%=i%></a>
			</li>
		<% }%>
		
		<%if(pm.isNext()==true && pm.getEndPage()>0){ %>
		<li><a href="<%=request.getContextPath()%>/board/boardList.aws?page=<%=pm.getEndPage()+1%>">→</a></li>
		<% } %>
		</ul>
	</div>
</section>

</body>
</html>