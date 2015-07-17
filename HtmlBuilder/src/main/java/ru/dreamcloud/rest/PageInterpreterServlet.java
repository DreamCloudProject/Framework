package ru.dreamcloud.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import ru.dreamcloud.html.PageBuilder;

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
		
		File xmlFile = new File(contextPath + "index.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
			Source xmlSource = new DOMSource(doc);
			Result outputTarget = new StreamResult(outputStream);
			TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
			InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			PageBuilder pageHtml = new PageBuilder();

			// Actual logic goes here.
			PrintWriter out = response.getWriter();
			out.println("<body>");			
			out.println(pageHtml.getHtmlResult(is));
			out.println("</body>");
			out.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

}
