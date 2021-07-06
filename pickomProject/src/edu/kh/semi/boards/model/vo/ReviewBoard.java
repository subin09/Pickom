package edu.kh.semi.boards.model.vo;

import java.sql.Timestamp;
import java.util.List;

import edu.kh.semi.movie.model.vo.MovieGenre;

public class ReviewBoard {
	private int reviewNo;
	private String reviewTitle;
	private String reviewContent;
	private int readCount;
	private Timestamp createDt;
	private Timestamp modifyDt;
	private String reviewStatus;
	private int memberNo;
	private String memberNm;
	private String memberGrade;
	private String memberNickNm;
	
	//REPORT
	private int reportNo;
	private String reportContent;
	private String reportTitle;
	private Timestamp reportDate;
	private int reportCd;
	private String reportCdNm;
	private int boardNo;
	private int commentNo;
	
	// MOVIE
	private int movieNo;
	private String movieTitleEn;
	private String movieTitleKo;
	
	// MOVIE_GENRE
	private List<Integer> movieGenreCode;
	private List<String> movieGenreNm;
	private List<MovieGenre> gnList;
	
	
	private List<String> filePath;
	private List<String> fileName;
	private List<ComplainAttachment> atList;
	
	private List<Integer> categoryCodes;
	private List<String> categoryNames;
	private List<ReviewCategory> ctList;
	
	private int categoryCd;
	private String categoryName;
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}
	public Timestamp getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Timestamp modifyDt) {
		this.modifyDt = modifyDt;
	}
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public String getMemberNickNm() {
		return memberNickNm;
	}
	public void setMemberNickNm(String memberNickNm) {
		this.memberNickNm = memberNickNm;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public Timestamp getReportDate() {
		return reportDate;
	}
	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}
	public int getReportCd() {
		return reportCd;
	}
	public void setReportCd(int reportCd) {
		this.reportCd = reportCd;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getMovieNo() {
		return movieNo;
	}
	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}
	public String getMovieTitleEn() {
		return movieTitleEn;
	}
	public void setMovieTitleEn(String movieTitleEn) {
		this.movieTitleEn = movieTitleEn;
	}
	public String getMovieTitleKo() {
		return movieTitleKo;
	}
	public void setMovieTitleKo(String movieTitleKo) {
		this.movieTitleKo = movieTitleKo;
	}
	public List<Integer> getMovieGenreCode() {
		return movieGenreCode;
	}
	public void setMovieGenreCode(List<Integer> movieGenreCode) {
		this.movieGenreCode = movieGenreCode;
	}
	public List<String> getMovieGenreNm() {
		return movieGenreNm;
	}
	public void setMovieGenreNm(List<String> movieGenreNm) {
		this.movieGenreNm = movieGenreNm;
	}
	public List<MovieGenre> getGnList() {
		return gnList;
	}
	public void setGnList(List<MovieGenre> gnList) {
		this.gnList = gnList;
	}
	public List<String> getFilePath() {
		return filePath;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
	public List<String> getFileName() {
		return fileName;
	}
	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}
	public List<ComplainAttachment> getAtList() {
		return atList;
	}
	public void setAtList(List<ComplainAttachment> atList) {
		this.atList = atList;
	}
	public List<Integer> getCategoryCodes() {
		return categoryCodes;
	}
	public void setCategoryCodes(List<Integer> categoryCodes) {
		this.categoryCodes = categoryCodes;
	}
	public List<String> getCategoryNames() {
		return categoryNames;
	}
	public void setCategoryNames(List<String> categoryNames) {
		this.categoryNames = categoryNames;
	}
	public List<ReviewCategory> getCtList() {
		return ctList;
	}
	public void setCtList(List<ReviewCategory> ctList) {
		this.ctList = ctList;
	}
	public int getCategoryCd() {
		return categoryCd;
	}
	public void setCategoryCd(int categoryCd) {
		this.categoryCd = categoryCd;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getReportCdNm() {
		return reportCdNm;
	}
	public void setReportCdNm(String reportCdNm) {
		this.reportCdNm = reportCdNm;
	}
	@Override
	public String toString() {
		return "ReviewBoard [reviewNo=" + reviewNo + ", reviewTitle=" + reviewTitle + ", reviewContent=" + reviewContent
				+ ", readCount=" + readCount + ", createDt=" + createDt + ", modifyDt=" + modifyDt + ", reviewStatus="
				+ reviewStatus + ", memberNo=" + memberNo + ", memberNm=" + memberNm + ", memberGrade=" + memberGrade
				+ ", memberNickNm=" + memberNickNm + ", reportNo=" + reportNo + ", reportContent=" + reportContent
				+ ", reportTitle=" + reportTitle + ", reportDate=" + reportDate + ", reportCd=" + reportCd
				+ ", reportCdNm=" + reportCdNm + ", boardNo=" + boardNo + ", commentNo=" + commentNo + ", movieNo="
				+ movieNo + ", movieTitleEn=" + movieTitleEn + ", movieTitleKo=" + movieTitleKo + ", movieGenreCode="
				+ movieGenreCode + ", movieGenreNm=" + movieGenreNm + ", gnList=" + gnList + ", filePath=" + filePath
				+ ", fileName=" + fileName + ", atList=" + atList + ", categoryCodes=" + categoryCodes
				+ ", categoryNames=" + categoryNames + ", ctList=" + ctList + ", categoryCd=" + categoryCd
				+ ", categoryName=" + categoryName + "]";
	}
	
	
	
	
	
}
