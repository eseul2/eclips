package mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dao.MemberDao;
import mvc.vo.MemberVo;

import java.io.IOException;

// @ 이거 어노테이션이라고 부른다. 				// httpservlet을 상속받고 있다 
@WebServlet("/MemberController")		// 서블릿 : 자바로 만든 웹페이지 (접속주소는 : /MemberController 이렇게 나타난다)
public class MemberController extends HttpServlet { // http 서블릿을 상속받고 있다. (extends) 그 뜻은 http통신을 하고 있다
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// 넘어온 모든 값은 여기에서 처리해서 분기한다. - controller의 역할 
		//System.out.println("값이 넘어오나요?");
		
		// 넘어온 주소를 뽑아낸다 (전체주소를 추출)
		String uri = request.getRequestURI();
		System.out.println("uri" + uri);	// mvc_programming/member/memberJoinAction.aws
		String[] location = uri.split("/");
		
		if(location[3].equals("memberJoinAction.aws")) { // 4번째방의 값이 memberJoinAction.aws이면 처리를하세요 
			
			
			String memberid = request.getParameter("memberid");
			String memberpw = request.getParameter("memberpw");
			String membername = request.getParameter("membername");
			String membergender = request.getParameter("membergender");
			String memberbirth = request.getParameter("memberbirth");
			String memberaddr = request.getParameter("memberaddr");
			String memberphone = request.getParameter("memberphone");
			String memberemail = request.getParameter("memberemail");
			
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
		 	int value = md.memberInsert(memberid,
			 			memberpw,		//객체안에 생성해놓은 멤버 메소드를 호출해서 값을꺼낸다
			 			membername,
			 			membergender,
			 			memberbirth,
			 			memberaddr,
			 			memberphone,
			 			memberphone,
			 	   	   memberInHobby);

		   	
		   
		   // value값이 1이면 입력성공! 0이면 입력실패 
		   // 1이면 성공했기 때문에 다른 페이지로 이동시키고 0이면 다시 회원가입 입력 페이지로 간다. 
		   String pageUrl ="";
		   String msg ="";
		   
		   HttpSession session = request.getSession();  // 세션갹채 활용
		   
		   if(value==1) {// index.jsp파일은 web.xml 웹 설정파일에 기본등록되어 있기 때문에 생략이 가능하다. 그냥 "/"; 이렇게 작성해도 됩니당
			
			   msg="회원 가입되었습니다";
			   session.setAttribute("msg",msg);
			   
			   pageUrl=request.getContextPath()+"/"; //request.getContextPath() : 프로젝트 이름  //메인 페이지입니다~
		   
		   response.sendRedirect(pageUrl); //전송방식 sendRedirect는 요청받으면 다시 그쪽으로 가라고 지시하는것 
		   }else {
				msg="회원 가입 오류발생하였습니다";
				session.setAttribute("msg",msg);
				
			   pageUrl=request.getContextPath()+"/member/memberJoin.jsp";  //실패하면 다시 회원가입 페이지로 가는것~
			   response.sendRedirect(pageUrl);
		   }
		   
		  
			
		}
	}
	
	// 보이지 않는 .. .
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
