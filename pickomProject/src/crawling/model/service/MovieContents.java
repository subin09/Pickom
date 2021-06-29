package crawling.model.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieContents {
	
	public MovieContents() {
		try {
			GetTag();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void GetTag() throws Exception{
		
		for(int i=194470; i<194478; i++) {
			String url =  "https://movie.naver.com/movie/bi/mi/basic.nhn?code="+i;
			Elements contents = null;
			Document doc = Jsoup.connect(url).get();
			
			String source_id = "title";
			contents = doc.select(source_id);
			String title = contents.text();
			
			title = title.substring(0, title.length()-9);
		}
	}
}
