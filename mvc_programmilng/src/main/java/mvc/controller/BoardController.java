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
				//System.out.println("boardWrite");
				
				paramMethod ="F";  // 포워드 방식은 내부에서 공유하는 것이기 때문에 내부에서 활동하고 이동한다.
				url= "/board/boardWrite.jsp"; 
			}else if(location.equals("boardWriteAction.aws")) { //boardWriteAction.aws 요청이 들어왔을 때, 
															    //사용자가 작성한 글의 정보를 파라미터로 받아서 처리할 준비를 하는 부분
				//System.out.println("boardWriteAction.aws");
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
				/* 이거 필여없으면 지우세여 
				 * paramMethod ="S"; url= request.getContextPath()+"/board/boardList.aws";
				 */ 
			}else if(location.equals("boardContents.aws")) {  //게시물 내용 넘어노느거
				//System.out.println("boardContents.aws");
				
				// 1. 넘어온 값 받기
				String bidx = request.getParameter("bidx");   // String 타입으로 bidx 받아오기
				//System.out.println("bidx -->" + bidx);       // 값이 넘어왔는지 확인
				int bidxInt = Integer.parseInt(bidx);	// 숫자형으로 되어있는 문자를 다시 바꿔준다. 
				//System.out.println("CobidxInt"+bidxInt);
				// 2. 처리하기
				BoardDao bd = new BoardDao(); // 객체 생성하고 
				BoardVo bv = bd.boardSelectOne(bidxInt); //다 문자형으로 넘어오기 때문에 숫자형으로 바궈줘야 한다. 생성한 메소드 호출
				
				
				
				request.setAttribute("bv",bv);	// 포워드 방식이라 같은 영역안에 있어서 공유해서 jsp페이지에서 꺼내 쓸 수 있다.
				//System.out.println("Cobv"+bv);
				
				
				// 3. 이동해서 화면 보여주기
				paramMethod = "F";   // 화면을 보여주기 위해서 같은 영역 내부안에 jsp페이지를 보여준다. 
				url= "/board/boardContents.jsp"; // 포워드 방식이기 때문에 바로 보여주는거라 리퀘스트 겟 컨텐츠 안해도된다. 	
			}else if(location.equals("boardModify.aws")) {  // 글 수정 화면으로 넘어가는거
				//System.out.println("boardModify.aws");
				
				String bidx = request.getParameter("bidx"); // 파라미터 bidx값 가져오기 
				int bidxInt = Integer.parseInt(bidx);	// 원래 숫자형인데 문자열로 가져왔어서 다시 숫자형으로 변경
				BoardDao bd = new BoardDao();	// 객체 생성하기
				BoardVo bv = bd.boardSelectOne(bidxInt); // 회원 정보를 가져오는 메소드 호출
				
				request.setAttribute("bv",bv);	// 포워드 방식이라 같은 영역안에 있어서 공유해서 jsp페이지에서 꺼내 쓸 수 있다.
				
				paramMethod = "F"; 
				url="/board/boardModify.jsp";	
				

			}else if(location.equals("boardModifyAction.aws")) {  //글 수정 해서 값 넘겨받고 객체에 새로 담는다.
				System.out.println("boardModifyAction.aws");
				
				// 1. 파라미터 값을 넘겨받는다. 파라미터 값은 무조건 문자형으로 받는다.
				String subject = request.getParameter("subject");
				String contents = request.getParameter("contents"); // 인터넷 통신을 통해서 넘어오는 것들은 다 문자형으로 넘어온다.
				String writer = request.getParameter("writer");
				String password = request.getParameter("password");	// 비밀번호가 맞는지 체크를 해야한다. 
				String bidx = request.getParameter("bidx");
				int bidxInt = Integer.parseInt(bidx);	// 원래 숫자형인데 문자열로 가져왔어서 다시 숫자형으로 변경
				
				BoardDao bd = new BoardDao();	// 객체 생성하기
				BoardVo bv = bd.boardSelectOne(bidxInt); // 회원 정보를 가져오는 메소드 호출
			
				paramMethod="S";  // 전역적으로 쓰면 됩니다. 
				// 비밀번호 체크 
				if(password.equals(bv.getPassword())) {
					// 같으면
					BoardDao bd2 = new BoardDao(); // 객체 생성 또 하나 하고 
					BoardVo bv2 = new BoardVo();  // 여기에 넘어온 값을 담기
					
					bv2.setSubject(subject);
					bv2.setContents(contents);
					bv2.setWriter(writer);			// 이거 담을거임
					bv2.setPassword(password);
					bv2.setBidx(bidxInt);
					int value = bd2.boardUpdate(bv2);
					
					if(value == 1) {  //입력성공
						url= request.getContextPath()+"/board/boardContents.aws?bidx="+bidx;	
					}else {  //입력실패
						url= request.getContextPath()+"/board/boardModify.aws?bidx="+bidx;		
					}		
					
				} else { // 비밀번호가 다르면 
					url = request.getContextPath() + "/board/boardModify.aws?bidx=" + bidx;
				}
			
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
