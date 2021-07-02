package edu.kh.semi.crawler.model.vo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerVo {
	
	// 영화 테이블
	private int movieNo;
	private ArrayList<String> arrMovieTitleKo = new ArrayList<String>();
	private ArrayList<String> arrMovieTitleEn = new ArrayList<String>();
	private ArrayList<String> arrMovieDirector = new ArrayList<String>();
	private ArrayList<String> arrMovieSummary = new ArrayList<String>();
	private ArrayList<String> arrGenreCd1 = new ArrayList<String>();
	private ArrayList<String> arrMovieCountry = new ArrayList<String>();
	private ArrayList<Date> arrMovieOpenDt = new ArrayList<Date>();
	private ArrayList<String> arrRuntime = new ArrayList<String>();
	
	// 장르이름/번호
	private ArrayList<String> arrGenreNm;
	private ArrayList<String> arrGenreCd2;
	
	// 영화파일
	private ArrayList<String> arrPoster;
	private ArrayList<String> arrStillCut;
	private ArrayList<String> arrMedia;
	
	/** 영화 상세페이지 Crawler[basic]
	 * @throws Exception
	 */
	public void movieTable() throws Exception {

		movieNo = 187310;

		// 영화 페이지 DOC
		String urlBasic =  "https://movie.naver.com/movie/bi/mi/basic.nhn?code="+movieNo;
		Document docBasic = Jsoup.connect(urlBasic).get();
		String urlDetail = "https://movie.naver.com/movie/bi/mi/detail.nhn?code="+movieNo;
		Document docDetail = Jsoup.connect(urlDetail).get();

		Elements elMovieTitleKo = docBasic.select("title");
		Elements elMovieTitleEn = docBasic.select(".mv_info > Strong.h_movie2");
		Elements elDirector = docBasic.select("dd > p > a");
		Elements elSummary = docBasic.select("p.con_tx");
		Elements elContents1 = docDetail.select("dd > p > span > a");
		Elements elContents2 = docDetail.select("dd > p > span");

		// 영화 한글 제목
		String delTitleKo = " : 네이버 영화";
		String movieTitleKo = elMovieTitleKo.text();
		movieTitleKo = movieTitleKo.substring
				(0,movieTitleKo.length()-delTitleKo.length());
		this.arrMovieTitleKo.add(movieTitleKo);

		// 영화 영어 제목
		String delTitleEn = ", 0000";
		String movieTitleEn = elMovieTitleEn.text();
		movieTitleEn = movieTitleEn.substring
				(0,movieTitleEn.length()-delTitleEn.length());
		this.arrMovieTitleEn.add(movieTitleEn);

		// 감독
		Element elDirector1 = elDirector.first();
		String director = elDirector1.text();
		this.arrMovieDirector.add(director);

		// 줄거리
		String summary = elSummary.html();
		this.arrMovieSummary.add(summary);

		

		// 장르, 국가, 개봉일
		List<Element> liContents1 = new ArrayList<Element>(elContents1);
		boolean flag = true;

		while(flag) {
			for(int j=0; j < liContents1.size(); j++) {

				String hrefContents = liContents1.get(j).attr("href");

				// 장르

				if(hrefContents.contains("genre=")) {
					String delGenre = "/movie/sdb/browsing/bmovie.nhn?genre=";
					String genreCd = hrefContents.substring
							(delGenre.length());
					this.arrGenreCd1.add(genreCd);

					// 국가

				}else if(hrefContents.contains("nation=")) {
					String delCountry = "/movie/sdb/browsing/bmovie.nhn?nation=";
					String movieCountry = hrefContents.substring
							(delCountry.length());
					this.arrMovieCountry.add(movieCountry);

					// 개봉일

				}else if(hrefContents.contains("open=")) {
					String delOpen = "/movie/sdb/browsing/bmovie.nhn?open=";
					String regExp = "^[0-9]{8}$";
					String openDt = hrefContents.substring
							(delOpen.length());
					if(openDt.matches(regExp)) {
						SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
						java.util.Date openDtSql = format.parse(openDt);
						java.sql.Date movieOpenDt = new java.sql.Date(openDtSql.getTime());
						this.arrMovieOpenDt.add(movieOpenDt);
					}
				}else {
					break;
				}
				flag = false;
			}
		}
		// 러닝타임
		List<Element> liContents2 = new ArrayList<Element>(elContents2);
		for(int j=0; j<liContents2.size(); j++) {
			String regExp = "^[1-9]{2,3}[분]$";
			String contents2 = liContents2.get(j).text();
			if(contents2.matches(regExp)) {
				contents2 = contents2.substring(0, contents2.length()-1);
				this.arrRuntime.add(contents2);
				break;
			}
		}
		
		System.out.println("크롤러까지 연결됬어요~");
		System.out.println(arrGenreCd1);
		System.out.println(arrMovieCountry);
		System.out.println(arrMovieOpenDt);
		System.out.println(arrRuntime);
		System.out.println(arrMovieTitleKo);
		System.out.println(arrMovieTitleEn);
		System.out.println(arrMovieDirector);
		System.out.println(arrMovieSummary);

	}
	
	/** 영화파일 Crawler
	 * @throws Exception
	 */
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
		for(int j=0; j<elMedia.size(); j++ ) {
			String mediaAtt = "https://movie.naver.com";
			String media = mediaAtt + elMedia.get(j).attr("href");
			arrMedia.add(media);
		}

		System.out.println(arrPoster);
		System.out.println(arrStillCut);
		System.out.println(arrMedia);
	}

	/** 영화배우 Crawler[detail]
	 * @throws Exception
	 */
	private void movieActor() throws Exception {

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
		
		
		
		System.out.println(arrActorNmKo);
		System.out.println(arrActorNmEn);
		System.out.println(arrActorCd);
		System.out.println(arrMovieNo);
	}


	/** 장르 Crawler[단독테이블]
	 * @throws Exception
	 */
	private void movieGenre() throws Exception {

		// 추출 url
		String url = "https://movie.naver.com/movie/sdb/browsing/bmovie_genre.nhn";
		Document doc = Jsoup.connect(url).get();

		// 추출 선택자
		Elements selectGenre  = doc.select(".directory_item_other td a");
		List<Element> elGenre = new ArrayList<Element>(selectGenre);

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
				arrGenreCd2.add(genreCd);

			} else if(10<= i) {
				String hrefGenre = elGenre.get(i).attr("href");
				String genreCd = hrefGenre.substring(hrefGenre.length()-2);
				arrGenreCd2.add(genreCd);
			}
		}
		System.out.println(arrGenreNm);
		System.out.println(arrGenreCd2);
	}
	
	
	
	
	public CrawlerVo() {}
	
	public CrawlerVo(int movieNo, ArrayList<String> arrMovieTitleKo, ArrayList<String> arrMovieTitleEn,
			ArrayList<String> arrMovieDirector, ArrayList<String> arrMovieSummary, ArrayList<String> arrGenreCd1,
			ArrayList<String> arrMovieCountry, ArrayList<Date> arrMovieOpenDt, ArrayList<String> arrRuntime) {
		super();
		this.movieNo = movieNo;
		this.arrMovieTitleKo = arrMovieTitleKo;
		this.arrMovieTitleEn = arrMovieTitleEn;
		this.arrMovieDirector = arrMovieDirector;
		this.arrMovieSummary = arrMovieSummary;
		this.arrGenreCd1 = arrGenreCd1;
		this.arrMovieCountry = arrMovieCountry;
		this.arrMovieOpenDt = arrMovieOpenDt;
		this.arrRuntime = arrRuntime;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public ArrayList<String> getArrMovieTitleKo() {
		return arrMovieTitleKo;
	}

	public void setArrMovieTitleKo(ArrayList<String> arrMovieTitleKo) {
		this.arrMovieTitleKo = arrMovieTitleKo;
	}

	public ArrayList<String> getArrMovieTitleEn() {
		return arrMovieTitleEn;
	}

	public void setArrMovieTitleEn(ArrayList<String> arrMovieTitleEn) {
		this.arrMovieTitleEn = arrMovieTitleEn;
	}

	public ArrayList<String> getArrMovieDirector() {
		return arrMovieDirector;
	}

	public void setArrMovieDirector(ArrayList<String> arrMovieDirector) {
		this.arrMovieDirector = arrMovieDirector;
	}

	public ArrayList<String> getArrMovieSummary() {
		return arrMovieSummary;
	}

	public void setArrMovieSummary(ArrayList<String> arrMovieSummary) {
		this.arrMovieSummary = arrMovieSummary;
	}

	public ArrayList<String> getArrGenreCd1() {
		return arrGenreCd1;
	}

	public void setArrGenreCd1(ArrayList<String> arrGenreCd1) {
		this.arrGenreCd1 = arrGenreCd1;
	}

	public ArrayList<String> getArrMovieCountry() {
		return arrMovieCountry;
	}

	public void setArrMovieCountry(ArrayList<String> arrMovieCountry) {
		this.arrMovieCountry = arrMovieCountry;
	}

	public ArrayList<Date> getArrMovieOpenDt() {
		return arrMovieOpenDt;
	}

	public void setArrMovieOpenDt(ArrayList<Date> arrMovieOpenDt) {
		this.arrMovieOpenDt = arrMovieOpenDt;
	}

	public ArrayList<String> getArrRuntime() {
		return arrRuntime;
	}

	public void setArrRuntime(ArrayList<String> arrRuntime) {
		this.arrRuntime = arrRuntime;
	}

	public ArrayList<String> getArrGenreNm() {
		return arrGenreNm;
	}

	public void setArrGenreNm(ArrayList<String> arrGenreNm) {
		this.arrGenreNm = arrGenreNm;
	}

	public ArrayList<String> getArrGenreCd2() {
		return arrGenreCd2;
	}

	public void setArrGenreCd2(ArrayList<String> arrGenreCd2) {
		this.arrGenreCd2 = arrGenreCd2;
	}

	public ArrayList<String> getArrPoster() {
		return arrPoster;
	}

	public void setArrPoster(ArrayList<String> arrPoster) {
		this.arrPoster = arrPoster;
	}

	public ArrayList<String> getArrStillCut() {
		return arrStillCut;
	}

	public void setArrStillCut(ArrayList<String> arrStillCut) {
		this.arrStillCut = arrStillCut;
	}

	public ArrayList<String> getArrMedia() {
		return arrMedia;
	}

	public void setArrMedia(ArrayList<String> arrMedia) {
		this.arrMedia = arrMedia;
	}

	@Override
	public String toString() {
		return "CrawlerMethod [movieNo=" + movieNo + ", arrMovieTitleKo=" + arrMovieTitleKo + ", arrMovieTitleEn="
				+ arrMovieTitleEn + ", arrMovieDirector=" + arrMovieDirector + ", arrMovieSummary=" + arrMovieSummary
				+ ", arrGenreCd1=" + arrGenreCd1 + ", arrMovieCountry=" + arrMovieCountry + ", arrMovieOpenDt="
				+ arrMovieOpenDt + ", arrRuntime=" + arrRuntime + ", arrGenreNm=" + arrGenreNm + ", arrGenreCd2="
				+ arrGenreCd2 + ", arrPoster=" + arrPoster + ", arrStillCut=" + arrStillCut + ", arrMedia=" + arrMedia
				+ "]";
	}
	
	
}
