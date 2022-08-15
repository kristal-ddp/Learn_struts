package com.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.util.dao.CommonDAO;
import com.util.dao.CommonDAOImpl;

public class MemberAction extends DispatchAction{
	
	CommonDAO dao = CommonDAOImpl.getInstance();

	public ActionForward terms(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("terms");
	}
	
	public ActionForward created(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String mode = request.getParameter("mode");
		
		if(mode==null) {//join
			request.setAttribute("mode", "insert");
		}else {//update
			
			String userId = request.getParameter("userId");
			
			MemberForm dto = (MemberForm)dao.getReadData("member.readData", userId);
			
			request.setAttribute("dto", dto);
			request.setAttribute("mode", mode);
			
		}
		
		return mapping.findForward("join");
	}
	
	public ActionForward created_ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("join_ok");
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("login");
	}
	
	public ActionForward login_ok(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		MemberForm dto = (MemberForm)dao.getReadData("member.readData", userId);
		
		if(dto==null) {
			request.setAttribute("message", "�������� �ʴ� ���̵��Դϴ�.");
			return mapping.findForward("login");
		}else if(dto!=null && !dto.getUserPwd().equals(userPwd)){
			request.setAttribute("message", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			return mapping.findForward("login");
		}
		
		/*userId�� ��� session�� �÷��־��Ѵ�
		(����� ������ pageNum�� 1���� ���Ƿ�, ActionForward ���)*/
		HttpSession session = request.getSession();
		session.setAttribute("memberForm", userId);
		
		return mapping.findForward("login_ok");
	}
	
	public ActionForward logout_ok(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.invalidate();//session�� session�� ����
		
		return mapping.findForward("logout_ok");
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("find");
	}
	
	public ActionForward find_ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("find_ok");
	}
	
	public ActionForward myPage(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("myPage");
	}
	
	public ActionForward withdraw(ActionMapping mapping, ActionForm form,//��
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("withdraw");
	}
	
	public ActionForward withdraw_ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("userId");
		
		dao.deleteData("member.withdraw", id);
		
		ActionForward af = new ActionForward();
		af.setRedirect(true);
		af.setPath("/member.do?method=withdraw");///
		
		return af;
	}
	
}