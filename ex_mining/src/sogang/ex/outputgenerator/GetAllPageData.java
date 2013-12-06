package sogang.ex.outputgenerator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;

public class GetAllPageData {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		
		// for test
		BufferedWriter file = new BufferedWriter(new FileWriter("C:\\data.txt")); 
		
		URL conn = new URL("https://okbfex.kbstar.com/quics?page=C015690");
		// 긁어오려는 주소 입력
		URLConnection con = conn.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String line = null;
		while (( line = in.readLine()) != null) {
			System.out.println(line);
			file.write(line);
		}
		in.close();
		
		
		// 아래부턴 데이터베이스 연동. 테스트용.
		/*
        String url= "jdbc:mysql://127.0.0.1:3306";
        String id= "root";// 사용자계정
        String pwd= "root";// 사용자 패스워드
		
        
        // MySQL 연동
		try {
			// MySQL 접속
	         try {
	        	 Class.forName("com.mysql.jdbc.Driver");
	         }  catch (ClassNotFoundException e) {
	        	 e.printStackTrace();	         
	         }
	         
			conn  = DriverManager.getConnection(url, id, pwd);
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
			/*
			
			// Test //
			result = state.executeQuery("select * from exchange");
		
			while(result.next()) {
				System.out.print(result.getString(10));
			}			
			// Test //
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		
	}
	
}
