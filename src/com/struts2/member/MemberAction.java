package com.struts2.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class MemberAction extends ActionSupport implements Preparable, ModelDriven<MemberDTO> {

	private static final long serialVersionUID = 1L;

	private MemberDTO dto;

	public MemberDTO getDto() {
		return dto;
	}

	@Override
	public MemberDTO getModel() {
		return dto;
	}

	@Override
	public void prepare() throws Exception {
		dto = new MemberDTO();
	}

	String userId = "";
	MemberDTO sessionMemberDTO;

	// 로그인 정보 불러오기
	public MemberAction() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String sessionUserId = (String) request.getSession().getAttribute("userId");
		if (sessionUserId != null && !sessionUserId.equals("")) {
			userId = sessionUserId;
		}
	}

	// 이용약관
	public String terms() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		return SUCCESS;

	}

	// 회원가입
	public String join() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		// 회원가입
		if (dto == null || dto.getMode() == null || dto.getMode().equals("")) {
			request.setAttribute("mode", "insert");
			return INPUT;
		}
		
		dao.insertData("member.insert", dto);

		request.setAttribute("userName", dto.getUserName());
		
		return SUCCESS;

	}

	/*
	// 회원가입 완료
	public String join_result() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userName = request.getParameter("userName");
		
		request.setAttribute("userName", userName);
		
		return SUCCESS;

	}
	*/

	// 로그인
	public String login() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();

		// 로그인
		if (dto == null || dto.getUserId() == null) {

			request.setAttribute("mode", "loin");

			return INPUT;

		}
		
		String userId = (String) dao.getReadData("member.login", dto);

		// 로그인 실패
		if (userId == null) {
			String message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			return INPUT;
		}

		request.getSession().setAttribute("userId", userId);

		return SUCCESS;

	}

	// 로그아웃
	public String logout() throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();

		session.removeAttribute("userId");
		session.invalidate();

		return SUCCESS;

	}

	// 아이디, 비밀번호 찾기
	public String find() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();

		String message = "";
		String mode = request.getParameter("mode");
		
		if (dto.getUserTel() == null || dto.getUserTel().equals("")) {
			
			return INPUT;
		
			// 아이디 찾기
		} else if (dto.getMode().equals("findId")) {

			String userId = (String) dao.getReadData("member.findId", dto);
			
			if (userId != null && !userId.equals("")) {
				message = "아이디는 [" + userId + "] 입니다.";
			} else {
				message = "아이디가 존재하지 않습니다.";
				request.setAttribute("message", message);
				return INPUT;
			}

			// 비밀번호 찾기
		} else if (dto.getMode().equals("findPwd")) {

			String userPwd = (String) dao.getReadData("member.findPwd", dto);

			if (userPwd != null && !userPwd.equals("")) {
				message = "비밀번호는 [" + userPwd + "] 입니다.";
			} else {
				message = "일치하는 정보가 존재하지 않습니다.";
				request.setAttribute("message", message);
				return INPUT;
			}

		}

		request.setAttribute("message", message);
		request.setAttribute("mode", "loin");

		return SUCCESS;

	}

	// 마이 페이지
	public String myPage() throws Exception {

		if (userId.equals("")) {
			return LOGIN;
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userId", userId);

		return SUCCESS;

	}

	// 회원 정보 수정
	public String update() throws Exception {

		if (userId.equals("")) {
			return LOGIN;
		}

		System.out.println("asd");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();

		if (dto == null || dto.getMode() == null || dto.getMode().equals("")) {

			MemberDTO dto = (MemberDTO) dao.getReadData("member.readData", userId);

			request.setAttribute("dto", dto);
			request.setAttribute("mode", "update");

			return INPUT;

		}

		return SUCCESS;

	}

	// 회원 탈퇴
	public String withdraw() throws Exception {

		if (!userId.equals("")) {
			return LOGIN;
		}

		return SUCCESS;

	}

	public String temp() throws Exception {

		return SUCCESS;

	}

}
