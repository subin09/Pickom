package edu.kh.semi.boards.model.vo;

public class Pagination {

	private int currentPage;			// 목록상 현재 페이지
	private int listCount;				// 게시글 전체 수
	
	private int limit = 10;				// 한 페이지에 보여질 게시글 수
	private int pageSize = 10;			// 한번에 보여질 페이지 번호 갯수
	
	private int maxPage;				// 게시글 목록의 마지막 페이지 번호	
	private int startPage;				// 보여지는 페이지 번호 중 시작 번호
	private int endPage;				// 보여지는 페이지 번호 중 끝 번호
	
	private int prevPage;				// 이전 페이지 번호 목록 중 끝 번호
	private int nextPage;				// 다음 페이지 번호 목록 중 시작 번호
	
	// 게시판 보드 타입 분류
	private int boardType;
	
	
	public Pagination(int currentPage, int listCount) {
		super();
		this.currentPage = currentPage;
		this.listCount = listCount;
		
		makePagination();
	}


	public Pagination(int cp, int listCount2, int boardType) {
		super();
		this.currentPage = cp;
		this.listCount = listCount2;
		this.boardType = boardType;
		
		makePagination();
	}
	

	public int getBoardType() {
		return boardType;
	}


	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	
		makePagination();
	}


	public int getListCount() {
		return listCount;
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
		
		makePagination();
	}


	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
		
		makePagination();
	}


	public int getPageSize() {
		return pageSize;
		
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		
		makePagination();
	}


	public int getMaxPage() {
		return maxPage;
	}


	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public int getPrevPage() {
		return prevPage;
	}


	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}


	public int getNextPage() {
		return nextPage;
	}


	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}




	


	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", listCount=" + listCount + ", limit=" + limit
				+ ", pageSize=" + pageSize + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prevPage=" + prevPage + ", nextPage=" + nextPage + ", boardType=" + boardType + "]";
	}


	// 페이징 처리에 필요한 값을 계산하는 메소드
	private void makePagination() {
		
		// maxPage == 마지막 페이지이자 총 페이지 수 
		maxPage = (int)Math.ceil((double)listCount/limit);
		// (int)Math.ceil(50.0) ==> int로 변환해 주니 50
		// listCount가 499일경우 (int)Math.ceil(49.9) ==> 올림처리 해주면 50.0 ==> int로 변환해 주니 50
		// listCount가 501일경우 (int)Math.ceil(50.1) ==> 올림처리 해주면 51 ==> int로 변환해 주니 51
		
		
		// startPage == 페이지 번호 목록 시작 번호
		// ex) 1, 11, 21, 31 .......
		startPage = (currentPage-1)/pageSize * pageSize +1;
		// ex) 현재 페이지 16
		// 11 12 13 14 15 [16] 17 18 19 20
		// (16-1) / 10 * 10 + 1
		// (15) / 10 * 10 + 1
		// 1 * 10 + 1
		// 10 + 1
		// 11 + 1
		
		
		// endPage == 페이지 번호 목록 끝 번호
		// ex) 10, 20, 30, 40
		endPage = startPage + pageSize -1;
		
		// ***** 보여지는 페이지 번호 목록의 끝 번호가 maxPage보다 클 경우
		
		// ex) 현재 페이지 : 52
		// 페이지 번호 목록 : 51 52 53 54 55 56 57 58 59 60
		// 끝 페이지 : 55
		// 페이지 번호 목록 : 51 52 53 54 55
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 이전, 다음 페이지 번호 목록으로 이동
		if(currentPage < 10) prevPage=1;
		else				 prevPage = (currentPage -1) / pageSize * pageSize;
		// ex) currentPage = 15
		// 15-1/10*10
		// 14/10*10
		// 1*10
		// 10

		nextPage = (currentPage + pageSize-1) / pageSize * pageSize +1;
		
		
		if(nextPage>maxPage) {
			nextPage=maxPage;
		}
		
		
	}
	
	

}
