package com.util;

public class Page_soo {
	
	//데이터: product, board 등
	
	//한 페이지 내 최상단 데이터
	public int start(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - ((currentPage-1) * numPerPage + 1) + 1 ;
	}
   
	//한 페이지 내 최하단 데이터
	public int end(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - (currentPage * numPerPage) + 1;//★★★-n~0 사이는??오류안남?
	}
	
	//전체 페이지 수
	public int totalPageCount(int numPerPage, int totalDataCount) {//numPerPage: 한 페이지당 데이터 수, totalDataCount: 전체 데이터 수
		
		int pageCount = 0;
		
		pageCount = totalDataCount / numPerPage;//게시글10개/5페이지씩=2페이지. 게시글12개/5페이지씩=2페이지
		
		if(totalDataCount % numPerPage != 0) {//게시글12개/5페이지씩=2페이지
			pageCount++;//3페이지
		}
		
		return pageCount;
	}
	
	//페이징
	//< 1 2 3 4 5 > → <, > 클릭시 숫자 변경. EX: < 6 7 8 9 10 >
	public String paging(int currentPage, int totalPage, String listUrl) {
		
		int numPerPaging = 5;//페이징에서 한번에 보여지는 개수
		int previousPage;//<
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//페이지가 없을 경우
		if(currentPage == 0 || totalPage == 0) {
			return "";//페이징 출력X
		}
		
		//페이징 주소
		if(listUrl.indexOf("?") != -1) {//검색을 한 경우. listUrl: list.jsp?searchKey=subject&searchValue=111
			listUrl = listUrl + "&";//list.jsp?searchKey=subject&searchValue=111&(pageNum=3)
		}else {//검색을 안 한 경우. listUrl: list.jsp
			listUrl = listUrl + "?";//list.jsp?(pageNum=3)
		}
		
		/*
		1 2 3 4 5 >
		< 6 7 8 9 10 >
		< 11 12 13 14 15
		*/
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging;//((7-1)/5)*5=5. ((10-1)/5)*5=5
		
		/*if(currentPage % numPerBlock == 0) {//나머지
			currentPageSetup = currentPageSetup - numPerBlock;//5=10-5
		}*/
		
		//이전 페이지로 이동 <
		/*if(totalPage > numPerPaging && previousPage > 0) {*///12>5 │X 3<5
		if(previousPage > 0) {
			
			/*
			append() 선택된 요소의 마지막에 새로운 요소를 추가
			<a href="list.jsp?pageNum=5"<</a>&nbsp;
			*/
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
		}
		
		//페이지 번호
		page = previousPage + 1;//< 이후 출력되는 번호
		
		while(page <= totalPage && page <= (previousPage + numPerPaging)) {//6<=12 && 6<=(5+5)
			
			if(page==currentPage) {
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");//<span id="currentPage">3</span>&nbsp;
			}else {
				sb.append("<a id=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				//<a id="non-currentPage" href="list.jsp?pageNum=2">3</a>
			}
			
			page++;
		}
		
		//다음 페이지로 이동 >
		if(totalPage - previousPage > numPerPaging) {//12-5>5 │X 12-10<5
			
			//<a href="list.jsp?pageNum=11">다음▶</a>&nbsp;
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
		}
		
		return sb.toString();
	}
	
	//<< < 1 2 3 4 5 > >> → <<, >>: 제일 앞, 제일 뒤 페이지 
	public String paging2(int currentPage, int totalPage, String listUrl) {
		
		int numPerPaging = 5;
		int firstPage = 1;//<<
		int endPage = totalPage;//>>
		int previousPage;
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//페이지가 없을 경우
		if(currentPage == 0 || totalPage == 0) {
			return "";
		}
		
		//페이징 주소
		if(listUrl.indexOf("?") != -1) {
			listUrl = listUrl + "&";
		}else {
			listUrl = listUrl + "?";
		}
		
		/*
		1 2 3 4 5 > >>
		<< < 6 7 8 9 10 > >>
		<< < 11 12 13 14 15
		*/
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging;
		
		if(totalPage > numPerPaging && previousPage > 0) {
			
			//제일 앞 페이지로 이동<<
			sb.append("<a href=\"" + listUrl + "pageNum=" + firstPage + "\"><<</a>&nbsp;");
			//이전 페이지로 이동 <
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
		}
		
		//페이지 번호
		page = previousPage + 1;
		
		while(page <= totalPage && page <= (previousPage + numPerPaging)) {
			
			if(page==currentPage) {
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
			}else {
				sb.append("<a id=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
			}
			
			page++;
		}
		
		//다음 페이지로 이동 >
		if(totalPage - previousPage > numPerPaging) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
		}
		
		//제일 뒤 페이지로 이동>>
		if(totalPage - previousPage > numPerPaging) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + endPage + "\">>></a>&nbsp;");
		}
		
		return sb.toString();
	}
}
