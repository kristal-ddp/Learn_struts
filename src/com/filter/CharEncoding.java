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
			
			HttpServletRequest req = (HttpServletRequest)request;//object로 바뀌므로 downcasting 해줘야함
			//client가 서버로 보낼 때, client→HSR→○→object→Server
			uri = req.getRequestURI();
			
			if(req.getMethod().equalsIgnoreCase("post")) {
				
				if(uri.indexOf("ajax.do")!=-1) {
					req.setCharacterEncoding("euc-kr");
				}else {
					req.setCharacterEncoding(charset);
				}
			}
		}
		
		chain.doFilter(request, response);//이거 안해주면 안 넘어감
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		charset = filterConfig.getInitParameter("charset");//xml에서 넘어오는 data
		if(charset==null) {//만약 못 넘어온다면
			charset = "UTF-8";//초기화
		}
		
	}

}
