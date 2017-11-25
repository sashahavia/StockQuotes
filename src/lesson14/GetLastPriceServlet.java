package lesson14;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.FormatUtil;

/**
 * Servlet implementation class GetLastPriceServlet
 */
@WebServlet(
		description = "Servlet that accepts a stock symbol as a parameter, and connects to Yahoo finance to get the last price", 
		urlPatterns = { "/lastPrice.jsp" }, 
		initParams = { 
				@WebInitParam(name = "YahooFinanceURL", 
						value = "http://quote.yahoo.com/d/quotes.csv?s=%s&f=sl1d1t1c1ohgv&e=.csv", 
						description = "A format string with a single format specifier that should be repalced with a stock symbol")
		})
public class GetLastPriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this servlet is protected by the AuthFilter so we do not need to worry about the validity of the user session...
		String symbolParam = request.getParameter("symbol");
		String redirectPage = "symbol.jsp";
		if (FormatUtil.isEmptyString(symbolParam)) {
			request.setAttribute(ProjectConstants.getRequestAttributeNameBadParam(), "A stock symbol is required for the quote lookup.");
		} else {
			String price = StockSymbol.getSymbolsFromDatabase(symbolParam);
			if(price != null){
				request.setAttribute("price", price);
			}
		}
		request.getRequestDispatcher(redirectPage).forward(request, response);
	}

}
