package sogang.ex.outputgenerator;

import java.net.URLConnection;
import java.sql.*;

public class DatabaseSearch {

	private String url= "jdbc:mysql://127.0.0.1:3306";
	private String id= "root";// 사용자계정
	private String pwd= "root";// 사용자 패스워드
    private Connection conn;
    private Statement state;
    private ResultSet result;
    
    public static void main(String[] args) {
    	new DatabaseSearch();
    }
    
    public DatabaseSearch() {
    	MySQLLogin();
    }
    
	public void MySQLLogin() {	
		// MySQL 연동		
		try {
		// MySQL 접속
			try {
				Class.forName("com.mysql.jdbc.Driver");
				}  catch (ClassNotFoundException e) {
	        	 e.printStackTrace();	         
		         }
	         
			this.conn  = DriverManager.getConnection(url, id, pwd);
			state = conn.createStatement();
			// 여기까지 실행되면 접속 성공			
			state.execute("use ip"); // 사용할 데이터베이스
			// Table : exchange
			
			/*
			 * 테이블 Index에 따른 정보들
			 * 1 : ID
			 * 2 : Bank Name
			 * 3 : Nation Code (USA, Korea, ...)
			 * 4 : Money Code (USD, KRW, ...)
			 * 5, 6 : 송금 시 송/수
			 * 7, 8 : 현금 시 송/
			 * 9  : 
			 * 10 : 달러 기준 호나
			 * 11 : Date
			 */
			
			
			// Test //
			result = state.executeQuery("select * from exchange");
		
			while(result.next()) {
				System.out.println(result.getString(1));
			}			
				// Test //
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
