package com.spstudio.love.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import com.spstudio.love.web.common.GlobalConstant;

public class AuthenticationFilter implements Filter {

	private Logger log = Logger.getLogger(AuthenticationFilter.class);
	
	@Override
	public void destroy() {
		log.info("AuthenticationFilter#destroy has been invoked.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		log.info("AuthenticationFilter#doFilter has been invoked.");
		HttpSession session = ((HttpServletRequest)request).getSession();
		if (session.getAttribute(GlobalConstant.USER_SUBJECT) == null){
//			HttpServletResponse rp = (HttpServletResponse)response;
//			rp.sendError(401);
//			rp.setStatus(200);
		}
		fc.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		log.info("AuthenticationFilter#init has been invoked.");
	}

}
