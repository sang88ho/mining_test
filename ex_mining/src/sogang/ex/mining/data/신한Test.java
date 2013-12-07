package sogang.ex.mining.data;

import java.io.IOException;

public class 신한Test {
	
	public static void main(String[] args) throws IOException   {
		String html = PageData.getAllDataFromTargetPage("http://banking.shinhan.com/websquare/websquare_contents.jsp?w2xPath=/contents/forcenter/fx_rate/finance_exchange_list001.xml&amp;cid=E50058&amp;mid=B50001&amp;sid=C50002");
		System.out.println(html);
	}
}
