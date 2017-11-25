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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stock Quote</title>
</head>
<body>
  <form method="GET" action="lastPrice.jsp">
  <table cellspacing="0" cellpadding="5">
    <tr>
      <th colspan="2">
        Welcome <%= session.getAttribute(ProjectConstants.getSessionAttributeNameAuthUser()) %>
        &nbsp;<br>
        <h2 style="color:#7B2240"><%= reqMessage %></h2>
      </th>
    </tr>
    <tr>
    	<th>Please enter one of these symbols</th>
    	<% 
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/HW3");
            Statement statement = connection.createStatement() ;
            ResultSet rs = statement.executeQuery("Select Symbol from Stocks") ;
            ResultSetMetaData rsMeta = rs.getMetaData();
			int colCount = rsMeta.getColumnCount();
        %>
    	<td> 
    	
    	<% while(rs.next()){ %>
            <ul>
            	<% for(int i = 1; i <= colCount; i++){ %>
                	<li style="display: inline; float: left; padding: 10px;"> <%= rs.getString(i) %></li>
                <%} %>
            </ul>
            <% } %>

    	</td>
    	<% 
    	try { if (statement != null) statement.close(); } catch (Exception e) {};
	    try { if (rs != null) rs.close(); } catch (Exception e) {};
	    try { if (connection != null) connection.close(); } catch (Exception e) {}; %>
    </tr>
  	<tr>
  	 <th>Enter a Symbol:</th>
  	  <td>
  	     <input type="text" id="symbol" name="symbol" value="" maxlength="10" size="10">
  	     &nbsp;&nbsp;&nbsp;
 	     <input type="submit" value="Get Price">  
  	  </td>
  	</tr>
  	<tr>
  	  <th>Last Price:</th>
  	  <td>
  	     <input type="text" id="price" name="price" value="<%= lastPrice %>" maxlength="10" size="10" readonly>
  	  </td>
  	</tr>
  	<tr>
  	  <th>You selected a symbol:</th>
  	  <td>
  	  	<input type="text" id="symbol2" name="symbol" value="<%= symbol %>" maxlength="10" size="10" readonly>
  	  </td>
  	</tr>
  	<tr>
  	  <th>&nbsp;<br><a href="<%= request.getContextPath()%>/logout.jsp"><b>LOGOUT</b></a>
  	  </th>
  	  <td>&nbsp;</td>
  	</tr>
  </table>  	
  </form>
</body>
</html>