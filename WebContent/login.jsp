<%@ page import="lesson14.ProjectConstants" %>

<%!
	public void jspInit (){
		ServletContext application = this.getServletContext();
		// We are getting an "init paramater" from the "ServletContext" object
		// explanation here:
		// http://stackoverflow.com/questions/14665037/getting-the-init-parameters-in-a-servlet 	
	
		System.out.println(
			"getInitParameter() from \"this\" object, i.e. servlet::" + 
				getInitParameter(ProjectConstants.getAuthDataSourceParamName())+"; "+
			"getInitParameter() from the ServletContext::" + 
				application.getInitParameter(ProjectConstants.getAuthDataSourceParamName())
			);
	
		String dsName = application.getInitParameter(ProjectConstants.getAuthDataSourceParamName());
		application.setAttribute(ProjectConstants.getSessionAttributeNameDataSource(), dsName);
		ProjectConstants.setAuthDataSourceName (dsName);
  	}
%>

<%
  	//We might have an error message here from the AuthFilter.java
  	//We could have passed it as a request attribute or as a session attribute.
  	String errMessage = (String) session.getAttribute(ProjectConstants.getSessionAttributeNameLoginMessage());
  	if (null != errMessage) {
	  	//remove the message from the user session after we display it here:
	  	  
  	} else {
	  	errMessage = "";
  	}
  	session.removeAttribute("message");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login Screen</title>	
	<!-- <link href="css/login.css" rel="stylesheet"> -->
	<style>
        <%@include file="css/login.css" %>
	</style>
</head>
<body>
	<%-- auth.jsp is a URL that actually refers to a servlet - see AuthenticateServlet.java --%>
	<form id="userForm" action="auth.jsp" method="post">
		<h2>Login:</h2>
	 	<input type="text" name="username" placeholder="username" value="" maxlength="30" size="15">
	 	<!-- <input type="text" name="username" value="" maxlength="30" size="15" autocomplete="off"> -->
	 	<br>
	 	<input type="password" placeholder="password" name="pwd" maxlength="30" size="15">
	 	<!-- <input type="password" name="pwd" maxlength="30" size="15" autocomplete="off"> -->
	  	<br>
	  	<br>
	 	<button type="submit" value="Login">Login</button>
	 	<h2><%= errMessage %></h2>
	</form>
</body>
</html>