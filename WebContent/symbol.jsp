<%@ page import="utils.FormatUtil" %>
<%@ page import="lesson14.ProjectConstants" %>
<%@ page import="lesson14.StockSymbol" %>
<%@ page import="java.sql.*" %>

<%
  String lastPrice =  FormatUtil.getValidString ((String)request.getAttribute("price"));
  String reqMessage = FormatUtil.getValidString ((String)request.getAttribute(ProjectConstants.getRequestAttributeNameBadParam ()));
  String symbol = FormatUtil.getValidString (request.getParameter("symbol"));
  
  
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Stock Quote</title>
	<link rel="stylesheet" href="css/symbol.css">
</head>
<body>
	<form method="GET" action="lastPrice.jsp">
  		
    	<div id="welcome">
      		<h2 class="welcome">
        		Welcome <%= session.getAttribute(ProjectConstants.getSessionAttributeNameAuthUser()) %>
        		&nbsp;<br></h2>
        		<h2><%= reqMessage %></h2>
      	</div>	
      	<div id="options">
    		<h3>Please enter one of these symbols</h3>
    		<% 
            	Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/HW3");
            	Statement statement = connection.createStatement() ;
            	ResultSet rs = statement.executeQuery("Select Symbol from Stocks") ;
            	ResultSetMetaData rsMeta = rs.getMetaData();
				int colCount = rsMeta.getColumnCount();
        	%>
    	
    	
    	<% while(rs.next()){ %>
            <ul>
            	<% for(int i = 1; i <= colCount; i++){ %>
                	<li> <%= rs.getString(i) %></li>
                <%} %>
            </ul>
            <% } %>

    	
    	<% 
    	try { if (statement != null) statement.close(); } catch (Exception e) {};
	    try { if (rs != null) rs.close(); } catch (Exception e) {};
	    try { if (connection != null) connection.close(); } catch (Exception e) {}; %>
	    </div>	
	    <div id="select">
  	 		<label>Enter a Symbol:
  	 			<input type="text" id="symbol" name="symbol" value="" maxlength="10" size="10">
  	     		&nbsp;&nbsp;&nbsp;
 	 			<input type="submit" value="Get Price"> 
 	 			</label>
 	 	</div>
 	 	<div id="select1">
  	 		<label>Last Price:
  	  			<input type="text" id="price" name="price" value="<%= lastPrice %>" maxlength="10" size="10" readonly>
  	  		</label>
  	  	</div>
  	  	<div id="select2">
  	  		<label>You selected a symbol:
  	  			<input type="text" id="symbol2" name="symbol" value="<%= symbol %>" maxlength="10" size="10" readonly>
  	  		</label>
 			<h4><a href="<%= request.getContextPath()%>/logout.jsp"><b>LOGOUT</b></a></h4>
  		</div>
  	</form>  
</body>
</html>