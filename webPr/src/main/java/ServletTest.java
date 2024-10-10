

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
		// 한글 깨지는거 막아주는 코드 
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println("서블릿 테스트입니다.");
	
	PrintWriter out = response.getWriter();
	out.println("안녕하세요 서블릿 웹페이지 입니다.");
	out.println("한글 테스트");
	
	out.println("<!doctype html>"
			+ "<html>"
			+ "<head>"
			+ "<title>제목란</title>"
			+ "</head>"
			+ "<body><span style= \"color:red;font-size:20px;\">내용</body>"
			+ "</html>");
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
