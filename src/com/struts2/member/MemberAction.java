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

	// �α��� ���� �ҷ�����
	public MemberAction() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String sessionUserId = (String) request.getSession().getAttribute("userId");
		if (sessionUserId != null && !sessionUserId.equals("")) {
			userId = sessionUserId;
		}
	}

	// �̿���
	public String terms() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		return SUCCESS;

	}

	// ȸ������
	public String join() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();
		
		// ȸ������
		if (dto == null || dto.getMode() == null || dto.getMode().equals("")) {
			request.setAttribute("mode", "insert");
			return INPUT;
		}
		
		dao.insertData("member.insert", dto);

		request.setAttribute("userName", dto.getUserName());
		
		return SUCCESS;

	}

	/*
	// ȸ������ �Ϸ�
	public String join_result() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userName = request.getParameter("userName");
		
		request.setAttribute("userName", userName);
		
		return SUCCESS;

	}
	*/

	// �α���
	public String login() throws Exception {

		if (!userId.equals("")) {
			return "myPage";
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = CommonDAOImpl.getInstance();

		// �α���
		if (dto == null || dto.getUserId() == null) {

			request.setAttribute("mode", "loin");

			return INPUT;

		}
		
		String userId = (String) dao.getReadData("member.login", dto);

		// �α��� ����
		if (userId == null) {
			String message = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			request.setAttribute("message", message);
			return INPUT;
		}

		request.getSession().setAttribute("userId", userId);

		return SUCCESS;

	}

	// �α׾ƿ�
	public String logout() throws Exception {

		HttpSession session = ServletActionContext.getRequest().getSession();

		session.removeAttribute("userId");
		session.invalidate();

		return SUCCESS;

	}

	// ���̵�, ��й�ȣ ã��
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
		
			// ���̵� ã��
		} else if (dto.getMode().equals("findId")) {

			String userId = (String) dao.getReadData("member.findId", dto);
			
			if (userId != null && !userId.equals("")) {
				message = "���̵�� [" + userId + "] �Դϴ�.";
			} else {
				message = "���̵� �������� �ʽ��ϴ�.";
				request.setAttribute("message", message);
				return INPUT;
			}

			// ��й�ȣ ã��
		} else if (dto.getMode().equals("findPwd")) {

			String userPwd = (String) dao.getReadData("member.findPwd", dto);

			if (userPwd != null && !userPwd.equals("")) {
				message = "��й�ȣ�� [" + userPwd + "] �Դϴ�.";
			} else {
				message = "��ġ�ϴ� ������ �������� �ʽ��ϴ�.";
				request.setAttribute("message", message);
				return INPUT;
			}

		}

		request.setAttribute("message", message);
		request.setAttribute("mode", "loin");

		return SUCCESS;

	}

	// ���� ������
	public String myPage() throws Exception {

		if (userId.equals("")) {
			return LOGIN;
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("userId", userId);

		return SUCCESS;

	}

	// ȸ�� ���� ����
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

	// ȸ�� Ż��
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
