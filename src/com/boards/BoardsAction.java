package com.boards;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.MyPage;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class BoardsAction extends DispatchAction {

	// write, update
	public ActionForward write(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommonDAO dao = CommonDAOImpl.getInstance();
		
		String mode = request.getParameter("mode");
		
		String refererUrl = (String) request.getHeader("REFERER");
		request.setAttribute("refererUrl", refererUrl);

		// write
		if (mode == null) {
			request.setAttribute("mode", "insert");

			// update
		} else if (mode.equals("update")) {

			String pageNum = request.getParameter("pageNum");
			
			String num = request.getParameter("boardNum");
			if (num == null) {
				// 잘못된 접근입니다.
				return mapping.findForward("list");
			}
			int boardNum = Integer.parseInt(num);
			
			BoardsForm dto = (BoardsForm) dao.getReadData("boards.readData", boardNum);
			if (dto == null) {
				// 존재하지 않는 글입니다.
				return mapping.findForward("list");
			}

			request.setAttribute("dto", dto);
			request.setAttribute("mode", mode);
			request.setAttribute("pageNum", pageNum);

		}

		return mapping.findForward("write");
	}

	public ActionForward boards_ok(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		BoardsForm dto = (BoardsForm) form;

		String mode = request.getParameter("mode");
		String pageNum = request.getParameter("pageNum");
		int currentPage = 1;
		
		if (pageNum  != null) {
			currentPage = Integer.parseInt(pageNum);
		}

		if (mode.equals("insert")) {
			
			int maxNum = dao.getIntValue("boards.maxNum");
			
			dto.setBoardNum(maxNum + 1);
			
			dao.insertData("boards.insertData", dto);
			
		} else if (mode.equals("update")) {
			
			dao.updateData("boards.updateData", dto);
			
		} else if (mode.equals("delete")) {
			
			int boardNum = dto.getBoardNum();
			
			dao.deleteData("boards.deleteData", boardNum);
			
		}
		
		ActionForward af = new ActionForward();
		af.setRedirect(true);
		af.setPath("/boards.do?method=list&pageNum=" + currentPage);
		return af;

	}

	public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		String cp = request.getContextPath();
		MyPage myPage = new MyPage();
		
		String pageNum = request.getParameter("pageNum");

		int numPerPage = 5;
		int totalPage = 0;
		int totalDataCount = 0;
		int currentPage = 1;
		
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if (searchKey == null) {
			searchKey = "subject";
			searchValue = "";
		}
		if (request.getMethod().equalsIgnoreCase("GET")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		Map<String, Object> hMap = new HashMap<>();
		hMap.put("searchKey", searchKey);
		hMap.put("searchValue", searchValue);
		
		totalDataCount = dao.getIntValue("boards.dataCount", hMap);

		if (totalDataCount != 0) {
			totalPage = myPage.getPageCount(numPerPage, totalDataCount);
		}

		if (currentPage > totalPage) {
			currentPage = totalPage;
		}

		int start = (currentPage - 1) * numPerPage + 1;
		int end = currentPage * numPerPage;

		hMap.put("start", start);
		hMap.put("end", end);
		
		List<Object> list = dao.getListData("boards.getList", hMap);

		String searchParam = "";
		
		if (!searchValue.equals("")) {
			searchValue = URLEncoder.encode(searchValue, "UTF-8");
			searchParam = "&searchKey=" + searchKey + "&searchValue=" + searchValue;
		}
		
		String listUrl = cp + "/boards.do?method=list" + searchParam;
		String viewUrl = cp + "/boards.do?method=view&pageNum=" + currentPage + searchParam;
		
		request.setAttribute("list", list);
		request.setAttribute("viewUrl", viewUrl);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageIndexList", myPage.pageIndexList(currentPage, totalPage, listUrl));
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalDataCount", totalDataCount);
		
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userId = "asd";
		request.setAttribute("userId", userId);
		
		CommonDAO dao = CommonDAOImpl.getInstance();
		String cp = request.getContextPath();
		
		String num = request.getParameter("boardNum");
		if (num == null) {
			// 잘못된 접근입니다.
			return mapping.findForward("list");
		}
		int boardNum = Integer.parseInt(num);
		
		dao.updateData("boards.hitCount", boardNum);
		BoardsForm dto = (BoardsForm) dao.getReadData("boards.readData", boardNum);
		if (dto == null) {
			// 존재하지 않는 글입니다.{
			return mapping.findForward("list");
		}
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));

		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		if (searchKey == null) {
			searchKey = "subject";
			searchValue = "";
		}
		if (request.getMethod().equalsIgnoreCase("GET")) {
			searchValue = URLDecoder.decode(searchValue, "UTF-8");
		}
		
		String param = "";
		if (!searchValue.equals("")) {
			searchValue = URLEncoder.encode(searchValue, "UTF-8");
			param = "&searchKey=" + searchKey + "&searchValue=" + searchValue;
		}
		String listUrl = cp + "/boards.do?method=list" + param;
		
		String pageNum = request.getParameter("pageNum");
		String paramView = "&boardNum=" + boardNum + "&pageNum=" + pageNum + param;

		listUrl += "&pageNum=" + pageNum;
		
		request.setAttribute("dto", dto);
		request.setAttribute("paramView", paramView);
		request.setAttribute("listUrl", listUrl);

		return mapping.findForward("view");
		
	}

	public ActionForward temp(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("temp");
	}

}
