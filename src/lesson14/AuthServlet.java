package lesson14;

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet(description = "Authenticates against the database", 
urlPatterns = { "/auth.jsp" })
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String redirectPage = "symbol.jsp";
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("pwd");
		UserData user = AuthHelper.authenticateUser(userName, userPassword);
		if (null!=user) {
			session.setAttribute(ProjectConstants.getSessionAttributeNameAuthUser(), user);
		} else {
			redirectPage = "login.jsp";
			session.setAttribute(ProjectConstants.getSessionAttributeNameLoginMessage(), 
					"Please provide a valid username /password.");
		}
		request.getRequestDispatcher(redirectPage).forward(request, response);
	}

}
