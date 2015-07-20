package ru.dreamcloud.framework.rest;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.dreamcloud.framework.html.PageBuilder;

/**
 * Servlet implementation class PageInterpreterServlet
 */
public class PageInterpreterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		
		File xmlFile = new File(contextPath + "WEB-INF/template/index.html");
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			PageBuilder pageHtml = new PageBuilder();

			// Actual logic goes here.
			PrintWriter out = response.getWriter();
			out.println("<body>");			
			out.println(pageHtml.getHtmlResult(xmlFile));
			out.println("</body>");
			out.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
