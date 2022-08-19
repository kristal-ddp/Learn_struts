package com.struts2.member;

import javax.servlet.http.HttpServletRequest;

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
	
	public MemberAction() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String sessionUserId = (String) request.getSession().getAttribute("userId");
		if (sessionUserId != null && !sessionUserId.equals("")) {
			userId = sessionUserId;
		}
	}

	private String terms() throws Exception{
		
		if (!userId.equals("")) {
			return ERROR;
		}
		
		return SUCCESS;
		
	}
	
	private String join() throws Exception{

		if (!userId.equals("")) {
			return ERROR;
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		
		if (dto == null || dto.getMode() == null || dto.getMode().equals("")) {
			request.setAttribute("mode", "join");
			return INPUT;
		}

		CommonDAO dao = CommonDAOImpl.getInstance();
		
		
		
		
		
		
		
		
		
		return SUCCESS;
		
	}

	private String login() throws Exception{

		if (!userId.equals("")) {
			return ERROR;
		}
		
		
		
		return SUCCESS;
		
	}

	private String logout() throws Exception{
		
		return SUCCESS;
		
	}

	private String find() throws Exception{

		if (!userId.equals("")) {
			return ERROR;
		}
		
		
		
		return SUCCESS;
		
	}

	private String myPage() throws Exception{

		if (userId.equals("")) {
			return ERROR;
		}
		
		
		
		return SUCCESS;
		
	}

	private String withdraw() throws Exception{

		if (!userId.equals("")) {
			return ERROR;
		}
		
		
		
		return SUCCESS;
		
	}

	private String temp() throws Exception{
		
		return SUCCESS;
		
	}
	
}
