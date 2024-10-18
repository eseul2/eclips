package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.dbcon.Dbconn;
import mvc.vo.BoardVo;
import mvc.vo.MemberVo;

public class BoardDao {
	
	
	private Connection conn; // 4. 연결 객체를 전역적으로 쓴다.
	private PreparedStatement pstmt; // 8. 전역변수를 설정
	
	// 0. 컨트롤러를 생성 했으면 그 다음 dao를 생성해라 
	public BoardDao() { // 1. 맨처음 생성자를 만든다. 왜? : DB연결하는 Dbconn 객체 생성하기 위해... 
						//생성을 해야 mysql접속이 가능하다
	
		Dbconn db = new Dbconn();  // 2. conn 연결 객체 생성하기
		this.conn = db.getConnection(); // 3. 메소드를 실행해서 연결하기 
	}
	
	
	
	public ArrayList<BoardVo> boardSelectAll() { // 4. 구문 클래스 생성하기: 형식부터 만들어라
		
		// 5. ArrayList 컬렉션 객체에 보드Vo를 담겠다. 보드VO는 컬럼값을 담겠다.
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();    
						
		// 6. db에서 작성한 쿼리를 가져온다
		String sql = "select * from board order by originbidx desc, depth asc"; 
		ResultSet rs =null; // 7. DB값을 가져오기 위한 전용 클래스 
		
		try {
			pstmt = conn.prepareStatement(sql); // 9. SQL문을 실행하기 위해 준비하는 코드
			rs = pstmt.executeQuery(); // 10. 데이터베이스 쿼리를 실행하고, 그 결과를 ResultSet 객체에 저장한다.
			
			//11. 여러개의 값을 담아야 하기 때문에 반복문으로 작성해야한다. 
			while(rs.next()) { // 12. 커서가 다음으로 이동해서 첫 글이 있느냐 물어보고 true면 진행
				int bidx = rs.getInt("bidx");
				String subject = rs.getString("subject");
				String contents = rs.getString("contents");
				String writer = rs.getString("writer");
				int viewcnt = rs.getInt("viewcnt");
				String writeday = rs.getString("writeday");
				
				BoardVo bv = new BoardVo();	// 13. 첫 행부터 vb에 옮겨담기
				bv.setBidx(bidx);
				bv.setSubject(subject);
				bv.setContents(contents); 
				bv.setWriter(writer); 
				bv.setViewcnt(viewcnt);
				bv.setWriteday(writeday);
				alist.add(bv);   // 14. ArrayList 객체에 bv값을 하나씩 넣는다.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();	 // 15. 연결을 끊어줘야 합니다. 다른 프로그램 사용자가 사용할 수 없음. 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alist; // alist에 값을 담는다.
	}
	
	
	//17. 이제 메소드 만든걸 컨트롤러에서 불러야겠죠?? 컨트롤러로 갑니다
	

}
