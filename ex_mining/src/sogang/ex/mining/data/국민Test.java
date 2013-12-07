package sogang.ex.mining.data;

import java.io.IOException;

public class 국민Test {
	
	public static void main(String[] args) throws IOException   {
		String html = PageData.getAllDataFromTargetPage("https://okbfex.kbstar.com/quics?page=C015690");
		System.out.println(html);
	}
}
