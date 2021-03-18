package cho.practice;

import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleEx01 {

	public static void main(String[] args) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "SYSTEM"; 
		String password = "c1217";
		try {
			Class.forName(driver);
			System.out.println("ojdbc driver 로딩 성공");
			DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오라클 연결 실패");
		}

	}

}
