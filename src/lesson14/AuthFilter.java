package lesson14;

import java.io.IOException;
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
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(
		description = "Checks whether there is a valid user in the session and redirects to the login page if there is none.", 
		urlPatterns = { "/*" }
		)
public class AuthFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String forwardTo = "login.jsp";
		
		UserData sessionUser = (UserData) httpSession.getAttribute(ProjectConstants.getSessionAttributeNameAuthUser());
		
		//if there is no user in the current session, and the request is not for the "login" or "authentication" pages, 
		//redirect the user to the login screen with some message.
		if (null == sessionUser &&
			httpRequest.getRequestURI().indexOf("auth.jsp")==-1 && 
			httpRequest.getRequestURI().indexOf("login.jsp")==-1 
    		) {
			
			//this is one of the ways how we pass data/objects between different pages and/or Servlets
			httpSession.setAttribute(
					ProjectConstants.getSessionAttributeNameLoginMessage(), 
					"Your Web session has expired. Please log in to access this application.");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/"+ forwardTo);
		} else {
			//user was found in the session = just proceed to the next filter, or servlet?JSP
			chain.doFilter(request, response);
		}
	}
	
    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
