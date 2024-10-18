package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.dao.BoardDao;
import mvc.dao.MemberDao;
import mvc.vo.BoardVo;
import mvc.vo.MemberVo;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String location;	// 멤버변수(전역) 초기화 => 이동할 페이지 
	
	
	//매개변수가 있는 생성자
	public BoardController(String location) {
		this.location = location;
	}
 
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramMethod ="";   // 전송방식이 sendRedirect면 S라고 하고, forward방식이면 F로 값을 받을것이다. 
		String url="";
		
		if(location.equals("boardList.aws")) { //가상경로 
			
			BoardDao bd = new BoardDao();   // 18. Dao에서 만든 메소드 불러오기
			ArrayList<BoardVo> alist = bd.boardSelectAll();  // 19. 불러왔다~
			System.out.println("alist ==>" + alist);  // 20.객체주소가 나오면 객체가 생성된 것을 짐작할 수 있다
  			
			request.setAttribute("alist", alist);
			
			// 21. 이제 jsp로 가서 설정을 해줘야  합니다.
			
			 paramMethod ="F";   
			 url= request.getContextPath()+"/board/boardList.jsp"; // 실제 내부경로
			
		}else if(location.equals("boardWrite.aws")) { 
				System.out.println("들어왔나?");
			url = "/board/boardWrite.jsp";
			paramMethod = "F";   //하단에서 포워드로 처리합니다.
		}
		
		
		
	
			
		
			
			
			String memberId = request.getParameter("memberid");   // 값을 받아오기 
			String memberPw = request.getParameter("memberpw");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLoginCheck(memberId, memberPw);
		//	System.out.println("mv객체가 생겼나요?" +mv);    //객체가 생겼는지 확인 
		
		
		
		
		
		if(paramMethod.equals("F")) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
		    rd.forward(request, response);      //f면 리퀘스트로 
		} else if(paramMethod.equals("S")){
			response.sendRedirect(url);  
		
		}
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
