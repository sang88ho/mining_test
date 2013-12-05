package sogang.ex.mining;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sogang.ex.mining.exchange.Exchange;

public class Woori {

	public static void main(String[] args) throws IOException, ParseException {
		Document doc = Jsoup.connect("https://spot.wooribank.com/pot/jcc?withyou=FXXRT0011&__ID=c008329").get();
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
		
		for (Exchange exchange : exhanges) {
			System.out.println(exchange.toString());
		}
	}
}
