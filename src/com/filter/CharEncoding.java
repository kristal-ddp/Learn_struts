package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharEncoding implements Filter{
	
	private String charset;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String uri;
		
		if(request instanceof HttpServletRequest) {
			
			HttpServletRequest req = (HttpServletRequest)request;//object�� �ٲ�Ƿ� downcasting �������
			//client�� ������ ���� ��, client��HSR��ۡ�object��Server
			uri = req.getRequestURI();
			
			if(req.getMethod().equalsIgnoreCase("post")) {
				
				if(uri.indexOf("ajax.do")!=-1) {
					req.setCharacterEncoding("euc-kr");
				}else {
					req.setCharacterEncoding(charset);
				}
			}
		}
		
		chain.doFilter(request, response);//�̰� �����ָ� �� �Ѿ
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		charset = filterConfig.getInitParameter("charset");//xml���� �Ѿ���� data
		if(charset==null) {//���� �� �Ѿ�´ٸ�
			charset = "UTF-8";//�ʱ�ȭ
		}
		
	}

}
