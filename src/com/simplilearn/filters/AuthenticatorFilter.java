package com.simplilearn.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticatorFilter
 */
@WebFilter(filterName="authFilter" , urlPatterns= {"/dashboard", "/student", "/book"})
public class AuthenticatorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthenticatorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpSession session = httpRequest.getSession();
		String userName = (String)session.getAttribute("username");
		if(userName!=null) {
			chain.doFilter(request, response);	
		} else {
			PrintWriter out = httpResponse.getWriter();
			out.println("<html><body>");
			out.println("<h1 align=\"center\">Library Management System</h1>");
			out.println("<p>Please Login first and then try to access this page. </p>");
			out.println("<p>Please click <a href=login> here </a> to login.</p>");

			out.println("</body></html>");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
