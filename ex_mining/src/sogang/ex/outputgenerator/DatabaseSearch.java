package sogang.ex.outputgenerator;

import java.sql.*;

/*
 * 테이블 Index에 따른 정보들
 * 1 : ID
 * 2 : Bank Name
 * 3 : Nation Code (USA, Korea, ...)
 * 4 : Money Code (USD, KRW, ...)
 * 5, 6 : 송금 환전 시 값
 * 7, 8 : 현금 환전 시 값
 * 9  : 
 * 10 : 달러 기준
 * 11 : Data 들어간 시
 */

public class DatabaseSearch {

	// MySQL 로그인 정보, 연결 정보, 결과 출력 
	private final String url= "jdbc:mysql://127.0.0.1:3306";
	private final String id= "root";// 사용자계정
	private final String pwd= "root";// 사용자 패스워드	
    private Connection conn;
    private Statement state;
    private ResultSet result;
    
    // 검색 시 사용되는 스트링
    private String whatYear, whatMonth, whatDay;
    private String whatNation;
    private String whatMoneyCode;

    // 저장용 리슽
    List<Exchange> ex;
    static enum TransferType {TRANSFER_SEND, TRANSFER_RECEIVE, CASH_SEND, CASH_RECEIVE}; 
    
    public static void main(String[] args) throws SQLException {
    	new DatabaseSearch();
    }
    
    public DatabaseSearch() throws SQLException {
    	MySQLLogin();	// 처음 클래스 생성 시 MySQL에 로그인
    	StringBuffer strTemp; // temp
    	System.out.println("ID\t" + "Bank\t" + "Nation\t" + "Code\t"
    			+ "TRA_S\t" + "TRA_R\t" + "CASH_S\t" + "CASH_T\t"
    			+ "Stand\t" + "USDRate\t" + "Date");
    	
    	
    	//this.searchBank("woori");
    	//a = this.searchBank("shinhan");    	
    	//a = this.searchDate("2013", "12", "04");
    	//strTemp = findMaximum_NationAndDate(TransferType.CASH_SEND, "미국", "2013", "12", "05");
    	strTemp = searchMoneyCode("CNY");
    	
    	System.out.print(strTemp);
    }
    

    // 지정한 날 기준으로, 지정한 Type 기준으로 오름차순으로 정렬해준다
    public StringBuffer findMaximum_NationAndDate (
    		TransferType type, String Nation,
    		String wantYr, String wantMn, String wantDay) throws SQLException {
    	String Year, Month, Day;
    	StringBuffer ret = new StringBuffer();
    	result = state.executeQuery("select * from exchange where nation = \'" + Nation + '\'');
    	
    	switch(type) {
    	case TRANSFER_SEND:
    		result = state.executeQuery
    		("select * from exchange where nation = \'" + Nation + '\'' + "order by transferSend DESC");
    		break;
    	case TRANSFER_RECEIVE:
    		result = state.executeQuery
    		("select * from exchange where nation = \'" + Nation + '\'' + "order by transferReceive DESC");
    		
    		break;
    	case CASH_SEND:
    		result = state.executeQuery
    		("select * from exchange where nation = \'" + Nation + '\'' + "order by cashSend DESC");
    		
    		break;
    	case CASH_RECEIVE:
    		result = state.executeQuery
    		("select * from exchange where nation = \'" + Nation + '\'' + "order by CashReceive DESC");
    		
    		break;
    	}
    	while(result.next()) {
	       	Year = result.getString(11).substring(0, 4);
	    	Month = result.getString(11).substring(5, 7); 
	    	Day = result.getString(11).substring(8, 10);
		    if (wantYr.equals(Year) && wantMn.equals(Month) && wantDay.equals(Day)) {
		    	for(int i=1; i<12; i++) {
		    		ret.append("<TD>");
					ret.append(result.getString(i));
					ret.append("</TD>");
				}
		    	ret.append('\n');
		    	break;	// 최대 값 찾으면 종료
	    	}
    	}
    	return ret;
    }
    
    
    // 일치하는 통화 코드에 대한 값만 리턴
    public StringBuffer searchMoneyCode(String MoneyCode) throws SQLException {
		result = state.executeQuery("select * from exchange where monetaryCode = \'" + MoneyCode + '\'');
		
		StringBuffer ret = new StringBuffer();
		
		while(result.next()) {
			for(int i=1; i<12; i++) {
	    		ret.append("<TD>");
				ret.append(result.getString(i));
				ret.append("</TD>");				
			}
			ret.append("\n");
		}
		return ret;
    }
    
    public StringBuffer searchBank(String Bank) throws SQLException {
		result = state.executeQuery("select * from exchange where bank = \'" + Bank + '\'');
		StringBuffer ret = new StringBuffer();
		while(result.next()) {
			for(int i=1; i<12; i++) {
	    		ret.append("<TD>");
				ret.append(result.getString(i));
				ret.append("</TD>");
			}
			ret.append("\n");
		}
		return ret;
    }
    
    
    public StringBuffer searchNationCode(String Nation) throws SQLException {
		result = state.executeQuery("select * from exchange where nation = \'" + Nation + '\'');		
		StringBuffer ret = new StringBuffer();
		
		while(result.next()) {
			for(int i=1; i<12; i++) {
	    		ret.append("<TD>");
				ret.append(result.getString(i));
				ret.append("</TD>");
			}
			
			ret.append("\n");
		}
		return ret;
    }
    
    //일치하는 날짜에 대한 값만 리턴
    public StringBuffer searchDate(String wantYr, String wantMn, String wantDay) throws SQLException {
    	String Year, Month, Day;
    	result = state.executeQuery("select * from exchange");
    	StringBuffer ret = new StringBuffer();
    	while(result.next()) {
    		
        	Year = result.getString(11).substring(0, 4);
        	Month = result.getString(11).substring(5, 7); 
        	Day = result.getString(11).substring(8, 10);
			if (wantYr.equals(Year) && wantMn.equals(Month) && wantDay.equals(Day)) {
				for(int i=1; i<12; i++) {
		    		ret.append("<TD>");
					ret.append(result.getString(i));
					ret.append("</TD>");
				}
				ret.append("\n");
				System.out.println();
			}
		}
    	return ret;
    }
    
	private void MySQLLogin() {	
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
			 * 5, 6 : 송금 환전 시 값
			 * 7, 8 : 현금 환전 시 값
			 * 9  : 
			 * 10 : 달러 기준
			 * 11 : Date
			 */
			
			
			// Test //
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	// 검색 시 사용하는 날짜, 통화 코드, 국가 코드 등을 set/get
	public void setWhatDate(String yr, String mn, String dy) {
		this.whatYear = yr;
		this.whatMonth = mn;
		this.whatDay = dy;
	}	
	public String getWhatDate() {
		return this.whatYear + '-' + this.whatMonth + '-' + this.whatDay;		
	}
	
	public void setWhatMoneyCode(String code) {
		this.whatMoneyCode = code;
	}
	
	public String getWhatMoneyCode() {
		return this.whatMoneyCode;
	}
	
	public void setWhatNation(String nat) {
		this.whatNation = nat;
	}
	
	public String getWhatNation() {
		return this.whatNation;
	}
	
}
