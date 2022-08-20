package com.struts2.boards;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.util.MyPage;
import com.util.Page_soo;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class BoardsAction extends ActionSupport
	implements Preparable,ModelDriven<BoardsDTO>{

	private BoardsDTO dto;
	
	public BoardsDTO getDto() {
		return dto;
	}
	
	@Override
	public BoardsDTO getModel() {
		return dto;
	}
	
	@Override
	public void prepare() throws Exception {
		dto = new BoardsDTO();
	}
	
	public String list() throws Exception{
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		/*MyPage myPage = new MyPage();*/
		Page_soo page = new Page_soo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String cp = request.getContextPath();
		
		int numPerPage = 8;
		int totalPage = 0;
		int totalDataCount = 0;
		
		int currentPage = 1;
		
		if(dto.getPageNum()!=null && !dto.getPageNum().equals("")) {
			currentPage = Integer.parseInt(dto.getPageNum());
		}
		
		if(dto.getSearchValue()==null || dto.getSearchValue().equals("")) {
			dto.setSearchKey("subject");
			dto.setSearchValue("");
		}
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			dto.setSearchValue(URLDecoder.decode(dto.getSearchValue(), "UTF-8"));
		}
		
		Map<String, Object> hMap = new HashMap<String, Object>();
		hMap.put("searchKey", dto.getSearchKey());//Map<Key,Value>�� Key�� ���� Value���� ��Ī�ؼ� ������ �� ���
		hMap.put("searchValue", dto.getSearchValue());
		
		totalDataCount = dao.getIntValue("boards.dataCount", hMap);
		
		if(totalDataCount!=0) {
			/*totalPage = myPage.getPageCount(numPerPage, totalDataCount);*/
			totalPage = page.totalPageCount(numPerPage, totalDataCount);
		}
		
		if(currentPage>totalPage) {
			currentPage = totalPage;
		}
		
		/*int start = (currentPage-1)*numPerPage+1;
		int end = currentPage*numPerPage;*/
		int start = page.start(currentPage, numPerPage, totalDataCount);
		int end = page.end(currentPage, numPerPage, totalDataCount);
		
		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> lists = dao.getListData("boards.listData", hMap);
		
		//�Ϸù�ȣ
		int listNum,n=0;
		
		ListIterator<Object> it = lists.listIterator();
		
		while(it.hasNext()) {
			
			BoardsDTO vo = (BoardsDTO)it.next();
			listNum = totalDataCount-(start+n-1);
			vo.setListNum(listNum);
			
			n++;
			
		}
		
		//�ּ�����
		String param = "";
		String listUrl = "";
		String viewUrl ="";
		
		if(!dto.getSearchValue().equals("")) {
			
			param = "searchKey=" + dto.getSearchKey();
			param+= "&searchValue=" +
					URLEncoder.encode(dto.getSearchValue(),"UTF-8");
		}
		
		listUrl = cp + "/boards/list.action";
		viewUrl = cp + "/boards/view.action?pageNum=" + currentPage;
		
		if(!param.equals("")) {
			listUrl += "?" + param;
			viewUrl += "&" + param;
		}
		
		request.setAttribute("lists", lists);
		request.setAttribute("totalDataCount", totalDataCount);
		/*request.setAttribute("pageIndexList", myPage.pageIndexList(currentPage, totalPage, listUrl));*/
		request.setAttribute("paging", page.paging(currentPage, totalPage, listUrl));
		request.setAttribute("viewUrl", viewUrl);
		request.setAttribute("rnum", dto.getRnum());
		
		return SUCCESS;
		
	}
	
	public String view() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		//dto�� ���� �̸� ������ ��Ƴ��� ���
		String searchKey = dto.getSearchKey();
		String searchValue = dto.getSearchValue();
		String pageNum = dto.getPageNum();
		int boardNum = dto.getBoardNum();
		
		if(searchValue==null || searchValue.equals("")) {
			searchKey = "subject";
			searchValue = "";
		}
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		dao.updateData("boards.hitCount", boardNum);
		
		dto = (BoardsDTO)dao.getReadData("boards.readData", boardNum);
		
		int lineSu = dto.getContent().split("\r\n").length;
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
		
		Map<String, Object> hMap = new HashMap<>();
		hMap.put("searchKey", searchKey);
		hMap.put("searchValue", searchValue);
		hMap.put("groupNum", dto.getGroupNum());
		hMap.put("orderNum", dto.getOrderNum());
		
		BoardsDTO preDTO = (BoardsDTO)dao.getReadData("boards.preReadData", hMap);
		int preBoardNum = 0;
		String preSubject = "";
		
		if(preDTO!=null) {
			preBoardNum = preDTO.getBoardNum();
			preSubject = preDTO.getSubject();
		}
		
		BoardsDTO nextDTO = (BoardsDTO)dao.getReadData("boards.nextReadData", hMap);
		int nextBoardNum = 0;
		String nextSubject = "";
		
		if(nextDTO!=null) {
			nextBoardNum = nextDTO.getBoardNum();
			nextSubject = nextDTO.getSubject();
		}
		
		//�ּ�����
		String param = "pageNum=" + pageNum;
		
		if(!searchValue.equals("")) {
			param += "&searchKey=" + searchKey;
			param += "&searchValue=" +
					URLEncoder.encode(searchValue, "UTF-8");
		}
		
		request.setAttribute("preBoardNum", preBoardNum);
		request.setAttribute("preSubject", preSubject);
		request.setAttribute("nextBoardNum", nextBoardNum);
		request.setAttribute("nextSubject", nextSubject);
		request.setAttribute("params", param);
		request.setAttribute("pageNum", pageNum);
		
		return SUCCESS;
		
	}
	
	public String write() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userId = "";
		String sessionUserId = (String) request.getSession().getAttribute("userId");
		
		if (sessionUserId != null && !sessionUserId.equals("")) {
			userId = sessionUserId;
		}else if (sessionUserId == null || sessionUserId.equals("")) {
			return LOGIN;
		}
		
		dto.setUserId(userId);//return INPUT �������� ������� userId�� ��µ�
		//+ 'dto==null' ���ǹ� ���� ��� DB�� �����. if not, 'dto!=null'�϶� 'dto==null' ���ǹ��� �ǳʶٱ� ������ DB�� ���� �ȵ�
		
		if(dto==null || dto.getMode()==null || dto.getMode().equals("")) {
			
			request.setAttribute("mode", "write");
			
			return INPUT;
		}
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		int maxBoardNum = dao.getIntValue("boards.maxBoardNum");
		
		dto.setBoardNum(maxBoardNum + 1);
		dto.setGroupNum(dto.getBoardNum());//maxBoardNum + 1�� �־ ��
		dto.setDepth(0);
		dto.setOrderNum(0);
		dto.setParent(0);
		
		dao.insertData("boards.insertData", dto);
		
		return SUCCESS;
	}
	
	public String update() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		if(dto.getMode()==null || dto.getMode().equals("")) {
			
			dto = (BoardsDTO)dao.getReadData("boards.readData", dto.getBoardNum());
			
			request.setAttribute("mode", "update");
			request.setAttribute("pageNum", dto.getPageNum());
			
			return INPUT;
			
		}
		
		dao.updateData("boards.updateData", dto);
		
		return SUCCESS;
		
	}
	
	public String reply() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		String userId = "";
		String sessionUserId = (String) request.getSession().getAttribute("userId");
		
		if (sessionUserId != null && !sessionUserId.equals("")) {
			userId = sessionUserId;
		}else if (sessionUserId == null || sessionUserId.equals("")) {
			return LOGIN;
		}
		
		dto.setUserId(userId);
		
		if(dto==null || dto.getMode()==null || dto.getMode().equals("")) {
			
			//�θ��� ������ �о��
			dto = (BoardsDTO)dao.getReadData("boards.readData", dto.getBoardNum());
			
			String temp = "\r\n\r\n������������������������������������\r\n\r\n";
			temp += "[�亯]\r\n";
			
			//�θ� �����͸� �����ؼ� �亯�����ͷ� write.jsp�� ���
			dto.setSubject("[�亯]" + dto.getSubject());
			dto.setContent(dto.getContent() + temp);
			
			request.setAttribute("mode", "reply");
			request.setAttribute("pageNum", dto.getPageNum());
			
			return INPUT;
			
		}
		
		//orderNum ����
		int orderNum = dao.getIntValue("boards.maxOrderNum", dto.getParent());//dto.getBoardNum(): parent�� boardNum
		
		System.out.println(orderNum);
		System.out.println(dto.getParent());
		
		Map<String, Object> hMap = new HashMap<>();
		hMap.put("groupNum", dto.getGroupNum());
		/*hMap.put("orderNum", dto.getOrderNum());*/
		hMap.put("orderNum", orderNum);
		
		dao.updateData("boards.orderNumUpdate", hMap);
		
		//�亯�� �Է�
		int maxBoardNum = dao.getIntValue("boards.maxBoardNum");
		
		dto.setBoardNum(maxBoardNum + 1);
		dto.setDepth(dto.getDepth() + 1);
		/*dto.setOrderNum(dto.getOrderNum() + 1);*/
		dto.setOrderNum(orderNum + 1);
		
		dao.insertData("boards.insertData", dto);
		
		return SUCCESS;
		
	}
	
	public String delete() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		dao.deleteData("boards.deleteData", dto.getBoardNum());
		
		return SUCCESS;
		
	}
}