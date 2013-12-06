package pageInformationGetter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetAllPageData {

	private static URL conn;
	
	public static void main(String[] args) throws IOException   {
		//GetAllPageData("http://fx.keb.co.kr/FER1101C.web?schID=fex&mID=FER1101C");
		GetAllPageData gt = new GetAllPageData("https://okbfex.kbstar.com/quics?page=C015690");
		gt.getAllDataFromTargetPage();
	}
	
	public GetAllPageData() {}
	public GetAllPageData(String pageURL) throws IOException {
		conn = new URL(pageURL);
	}
	
	public static void getAllDataFromTargetPage() throws IOException {
		getAllDataFromTargetPage("C:\\DefaultDataTextFile.txt");	
	}
	
	public static void getAllDataFromTargetPage(String saveTxtFileName) throws IOException {
	
		BufferedWriter file = new BufferedWriter(new FileWriter(saveTxtFileName)); 
		// 긁어오려는 주소 입력
		URLConnection con = conn.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String line = null;
		while (( line = in.readLine()) != null) {
			System.out.println(line);
			file.write(line);
		}
		in.close();		
	}
	
}
