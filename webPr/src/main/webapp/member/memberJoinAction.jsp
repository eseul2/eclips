<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page import="java.sql.Connection"%> --%> 
<%-- <%@ page import="java.sql.DriverManager"%> --%> 
<%@ page import="java.sql.*" %>
<%@include file ="/common/dbconn.jsp"%>
<%@include file ="/common/function.jsp"%>


 <% 
String memberId = request.getParameter("memberId");
  System.out.println("memberId값은?" + memberId); 
  
  String memberPw = request.getParameter("memberPw");
  System.out.println("memberPw?" + memberPw);

  String memberPw2 = request.getParameter("memberPw2");
  System.out.println("memberPw2?" + memberPw2);
 
  String memberName = request.getParameter("memberName");
  System.out.println("memberName?" + memberName);
  
  String memberEmail = request.getParameter("memberEmail");
  System.out.println("memberEmail?" + memberEmail);
  
  String memberPhone = request.getParameter("memberPhone");
  System.out.println("memberPhone?" + memberPhone);
 
  String memberAddr = request.getParameter("memberAddr");
  System.out.println("memberAddr?" + memberAddr);
  
  String memberGender = request.getParameter("memberGender");
  System.out.println("memberGender?" + memberGender);

  String memberBirth = request.getParameter("memberBirth");
  System.out.println("memberBirth?" + memberBirth);
  
  
		   String[] memberHobby = request.getParameterValues("memberHobby");  //배열 타입으로 받겠다. 파라미터 밸류스 사용해서 
		   String memberInHobby = "";

		   if (memberHobby == null) {
		       memberInHobby = "No hobbies selected";  // 기본 값 설정
		   } else {
		       for (int i = 0; i < memberHobby.length; i++) {
		           memberInHobby = memberInHobby + memberHobby[i] + ",";
		       }
		   }

		   
   // 1.jsp 프로그래밍 (날코딩 날코딩방법부터 -> 함수화 -> 객체화 방식)
   // 2. java/jsp 프로그래밍 (model1, model2 MVC방식으로 진화되는 방법) 
   // 3. spring 프레임워크로 프로그래밍 하는 방법
   
   
   // conn 객체 안에는 많은 메소드가 있는데, 일단 createStatement 메소드를 사용해서 쿼리 작성
   // 쓰지 마셔요.... 해킹 위험 있음 
/*    String sql = "insert into member(memberid,memberpw,membername,membergender,memberbirth,memberaddr,memberphone,memberemail,memberhobby)"
   +" values('" 
   +memberId+"','" 
   +memberPw+"','" 
   +memberName+"','"
   +memberGender+"','"
   +memberBirth+"','"
   +memberAddr+"','"
   +memberPhone+"','"  
   +memberEmail+"','"  
   +memberInHobby+"')";
 
   Statement stmt = conn.createStatement(); //쿼리 구문을 동작시키는 클래스 Statement 
   int value = stmt.executeUpdate(sql); 
   // value가 0이면 미입력 1이면 입력됨  */
   
   //PreparedStatement 클래스는 메소드화 시켜서 사용함
   
   //매개변수에 인자값을 대입해서 함수호출하자 
   int value = memberInsert(conn,memberId,memberPw,memberName,memberGender,memberBirth,memberAddr,memberPhone,memberEmail,memberInHobby);
   
   // value값이 1이면 입력성공! 0이면 입력실패 
   // 1이면 성공했기 때문에 다른 페이지로 이동시키고 0이면 다시 회원가입 입력 페이지로 간다. 
  
   String pageUrl =""; 
   if(value==1) {						// index.jsp파일은 web.xml 웹 설정파일에 기본등록되어 있기 때문에 생략이 가능하다. 그냥 "/"; 이렇게 작성해도 됩니당
	   pageUrl=request.getContextPath()+"/index.jsp"; //request.getContextPath() : 프로젝트 이름  //메인 페이지입니다~
	 //  response.sendRedirect(url); //전송방식 sendRedirect는 요청받으면 다시 그쪽으로 가라고 지시하는것 
   }else {
	   pageUrl=request.getContextPath()+"/member/memberJoin.jsp";  //실패하면 다시 회원가입 페이지로 가는것~
	 //  response.sendRedirect(pageUrl);
   }
   
   
    
  %>
  <script>
  // 자바스크립트로 페이지 이동시킨다. document객체 안에 location객체 안에 주소 속성에 담아서
  document.location.href="<%=pageUrl%>";
  </script>
    