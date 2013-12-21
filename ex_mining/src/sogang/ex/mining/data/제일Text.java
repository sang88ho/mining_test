package sogang.ex.mining.data;

import java.io.IOException;

public class 제일Text {
	public static void main(String[] args) throws IOException   {
		//String html = PageData.getAllDataFromTargetPage("https://www.standardchartered.co.kr/hp/kr/fp/pr/HP_FP_PR_ExchangeRateList.jsp");
		String html = PageData.getAllDataFromTargetPage("https://www.standardchartered.co.kr/np/kr/pl/et/ExchangeRateP1.jsp");
		//String html = PageData.getAllDataFromTargetPage("http:/fx.standardchartered.co.kr/exrate/RtdEXHO02.jsp");
		
		System.out.println(html);
	}
}
