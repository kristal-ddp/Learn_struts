package com.util;

public class Page_hong {

	// ��ü �������� ����
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

	// ����¡ ó�� �޼ҵ�

	// 1 2 3 4 5 ������
	// ������ 6 7 8 9 10 ������
	// ������ 11 12
	public String paging(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ������ 6 7 8 9 10 ������
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // �˻�
			return ""; // ����¡�� �� �������� 0���� ��
		}

		// �ּ�
		// list.jsp �˻��� ���� �ʾ��� ��
		// list.jsp?searchKey=subject&searchValue=ASD �˻��� ���� ��
		if (listUrl.indexOf("?") != -1) { // ������~
			listUrl = listUrl + "&";
			// list.jsp?searchKey=subject&searchValue=ASD&pageNum=3
		} else {
			listUrl = listUrl + "?";
			// list.jsp?pageNum=3
		}

		// 1 2 3 4 5 ������
		// ������ 6 7 8 9 10 ������
		// ������ 11 12
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging; // (12 / 5) * 5 = 10

//		if(currentPage % numPerPaging == 0) { // ������
//			previousPage = previousPage - numPerPaging; // 10 - 5 = 5
//		}

		// ������
		if (totalPage > numPerPaging && previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\">������</a>&nbsp;");
			// <a href="list.jsp?pageNum=5"������</a>&nbsp;
		}

		// 1 2 3 4 5 ������
		// ������ 6 7 8 9 10 ������
		// ������ 11 12

		// �ٷΰ��� ������
		page = previousPage + 1; // �����ϴ� ��������ȣ

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // 6 <= 12 && 6 <= (5 + 5)

			if (page == currentPage) { // ���� ������
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <font color="Fuchsia">9</font>&nbsp;
			} else { // �ٸ� ������
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href=list.jsp?pageNum=2">2</a>
			}

			page++;

		}

		// 1 2 3 4 5 ������
		// ������ 6 7 8 9 10 ������
		// ������ 11 12

		// ������
		if (totalPage - previousPage > numPerPaging) { // 12 - 5 > 5 ; 12 - 10 > 5 �� �Ⱥ�����

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">������</a>");
			// <a href="list.jsp?pageNum=11">������</a>&nbsp;

		}

		return sb.toString();

	}

	// 1 2 3 4 5 > >>
	// << < 6 7 8 9 10 > >>
	// << < 11 12
	public String paging1(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ������ 6 7 8 9 10 ������
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // �˻�
			return ""; // ����¡�� �� �������� 0���� ��
		}

		// �ּ�
		// list.jsp �˻��� ���� �ʾ��� ��
		// list.jsp?searchKey=subject&searchValue=ASD �˻��� ���� ��
		if (listUrl.indexOf("?") != -1) { // ������~
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

//		if(currentPage % numPerBlock == 0) { // ������
//			currentPageSetup = currentPageSetup - numPerBlock; // 10 - 5 = 5
//		}

		// << <
		if (totalPage > numPerPaging && previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + 1 + "\"><<</a>&nbsp;&nbsp;");
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
			// <a href="list.jsp?pageNum=5"������</a>&nbsp;
		}

		// 1 2 3 4 5 > >>
		// << < 6 7 8 9 10 > >>
		// << < 11 12

		// �ٷΰ��� ������
		page = previousPage + 1; // �����ϴ� ��������ȣ

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // 6 <= 12 && 6 <= (5 + 5)

			if (page == currentPage) { // ���� ������
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <font color="Fuchsia">9</font>&nbsp;
			} else { // �ٸ� ������
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href=list.jsp?pageNum=2">2</a>
			}
			
			page++;

		}

		// 1 2 3 4 5 > >>
		// << < 6 7 8 9 10 > >>
		// << < 11 12

		// > >>
		if (totalPage - previousPage > numPerPaging) { // 12 - 5 > 5 ; 12 - 10 > 5 �� �Ⱥ�����

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
			sb.append("&nbsp;<a href=\"" + listUrl + "pageNum=" + totalPage + "\">>></a>");
			// <a href="list.jsp?pageNum=11">������</a>&nbsp;

		}

		return sb.toString();

	}

	// 1 2 3 4 5 ������
	// ������ 4 5 6 7 8 ������
	// ������ 8 9 10 11 12
	public String paging2(int currentPage, int totalPage, String listUrl) {

		int numPerPaging = 5; // ������ 4 5 6 7 8 ������
		int previousPage;
		int page;

		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // �˻�
			return ""; // ����¡�� �� �������� 0���� ��
		}

		// �ּ�
		// list.jsp �˻��� ���� �ʾ��� ��
		// list.jsp?searchKey=subject&searchValue=ASD �˻��� ���� ��
		if (listUrl.indexOf("?") != -1) { // ������~
			listUrl = listUrl + "&";
			// list.jsp?searchKey=subject&searchValue=ASD&pageNum=3
		} else {
			listUrl = listUrl + "?";
			// list.jsp?pageNum=3
		}

		// 1 2 3 4 5 ������
		// ������ 4 5 6 7 8 ������
		// ������ 8 9 10 11 12
		previousPage = currentPage - ((numPerPaging + 1) / 2); // 6 - ((5 + 1) / 2 = 3

		if(currentPage <= (numPerPaging + 1) / 2) {
			previousPage = 0;
		}

		// ������
		if (previousPage > 0) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\">������</a>&nbsp;");
			// <a href="list.jsp?pageNum=3"������</a>&nbsp;
		}

		// 1 2 3 4 5 ������
		// ������ 4 5 6 7 8 ������
		// ������ 8 9 10 11 12

		// �ٷΰ��� ������
		page = previousPage + 1; // �����ϴ� ������ ��ȣ
		
		if (page > totalPage - numPerPaging + 1) { // 12 > 12 - 5 + 1
			page = totalPage - numPerPaging + 1; // page = 8 : �ִ�
		}
		if (page < 1) {
			page = 1; // �ּڰ�
		}

		while (page <= totalPage && page <= (previousPage + numPerPaging)) { // page <= 12 && page <= (3 + 5)

			if (page == currentPage) { // ���� ������
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
				// <span id="currentPage">9</span>&nbsp;
			} else { // �ٸ� ������
				sb.append("<a name=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a id="non-currentPage" href=list.jsp?pageNum=2">2</a>
			}
			
			page++;

		}

		// 1 2 3 4 5 ������
		// ������ 4 5 6 7 8 ������
		// ������ 8 9 10 11 12

		// ������
		if (totalPage - page > numPerPaging) {

			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">������</a>");
			// <a href="list.jsp?pageNum=11">������</a>;

		}

		return sb.toString();

	}
}
