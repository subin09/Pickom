package crawler.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
		
		public Crawler() {
			
			try {
				GetTag();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void GetTag() throws Exception{
			
			//for(int i=194470; i<194478; i++) {
				
				// 영화 페이지 DOC
				String url =  "https://movie.naver.com/movie/bi/mi/basic.nhn?code="+187322;
				Document doc = Jsoup.connect(url).get();
				
				// 영화 한글 제목
				Elements movieKoTitle = doc.select("title");
				String title = movieKoTitle.text();
				title = title.substring(0, title.length()-9);
				//System.out.println(title);
				
				// 영화 영어 제목
				Elements movieEnTitle = doc.select(".mv_info > Strong.h_movie2");
				String title2 = movieEnTitle.text();
				title2 = title2.substring(0, title2.length()-6);
				//System.out.println(title2);
				
				// 장르, 러닝타임, 국가, 개봉일
				Elements genres = doc.select("dd > p > span");
				List<Element> step1 = new ArrayList<Element>(genres);
				System.out.println(step1.toString());
				
				String moName = step1.get(1).text().trim();
				String moName2 = step1.get(2).text().trim();
				String moName0 = step1.get(0).text().trim();
				System.out.println(moName);
				System.out.println(moName2);
				//System.out.println(moName0);
				
				String[] split = moName0.split(", ");
				System.out.println(Arrays.toString(split));
				System.out.println(split[0]);
				
				// 감독
				Elements director = doc.select("dd > p > a");
				String link = director.attr("href");
				System.out.println(director);
				System.out.println(link);
				
				// 줄거리
				Elements context = doc.select("p.con_tx");
				String context1 = context.html();
				//System.out.println(context1);
			//}
		}
}
