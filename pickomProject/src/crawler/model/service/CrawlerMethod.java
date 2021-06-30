package crawler.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerMethod {

	public CrawlerMethod() {

		try {
			// movieBasic();
			// movieActor();
			// movieDetail();
			// movieGenre();
			movieFileLink();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void movieFileLink() throws Exception {

		int i = 187310;

		// 영화 페이지 DOC
		String urlPicture = "https://movie.naver.com/movie/bi/mi/basic.nhn?code="+i;
		String urlStillCut = "https://movie.naver.com/movie/bi/mi/photoView.nhn?code="+i;
		String urlMedia = "https://movie.naver.com/movie/bi/mi/media.nhn?code="+i;
		Document docPicture = Jsoup.connect(urlPicture).get();
		Document docStillCut = Jsoup.connect(urlStillCut).get();
		Document docMedia = Jsoup.connect(urlMedia).get();

		Elements elPoster = docPicture.select(".mv_info_area .poster a img");
		Elements elStillCut = docStillCut.select(".list_area .rolling_list li > a > img");
		Elements elMedia = docMedia.select(".video_thumb li p.tx_video a, .v_list li p.tx_video > a");

		ArrayList<String> arrPoster = new ArrayList<String>();
		ArrayList<String> arrStillCut = new ArrayList<String>();
		ArrayList<String> arrMedia = new ArrayList<String>();

		// 포스터
		String poster = elPoster.attr("src");
		arrPoster.add(poster);

		// 스틸컷
		for(int j=0; j<elStillCut.size(); j++ ) {
			String delSize = "n74_74_2";
			String saveSize = "m665_443_2";
			String stillCut = elStillCut.get(j).attr("src");
			stillCut = stillCut.substring
					(0, stillCut.length()-delSize.length());
			if(!stillCut.matches(delSize)) {
				stillCut = stillCut+saveSize;
				arrStillCut.add(stillCut);	
			}
		}

		// 영상
		System.out.println(elMedia.attr("href"));
		for(int j=0; j<elMedia.size(); j++ ) {
			String mediaAtt = "https://movie.naver.com";
			String media = mediaAtt + elMedia.get(j).attr("href");
			arrMedia.add(media);
		}

		// System.out.println(arrPoster);
		// System.out.println(arrStillCut);
		// System.out.println(arrMedia);
		
		

	}

	/** 영화 상세페이지 Crawler[basic]
	 * @throws Exception
	 */
	public void movieBasic() throws Exception {

		int i = 187310;

		// 영화 페이지 DOC
		String url =  "https://movie.naver.com/movie/bi/mi/basic.nhn?code="+i;
		Document doc = Jsoup.connect(url).get();

		Elements elMovieTitleKo = doc.select("title");
		Elements elMovieTitleEn = doc.select(".mv_info > Strong.h_movie2");
		Elements elDirector = doc.select("dd > p > a");

		ArrayList<String> arrMovieTitleKo = new ArrayList<String>();
		ArrayList<String> arrMovieTitleEn = new ArrayList<String>();
		ArrayList<String> arrMovieDirector = new ArrayList<String>();
		ArrayList<String> arrMovieSummary = new ArrayList<String>();

		// 영화 한글 제목
		String delTitleKo = " : 네이버 영화";
		String movieTitleKo = elMovieTitleKo.text();
		movieTitleKo = movieTitleKo.substring
				(0,movieTitleKo.length()-delTitleKo.length());
		arrMovieTitleKo.add(movieTitleKo);

		// 영화 영어 제목
		String delTitleEn = ", 0000";
		String movieTitleEn = elMovieTitleEn.text();
		movieTitleEn = movieTitleEn.substring
				(0,movieTitleEn.length()-delTitleEn.length());
		arrMovieTitleEn.add(movieTitleEn);

		// 감독
		Element elDirector1 = elDirector.first();
		String director = elDirector1.text();
		arrMovieDirector.add(director);

		// 줄거리
		Elements elSummary = doc.select("p.con_tx");
		String summary = elSummary.html();
		arrMovieSummary.add(summary);

		// System.out.println(arrMovieTitleKo);
		// System.out.println(arrMovieTitleEn);
		// System.out.println(arrMovieDirector);
		// System.out.println(arrMovieSummary);

	}

	/** 영화배우 Crawler[detail]
	 * @throws Exception
	 */
	public void movieActor() throws Exception {

		int i = 187310;	

		// 추출 url
		String url = "https://movie.naver.com/movie/bi/mi/detail.nhn?code="+i;
		Document doc = Jsoup.connect(url).get();

		Elements elActorNmEn = doc.select(".lst_people li .p_info em.e_name");
		Elements elActorNmKo = doc.select(".lst_people li .p_info a.k_name");

		List<Element> liActorNmKo= new ArrayList<Element>(elActorNmKo);
		List<Element> liActorNmEn= new ArrayList<Element>(elActorNmEn);

		ArrayList<String> arrActorNmKo = new ArrayList<String>();
		ArrayList<String> arrActorNmEn = new ArrayList<String>();
		ArrayList<String> arrActorCd = new ArrayList<String>();
		ArrayList<String> arrMovieNo = new ArrayList<String>();	

		// 영화배우 한글 이름
		for(int j=0; j<liActorNmKo.size(); j++ ) {
			String actorNmKo = liActorNmKo.get(j).text().trim();
			arrActorNmKo.add(actorNmKo);
		}

		// 영화배우 영어 이름
		for(int j=0; j<liActorNmEn.size(); j++ ) {
			String actorNmEn = liActorNmEn.get(j).text().trim();
			arrActorNmEn.add(actorNmEn);
		}

		// 영화배우 코드
		boolean flag = true;
		while(flag) {
			for(int j=0; j < liActorNmKo.size(); j++) {

				String hrefActorCd = liActorNmKo.get(j).attr("href");
				String delActor = "/movie/bi/pi/basic.nhn?code=";
				// 장르
				if(hrefActorCd.contains("code=")) {
					String actorCd = hrefActorCd.substring
							(delActor.length());
					arrActorCd.add(actorCd);
				}else {
					break;
				}
				flag = false;
			}
		}
		// 영화번호
		int movieNo = i;
		arrMovieNo.add(movieNo+"");

		// System.out.println(arrActorNmKo);
		// System.out.println(arrActorNmEn);
		// System.out.println(arrActorCd);
		// System.out.println(arrMovieNo);
	}


	/** 영화 요소 Crawler[detail]
	 * @throws Exception
	 */
	public void movieDetail() throws Exception {

		int i = 187310;

		// 추출 url
		String url = "https://movie.naver.com/movie/bi/mi/detail.nhn?code="+i;
		Document doc = Jsoup.connect(url).get();

		Elements elContents1 = doc.select("dd > p > span > a");
		Elements elContents2 = doc.select("dd > p > span");

		List<Element> liContents1 = new ArrayList<Element>(elContents1);
		List<Element> liContents2 = new ArrayList<Element>(elContents2);

		ArrayList<String> arrGenreCd = new ArrayList<String>();
		ArrayList<String> arrMovieCountry = new ArrayList<String>();
		ArrayList<String> arrMovieOpenDt = new ArrayList<String>();
		ArrayList<String> arrRuntime = new ArrayList<String>();

		// 장르, 국가, 개봉일
		boolean flag = true;

		String delGenre = "/movie/sdb/browsing/bmovie.nhn?genre=";
		String delCountry = "/movie/sdb/browsing/bmovie.nhn?nation=";
		String delOpen = "/movie/sdb/browsing/bmovie.nhn?open=";

		while(flag) {
			for(int j=0; j < liContents1.size(); j++) {

				String hrefContents = liContents1.get(j).attr("href");

				// 장르
				if(hrefContents.contains("genre=")) {
					String genreCd = hrefContents.substring
							(delGenre.length());
					arrGenreCd.add(genreCd);

					// 국가
				}else if(hrefContents.contains("nation=")) {
					String movieCountry = hrefContents.substring
							(delCountry.length());
					arrMovieCountry.add(movieCountry);

					// 개봉일
				}else if(hrefContents.contains("open=")) {
					String regExp = "^[0-9]{8}$";
					String movieOpenDt = hrefContents.substring
							(delOpen.length());
					if(movieOpenDt.matches(regExp)) {
						// Date 형으로 추후 변경??
						arrMovieOpenDt.add(movieOpenDt);
					}
				}else {
					break;
				}
				flag = false;
			}
		}
		// 러닝타임
		for(int j=0; j<liContents2.size(); j++) {
			String regExp = "^[1-9]{2,3}[분]$";
			String contents2 = liContents2.get(j).text();
			if(contents2.matches(regExp)) {
				arrRuntime.add(contents2);
				break;
			}
		}
		System.out.println(arrGenreCd);
		System.out.println(arrMovieCountry);
		System.out.println(arrMovieOpenDt);
		System.out.println(arrRuntime);
	}

	/** 장르 Crawler[단독테이블]
	 * @throws Exception
	 */
	public void movieGenre() throws Exception {

		// 추출 url
		String url = "https://movie.naver.com/movie/sdb/browsing/bmovie_genre.nhn";
		Document doc = Jsoup.connect(url).get();

		// 추출 선택자
		Elements selectGenre  = doc.select(".directory_item_other td a");
		List<Element> elGenre = new ArrayList<Element>(selectGenre);

		// 장르이름/번호
		ArrayList<String> arrGenreNm = new ArrayList<String>();
		ArrayList<String> arrGenreCd = new ArrayList<String>();

		// 장르이름
		for(int i=0; i<elGenre.size(); i++ ) {
			String genreNm = elGenre.get(i).text().trim();
			arrGenreNm.add(genreNm);
		}

		// 장르번호
		for(int i=0; i<elGenre.size(); i++ ) {
			if(0<= i && i <10) {
				String hrefGenre = elGenre.get(i).attr("href");
				String genreCd = hrefGenre.substring(hrefGenre.length()-1);
				arrGenreCd.add(genreCd);

			} else if(10<= i) {
				String hrefGenre = elGenre.get(i).attr("href");
				String genreCd = hrefGenre.substring(hrefGenre.length()-2);
				arrGenreCd.add(genreCd);
			}
		}
		System.out.println(arrGenreNm);
		System.out.println(arrGenreCd);
	}
}
