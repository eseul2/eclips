package mvc.controller;

import jakarta.servlet.RequestDispatcher;
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
		
		if(location[2].equals("memberJoinAction.aws")) { // 3번째방의 값이 memberJoinAction.aws이면 처리를하세요 
		
			String memberId = request.getParameter("memberid");
			String memberPw = request.getParameter("memberpw");
			String memberPw2 = request.getParameter("memberpw2");
			String memberName = request.getParameter("membername");
			String memberGender = request.getParameter("membergender");
			String memberBirth = request.getParameter("memberbirth");
			String memberAddr = request.getParameter("memberaddr");
			String memberPhone = request.getParameter("memberphone");
			String memberEmail = request.getParameter("memberemail");
			String[]memberHobby = request.getParameterValues("memberhobby");  //배열 타입으로 받겠다. 파라미터 밸류스 사용해서 
			String memberInHobby = "";
			
			 if (memberHobby == null) {
			     memberInHobby = "No hobbies selected";  // 기본 값 설정
			 } else {
			     for (int i = 0; i < memberHobby.length; i++) {
			         memberInHobby = memberInHobby + memberHobby[i] + ",";
			     }
			 }
		

		    MemberDao md = new MemberDao();
		 	int value = md.memberInsert(memberId,
			 			memberPw,		//객체안에 생성해놓은 멤버 메소드를 호출해서 값을꺼낸다
			 			memberName,
			 			memberGender,
			 			memberBirth,
			 			memberAddr,
			 			memberPhone,
			 			memberEmail,
			 	   	    memberInHobby);

		   	
		   
		   // value값이 1이면 입력성공! 0이면 입력실패 
		   // 1이면 성공했기 때문에 다른 페이지로 이동시키고 0이면 다시 회원가입 입력 페이지로 간다. 
		   String pageUrl ="";
		   String msg ="";
		   
		   HttpSession session = request.getSession();  // 세션객채 활용
		   
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
		   
		}else if(location[2].equals("memberJoin.aws")) {
			System.out.println("들어왔나?");
			
			String uri2 = "/member/memberJoin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri2);
		    rd.forward(request, response);  // 포워드 방식 : 내부 안에서 넘겨서 토스하겠다는뜻
		
		}else if(location[2].equals("memberLogin.aws")) {   // 회원 로그인 페이지 index로 넘긴다.
			System.out.println("들어왔나?");
			
			String uri2 = "/member/memberLogin.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(uri2);
		    rd.forward(request, response);  // 포워드 방식 : 내부 안에서 넘겨서 토스하겠다는뜻
		}else if(location[2].equals("memberLoginAction.aws")) {
			System.out.println("memberLoginAction 들어왔나?");
			
			
			String memberId = request.getParameter("memberid");   // 값을 받아오기 
			String memberPw = request.getParameter("memberpw");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLoginCheck(memberId, memberPw);
			System.out.println("mv객체가 생겼나요?" +mv);    //객체가 생겼는지 확인 
			
			if(mv == null ) {
				// 해당하는 값이 없을때 해당 주소로 다시 가세요
				response.sendRedirect(request.getContextPath()+"/member/memberLogin.aws"); 
			}else {
				// 해당되는 로그인 사용자가 있으면 세션에 회원정보를 담아서 메인으로 가라 
				
				String mid = mv.getMemberid(); //아이디 꺼내기
				int midx = mv.getMidx();		// 회원번호 꺼내기
				String memberName = mv.getMembername(); // 이름 꺼내기 
				
				HttpSession session = request.getSession();
				session.setAttribute("mid", mid);
				session.setAttribute("midx", midx);
				session.setAttribute("memberName", memberName);
				
				response.sendRedirect(request.getContextPath()+"/");	// 로그인 되었으면 메인으로 가세용
				}
			}else if(location[2].equals("memberLogout.aws")) {
				System.out.println("memberLogout");
				
				HttpSession session = request.getSession();
				session.removeAttribute("mid");
				session.removeAttribute("midx");
				session.removeAttribute("memberName");
				session.invalidate(); // 모든걸 초기화 시킨다.
				
				response.sendRedirect(request.getContextPath()+"/");
			}
	}
	
	
	// 보이지 않는
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
