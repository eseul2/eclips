package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dao.BoardDao;
import mvc.dao.MemberDao;
import mvc.vo.BoardVo;
import mvc.vo.Criteria;
import mvc.vo.MemberVo;
import mvc.vo.PageMaker;

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
			
			String page = request.getParameter("page"); // 2-14.
			if (page == null) page= "1";  // 2-15. 실행문이 하나일때 요렇게 써도 된다. 
			int pageInt = Integer.parseInt(page); // 2-34. 문자를 숫자로 변경한다. 
			
			
			Criteria cri = new Criteria();
			cri.setPage(pageInt);
			
			PageMaker pm = new PageMaker();
			pm.setCri(cri);			// 2-35. PageMaker에 Criteria 담아서 가지고 다닌다. 

			
			BoardDao bd = new BoardDao();
			// 2-4. 페이징 처리하기 위한 전체 데이터 갯수 가져오ㅁ기 
			int boardCnt = bd.boardTotalCount(); 
		//	System.out.println("게시물 수는? " + boardCnt); // 2-13.
			pm.setTotalCount(boardCnt);		// 2-36. PageMaker에 전체게시물 수를 담아서 페이지 계산.
			
			//BoardDao bd = new BoardDao();   // 18. Dao에서 만든 메소드 불러오기 
			ArrayList<BoardVo> alist = bd.boardSelectAll(cri);  // 19. 불러왔다~    2-42. cri 매개변수 넣기 
		//	System.out.println("alist ==>" + alist);  // 20.객체주소가 나오면 객체가 생성된 것을 짐작할 수 있다
  			
			request.setAttribute("alist", alist);  // 화면까지 가지고 가기 위해 request 객체에 담는다.
			request.setAttribute("pm",pm);    	// 2-37. forward방식으로 넘기기 때문에 공유가 가능하다.  그리고 보드 리스트로 이동
			
			// 21. 이제 jsp로 가서 설정을 해줘야  합니다.
			
			 paramMethod ="F";   
			 url="/board/boardList.jsp"; // 실제 내부경로
			
			}else if(location.equals("boardWrite.aws")) {   //글쓰기 경로로 가세용 
				System.out.println("boardWrite");
				
				paramMethod ="F";  // 포워드 방식은 내부에서 공유하는 것이기 때문에 내부에서 활동하고 이동한다.
				url= "/board/boardWrite.jsp"; 
			}else if(location.equals("boardWriteAction.aws")) { //boardWriteAction.aws 요청이 들어왔을 때, 
															    //사용자가 작성한 글의 정보를 파라미터로 받아서 처리할 준비를 하는 부분
				System.out.println("boardWriteAction.aws");
				// 1. 파라미터 값을 넘겨받는다. 
				String subject = request.getParameter("subject"); //서브젝트라는 이름의 요청 파라미터를 가져와 변수에 저장
				String contents = request.getParameter("contents");
				String writer = request.getParameter("writer");
				String password = request.getParameter("password");
				
				HttpSession session = request.getSession();   // 세션 객체를 불러와서 
				int midx = Integer.parseInt(session.getAttribute("midx").toString()); // 로그인 할 때 담았던 세션변수 midx값을 꺼낸다.
				
				BoardVo bv = new BoardVo();
				bv.setSubject(subject);
				bv.setContents(contents);
				bv.setWriter(writer);
				bv.setPassword(password);
				bv.setMidx(midx);
				
				//2. DB처리한다.
				BoardDao bd = new BoardDao();
				int value = bd.boardInsert(bv);
				
				if(value == 2) {  //입력성공
					paramMethod="S";
					url= request.getContextPath()+"/board/boardList.aws";
				}else {  //실패했으면
					paramMethod="S";
					url= request.getContextPath()+"/board/boardWrite.aws";			
				}		
				
				//3. 처리후 이동한다. sendRedirect
				paramMethod ="S";   
				url= request.getContextPath()+"/board/boardList.aws"; 
			}
		
		
		
		
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
