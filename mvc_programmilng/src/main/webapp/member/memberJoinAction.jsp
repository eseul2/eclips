<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="mv" class="mvc.vo.MemberVo" scope="page" />
<%@ page import= "mvc.dao.MemberDao" %>
<jsp:setProperty name="mv" property="*" /> <!-- 바인딩기술을 위해 변수이름같게함 -->


 <% 
 		// 매개변수에 인자값 대입해서 함수호출하자 
	 String[] memberHobby = request.getParameterValues("memberHobby");  //배열 타입으로 받겠다. 파라미터 밸류스 사용해서 
	 String memberInHobby = "";
	
	 if (memberHobby == null) {
	     memberInHobby = "No hobbies selected";  // 기본 값 설정
	 } else {
	     for (int i = 0; i < memberHobby.length; i++) {
	         memberInHobby = memberInHobby + memberHobby[i] + ",";
	     }
	 }

    MemberDao md = new MemberDao();
 	int value = md.memberInsert(mv.getMemberid(),
	 		   mv.getMemberpw(),		//객체안에 생성해놓은 멤버 메소드를 호출해서 값을꺼낸다
	 		   mv.getMembername(),
	 	   	   mv.getMembergender(),
	 	   	   mv.getMemberbirth(),
	 	   	   mv.getMemberaddr(),
	 	   	   mv.getMemberphone(),
	 	   	   mv.getMemberemail(),
	 	   	   memberInHobby);

   	
   
   // value값이 1이면 입력성공! 0이면 입력실패 
   // 1이면 성공했기 때문에 다른 페이지로 이동시키고 0이면 다시 회원가입 입력 페이지로 간다. 
   String pageUrl ="";
   String msg ="";
   if(value==1) {						// index.jsp파일은 web.xml 웹 설정파일에 기본등록되어 있기 때문에 생략이 가능하다. 그냥 "/"; 이렇게 작성해도 됩니당
	
	   msg="회원 가입되었습니다";
   pageUrl=request.getContextPath()+"/index.jsp"; //request.getContextPath() : 프로젝트 이름  //메인 페이지입니다~
	 //  response.sendRedirect(url); //전송방식 sendRedirect는 요청받으면 다시 그쪽으로 가라고 지시하는것 
   }else {
		msg="회원 가입 오류발생하였습니다";
	   pageUrl=request.getContextPath()+"/member/memberJoin.jsp";  //실패하면 다시 회원가입 페이지로 가는것~
	 //  response.sendRedirect(pageUrl);
   }
   
   
    
  %>
  <script>
  alert('<%=msg%>');
  // 자바스크립트로 페이지 이동시킨다. document객체 안에 location객체 안에 주소 속성에 담아서
  document.location.href="<%=pageUrl%>";
  </script>
    