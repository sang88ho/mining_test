package sogang.ex.mining.data;

import java.io.IOException;

public class 기업Test {
	public static void main(String[] args) throws IOException   {
		String html = PageData.getAllDataFromTargetPage("http://www.ibk.co.kr/fxtr/excRateList.ibk");
		System.out.println(html);
	}
}
