package sogang.ex.mining.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PageData {

	private static final String DEFAULT_RESULT_PATH = "./src/sogang/ex/mining/data/result.txt";
	
	public static String getAllDataFromTargetPage(String url) throws IOException {
		return getAllDataFromTargetPage(url, DEFAULT_RESULT_PATH);
	}
	
	public static String getAllDataFromTargetPage(String bankUrl, String filename) throws IOException {
		URL url = new URL(bankUrl);

		URLConnection conn = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));


		StringBuffer buffer = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			buffer.append(line).append("\n");
		}
		in.close();
		
		if (filename != null) {
			BufferedWriter file = new BufferedWriter(new FileWriter(filename));
			file.write(buffer.toString());
			file.close();
		}
		
		return buffer.toString();
	}
}
