package com.util;

public class Page_soo {
	
	//������: product, board ��
	
	//�� ������ �� �ֻ�� ������
	public int start(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - ((currentPage-1) * numPerPage + 1) + 1 ;
	}
   
	//�� ������ �� ���ϴ� ������
	public int end(int currentPage, int numPerPage, int totalDataCount) {
		return totalDataCount - (currentPage * numPerPage) + 1;//�ڡڡ�-n~0 ���̴�??�����ȳ�?
	}
	
	//��ü ������ ��
	public int totalPageCount(int numPerPage, int totalDataCount) {//numPerPage: �� �������� ������ ��, totalDataCount: ��ü ������ ��
		
		int pageCount = 0;
		
		pageCount = totalDataCount / numPerPage;//�Խñ�10��/5��������=2������. �Խñ�12��/5��������=2������
		
		if(totalDataCount % numPerPage != 0) {//�Խñ�12��/5��������=2������
			pageCount++;//3������
		}
		
		return pageCount;
	}
	
	//����¡
	//< 1 2 3 4 5 > �� <, > Ŭ���� ���� ����. EX: < 6 7 8 9 10 >
	public String paging(int currentPage, int totalPage, String listUrl) {
		
		int numPerPaging = 5;//����¡���� �ѹ��� �������� ����
		int previousPage;//<
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//�������� ���� ���
		if(currentPage == 0 || totalPage == 0) {
			return "";//����¡ ���X
		}
		
		//����¡ �ּ�
		if(listUrl.indexOf("?") != -1) {//�˻��� �� ���. listUrl: list.jsp?searchKey=subject&searchValue=111
			listUrl = listUrl + "&";//list.jsp?searchKey=subject&searchValue=111&(pageNum=3)
		}else {//�˻��� �� �� ���. listUrl: list.jsp
			listUrl = listUrl + "?";//list.jsp?(pageNum=3)
		}
		
		/*
		1 2 3 4 5 >
		< 6 7 8 9 10 >
		< 11 12 13 14 15
		*/
		previousPage = ((currentPage - 1) / numPerPaging) * numPerPaging;//((7-1)/5)*5=5. ((10-1)/5)*5=5
		
		/*if(currentPage % numPerBlock == 0) {//������
			currentPageSetup = currentPageSetup - numPerBlock;//5=10-5
		}*/
		
		//���� �������� �̵� <
		/*if(totalPage > numPerPaging && previousPage > 0) {*///12>5 ��X 3<5
		if(previousPage > 0) {
			
			/*
			append() ���õ� ����� �������� ���ο� ��Ҹ� �߰�
			<a href="list.jsp?pageNum=5"<</a>&nbsp;
			*/
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
		}
		
		//������ ��ȣ
		page = previousPage + 1;//< ���� ��µǴ� ��ȣ
		
		while(page <= totalPage && page <= (previousPage + numPerPaging)) {//6<=12 && 6<=(5+5)
			
			if(page==currentPage) {
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");//<span id="currentPage">3</span>&nbsp;
			}else {
				sb.append("<a id=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				//<a id="non-currentPage" href="list.jsp?pageNum=2">3</a>
			}
			
			page++;
		}
		
		//���� �������� �̵� >
		if(totalPage - previousPage > numPerPaging) {//12-5>5 ��X 12-10<5
			
			//<a href="list.jsp?pageNum=11">������</a>&nbsp;
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
		}
		
		return sb.toString();
	}
	
	//<< < 1 2 3 4 5 > >> �� <<, >>: ���� ��, ���� �� ������ 
	public String paging2(int currentPage, int totalPage, String listUrl) {
		
		int numPerPaging = 5;
		int firstPage = 1;//<<
		int endPage = totalPage;//>>
		int previousPage;
		int page;
		
		StringBuffer sb = new StringBuffer();
		
		//�������� ���� ���
		if(currentPage == 0 || totalPage == 0) {
			return "";
		}
		
		//����¡ �ּ�
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
			
			//���� �� �������� �̵�<<
			sb.append("<a href=\"" + listUrl + "pageNum=" + firstPage + "\"><<</a>&nbsp;");
			//���� �������� �̵� <
			sb.append("<a href=\"" + listUrl + "pageNum=" + previousPage + "\"><</a>&nbsp;");
		}
		
		//������ ��ȣ
		page = previousPage + 1;
		
		while(page <= totalPage && page <= (previousPage + numPerPaging)) {
			
			if(page==currentPage) {
				sb.append("<span id=\"currentPage\">" + page + "</span>&nbsp;");
			}else {
				sb.append("<a id=\"non-currentPage\" href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
			}
			
			page++;
		}
		
		//���� �������� �̵� >
		if(totalPage - previousPage > numPerPaging) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">></a>&nbsp;");
		}
		
		//���� �� �������� �̵�>>
		if(totalPage - previousPage > numPerPaging) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + endPage + "\">>></a>&nbsp;");
		}
		
		return sb.toString();
	}
}
