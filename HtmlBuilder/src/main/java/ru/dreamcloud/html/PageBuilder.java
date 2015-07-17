package ru.dreamcloud.html;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PageBuilder {
	
	private HtmlHandler handler = new HtmlHandler();
	
	/*public static void main(String[] args) {
		File xmlFile = new File("index.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();		
			Source xmlSource = new DOMSource(doc);
			Result outputTarget = new StreamResult(outputStream);
			TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
			InputStream is = new ByteArrayInputStream(outputStream.toByteArray());
			System.out.println(getHtmlResult(is));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public String getHtmlResult(InputStream is) {
		parseSAX(is);
		return handler.getResultHtml();
	}

	private void parseSAX(InputStream xmlFile) {
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
