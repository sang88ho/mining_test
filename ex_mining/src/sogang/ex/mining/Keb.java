package sogang.ex.mining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sogang.ex.mining.exchange.Exchange;

public class Keb {

	public static void main(String[] args) throws IOException, ParseException {
		//Connection.Response res = Jsoup.connect("http://fx.keb.co.kr/FER1101C.web?schID=fex&mID=FER1101C").method(Method.POST).execute();
		URLConnection conn = new URL("http://fx.keb.co.kr/FER1101C.web?schID=fex&mID=FER1101C").openConnection();
		conn.addRequestProperty("Referrer", "http://fx.keb.co.kr");

		// 브라우저에서 파일 다운로드 받는 듯이 구현하여 해당 파일이 값이 나오는지테스트 
		
		
		Document doc = Jsoup.connect("http://fx.keb.co.kr/FER1101C.web?schID=fex&mID=FER1101C")
				.referrer("Referrer")
				//.referrer("http://banking.shinhan.com")
				.get();
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        //BufferedWriter bw = new BufferedWriter();
        		//(response.getWriter());
		
		
		//"http://banking.shinhan.com/contents/forcenter/fx_rate/finance_exchange_view001.xml")
		//"http://banking.shinhan.com/contents/forcenter/fx_rate/finance_exchange_list001.xml")
		
		
		
		//doc = Jsoup.connect("").post()
		//System.out.println(asdf);
		// 데이터가 있는 주소
		
		System.out.println(doc.data());
		
		System.out.println("Done");
		/*
		Elements ddElements = doc.getElementsByTag("dd");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date date = sdf.parse(ddElements.get(0).text());
		
		Elements contents = doc.getElementsByTag("td");

		int i = 0;
		boolean find = false;

		List<Exchange> exhanges = new ArrayList<Exchange>();
		Exchange ex = null;
		for (Element content : contents) {
			String text = content.text();
			if (text.equals("USD")) {
				i = 0;
				find = true;
				ex = new Exchange("woori", "미국", "USD", date);
				exhanges.add(ex);
			} else if (text.equals("JPY")) {
				i = 0;
				find = true;
				ex = new Exchange("woori", "일본", "JPY", date);
				exhanges.add(ex);
			} else if (text.equals("EUR")) {
				i = 0;
				find = true;
				ex = new Exchange("woori", "유럽연합", "EUR", date);
				exhanges.add(ex);
			} else if (text.equals("CNY")) {
				i = 0;
				find = true;
				ex = new Exchange("woori", "중국", "CNY", date);
				exhanges.add(ex);
			}
			
			if (find) {
				if(text.contains(",")) {
					StringTokenizer st = new StringTokenizer(text, ",");
					
					String removedText = "";
					while (st.hasMoreElements()) { 
						removedText += st.nextToken();
					}
					
					text = removedText;
				}

				switch (i) {
				case 2:
					ex.setTransferSend(Float.parseFloat(text));
					break;

				case 3:
					ex.setTransferReceive(Float.parseFloat(text));
					break;
					
				case 4:
					ex.setCashReceive(Float.parseFloat(text));
					break;
					
				case 5:
					ex.setCashSend(Float.parseFloat(text));					
					break;
					
				case 7:
					ex.setSaleStandard(Float.parseFloat(text));
					break;
					
				case 9:
					find = false;
					ex.setUsdExchangeRate(Float.parseFloat(text));
					break;
					
				default:
					break;
				}
				++i;
			}
		}
		*/
	}
}
