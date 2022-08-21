package com.util;

public class Page_hong {

	// 전체 페이지의 개수
	public int totalPageCount(int numPerPage, int totalDataCount) {

		int pageCount = 0;

		pageCount = totalDataCount / numPerPage; // 34/3

		if (totalDataCount % numPerPage != 0) {
			pageCount++;
		}

		return pageCount;

	}

	public int start(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - ((currentPage - 1) * numPerPage + 1) + 1;
	}

	public int end(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - (currentPage * numPerPage) + 1;
	}

	// 페이징 처리 메소드

	// 1 2 3 4 5 다음▶
	// ◀이전 6 7 8 9 10 다음▶
	// ◀이전 11 12
	public String paging(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ◀이전 6 7 8 9 10 다음▶
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // 검사
			return ""; // 페이징이 안 나오도록 0으로 함
		}

		// 주소
		// list.jsp 검색을 하지 않았을 때
		// list.jsp?searchKey=subject&searchValue=ASD 검색을 했을 때
		if (listUrl.indexOf("?") != -1) { // 있으면~
			listUrl = listUrl + "&";
			// list.jsp?searchKey=subject&searchValue=ASD&pageNum=3
		} else {
			listUrl = listUrl + "?";
			// list.jsp?pageNum=3
		}

		// 1 2 3 4 5 다음▶
		// ◀이전 6 7 8 9 10 다음▶
		// ◀이전 11 12
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging; // (12 / 5) * 5 = 10

//		if(currentPage % numPerPaging == 0) { // 나머지
//			previousPage = previousPage - numPerPaging; // 10 - 5 = 5
//		}

		// ◀이전
		if (totalPage > numPerPaging && previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\">◀이전</a>&nbsp;");
			// <a href="list.jsp?pageNum=5"◀이전</a>&nbsp;
		}

		// 1 2 3 4 5 다음▶
		// ◀이전 6 7 8 9 10 다음▶
		// ◀이전 11 12

		// 바로가기 페이지
		page = previousPage + 1; // 시작하는 페이지번호

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // 6 <= 12 && 6 <= (5 + 5)

			if (page == currentPage) { // 현재 페이지
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <font color="Fuchsia">9</font>&nbsp;
			} else { // 다른 페이지
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href=list.jsp?pageNum=2">2</a>
			}

			page++;

		}

		// 1 2 3 4 5 다음▶
		// ◀이전 6 7 8 9 10 다음▶
		// ◀이전 11 12

		// 다음▶
		if (totalPage - previousPage > numPerPaging) { // 12 - 5 > 5 ; 12 - 10 > 5 → 안보여줌

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>");
			// <a href="list.jsp?pageNum=11">다음▶</a>&nbsp;

		}

		return sb.toString();

	}

	// 1 2 3 4 5 > >>
	// << < 6 7 8 9 10 > >>
	// << < 11 12
	public String paging1(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ◀이전 6 7 8 9 10 다음▶
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // 검사
			return ""; // 페이징이 안 나오도록 0으로 함
		}

		// 주소
		// list.jsp 검색을 하지 않았을 때
		// list.jsp?searchKey=subject&searchValue=ASD 검색을 했을 때
		if (listUrl.indexOf("?") != -1) { // 있으면~
			listUrl = listUrl + "&";
			// list.jsp?searchKey=subject&searchValue=ASD&pageNum=3
		} else {
			listUrl = listUrl + "?";
			// list.jsp?pageNum=3
		}

		// 1 2 3 4 5 > >>
		// << < 6 7 8 9 10 > >>
		// << < 11 12
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging; // (12 / 5) * 5 = 10

//		if(currentPage % numPerBlock == 0) { // 나머지
//			currentPageSetup = currentPageSetup - numPerBlock; // 10 - 5 = 5
//		}

		// << <
		if (totalPage > numPerPaging && previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + 1 + "\"><<</a>&nbsp;&nbsp;");
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
			// <a href="list.jsp?pageNum=5"◀이전</a>&nbsp;
		}

		// 1 2 3 4 5 > >>
		// << < 6 7 8 9 10 > >>
		// << < 11 12

		// 바로가기 페이지
		page = previousPage + 1; // 시작하는 페이지번호

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // 6 <= 12 && 6 <= (5 + 5)

			if (page == currentPage) { // 현재 페이지
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <font color="Fuchsia">9</font>&nbsp;
			} else { // 다른 페이지
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href=list.jsp?pageNum=2">2</a>
			}
			
			page++;

		}

		// 1 2 3 4 5 > >>
		// << < 6 7 8 9 10 > >>
		// << < 11 12

		// > >>
		if (totalPage - previousPage > numPerPaging) { // 12 - 5 > 5 ; 12 - 10 > 5 → 안보여줌

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
			sb.append("&nbsp;<a href=\"" + listUrl + "pageNum=" + totalPage + "\">>></a>");
			// <a href="list.jsp?pageNum=11">다음▶</a>&nbsp;

		}

		return sb.toString();

	}

	// 1 2 3 4 5 다음▶
	// ◀이전 4 5 6 7 8 다음▶
	// ◀이전 8 9 10 11 12
	public String paging2(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ◀이전 4 5 6 7 8 다음▶
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // 검사
			return ""; // 페이징이 안 나오도록 0으로 함
		}

		// 주소
		// list.jsp 검색을 하지 않았을 때
		// list.jsp?searchKey=subject&searchValue=ASD 검색을 했을 때
		if (listUrl.indexOf("?") != -1) { // 있으면~
			listUrl = listUrl + "&";
			// list.jsp?searchKey=subject&searchValue=ASD&pageNum=3
		} else {
			listUrl = listUrl + "?";
			// list.jsp?pageNum=3
		}

		// 1 2 3 4 5 다음▶
		// ◀이전 4 5 6 7 8 다음▶
		// ◀이전 8 9 10 11 12
		previousPage = currentPage - ((numPerPaging + 1) / 2); // 6 - ((5 + 1) / 2 = 3

		if(currentPage <= (numPerPaging + 1) / 2) {
			previousPage = 0;
		}

		// ◀이전
		if (previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\">◀이전</a>&nbsp;");
			// <a href="list.jsp?pageNum=3"◀이전</a>&nbsp;
		}

		// 1 2 3 4 5 다음▶
		// ◀이전 4 5 6 7 8 다음▶
		// ◀이전 8 9 10 11 12

		// 바로가기 페이지
		page = previousPage + 1; // 시작하는 페이지 번호
		
		if (page > totalPage - numPerPaging + 1) { // 12 > 12 - 5 + 1
			page = totalPage - numPerPaging + 1; // page = 8 : 최댓값
		}
		if (page < 1) {
			page = 1; // 최솟값
		}

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // page <= 12 && page <= (3 + 5)

			if (page == currentPage) { // 현재 페이지
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <span id="currentPage">9</span>&nbsp;
			} else { // 다른 페이지
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a id="non-currentPage" href=list.jsp?pageNum=2">2</a>
			}
			
			page++;

		}

		// 1 2 3 4 5 다음▶
		// ◀이전 4 5 6 7 8 다음▶
		// ◀이전 8 9 10 11 12

		// 다음▶
		if (totalPage - page > numPerPaging) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">다음▶</a>");
			// <a href="list.jsp?pageNum=11">다음▶</a>;

		}

		return sb.toString();

	}
}
