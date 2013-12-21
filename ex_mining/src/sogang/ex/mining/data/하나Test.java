package sogang.ex.mining.data;

import java.io.IOException;

public class 하나Test {

	public static void main(String[] args) throws IOException   {
		String html = PageData.getAllDataFromTargetPage("http://www.hanabank.com/app/portal/mkt/rte_p061.do");
		System.out.println(html);
	}
	
}
