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
  if (null!=errMessage) {
	  //remove the message from the user session after we display it here:
	  	  
  } else {
	  errMessage = "";
  }
  session.removeAttribute("message");
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Screen</title>
</head>

<body>

<h2 style="color:#7B2240"><%= errMessage %></h2>

<h2>Login:</h2>

<%-- auth.jsp is a URL that actually refers to a servlet - see AuthenticateServlet.java --%>
<form id="userForm" action="auth.jsp" method="post">
 Enter username: <br>
 <!-- <input type="text" name="username" value="" maxlength="30" size="15" autocomplete="off"> -->
 <input type="text" name="username" value="" maxlength="30" size="15">
 <br>
 <br>
 Enter password:<br>
 <!-- <input type="password" name="pwd" maxlength="30" size="15" autocomplete="off"> -->
 <input type="password" name="pwd" maxlength="30" size="15">
  <br>
  <br>
 <input type="submit" value="Login">
</form>
</body>
</html>