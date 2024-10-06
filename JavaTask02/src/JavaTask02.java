import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JavaTask02 {
	static final String DB_URL =  "jdbc:mysql://localhost/causwtask2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String USER = "root"; // user name
	static final String PASS = "mysql"; // user password

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Scanner scanner = new Scanner(System.in);
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS); 
			
			System.out.print("검색할 빌딩 이름을 입력하세요: ");
            String buildingName = scanner.nextLine();
            
			String query = "SELECT floor, number, capacity FROM room WHERE building = ?"; // input query
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, buildingName);
			rs = stmt.executeQuery();
			
			System.out.println("검색 결과:");
            while (rs.next()) {
                int floor = rs.getInt("floor");
                int number = rs.getInt("number");
                int capacity = rs.getInt("capacity");
                System.out.println("floor: " + floor + ", number: " + number + ", capacity: " + capacity);
            }
			} catch (SQLException e) {
				System.out.println("SQLException : " + e);
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						
					}
			}
	}
	
//	public static void main(String[] args) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = DriverManager.getConnection(DB_URL, USER, PASS);
//			String sql = "insert into student values (?,?,?,?)";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "20150008");
//			pstmt.setString(2, "Cho");
//			pstmt.setInt(3, 15);
//			pstmt.setString(4, "CSE");
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("SQLException : " + e);
//			
//		} finally {
//			try {
//				pstmt.close();
//				conn.close();
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//				
//			}
//			
//		}
//		
//	}
}
