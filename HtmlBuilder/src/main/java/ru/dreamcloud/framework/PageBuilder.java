package ru.dreamcloud.framework;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ccil.cowan.tagsoup.jaxp.SAXParserImpl;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ru.dreamcloud.framework.html.HtmlHandler;

public class PageBuilder {
	
	private HtmlHandler handler = new HtmlHandler();
	
	/*public static void main(String[] args) {
		File xmlFile = new File("../REST/src/main/webapp/WEB-INF/template/index.html");
		System.out.println(getParsedHtml(xmlFile));
	}*/
	
	public String getHtmlResult(File xmlFile) {		
		try {
			//parseSAX(xmlFile);
			parseSAXHtml(xmlFile);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return handler.getResultHtml();
	}
	
	public String getParsedHtml(File htmlFile){
		String html = new String();
		try {
			org.jsoup.nodes.Document doc = Jsoup.parse(htmlFile, "UTF-8");
			doc.title("DreamCloud Framework Page");
			html = doc.html();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}
	
	private void parseSAXHtml(File xmlFile) {
		try {
			SAXParserImpl htmlParser = SAXParserImpl.newInstance(null);
			htmlParser.parse(xmlFile,handler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				

	}

	private void parseSAX(File xmlFile) {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();			
			saxParser.parse(xmlFile, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parseDOM(InputStream xmlFile) {
		try {
			DocumentBuilderFactory xmlFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder xmlBuild = xmlFact.newDocumentBuilder();
			Document xmlDocument = xmlBuild.parse(xmlFile);
			Element root = xmlDocument.getDocumentElement();
			parseNode(root);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void parseNode(Element root) {
		NodeList nodelist = root.getChildNodes();
		for (int i = 0; i < nodelist.getLength(); i++) {
			if (nodelist.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nodelist.item(i);
				System.out.println(element.getNodeName());
				parseNodeAttributes(element);
				parseNode(element);
			}
		}
	}

	private void parseNodeAttributes(Element element) {
		NamedNodeMap attributes = element.getAttributes();
		for (int a = 0; a < attributes.getLength(); a++) {
			Node theAttribute = attributes.item(a);
			System.out.println(theAttribute.getNodeName() + "=" + theAttribute.getNodeValue());
		}
	}
}
