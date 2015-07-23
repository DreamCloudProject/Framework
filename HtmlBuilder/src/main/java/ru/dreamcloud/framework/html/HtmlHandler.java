package ru.dreamcloud.framework.html;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HtmlHandler extends DefaultHandler {
	private String currentQName;
	private String resultHtml = new String();
	private String tabs = new String();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentQName = qName;
		HtmlTag tag = new HtmlTag("div",false);
		//System.out.println(tabs + "Start Element :" + qName);		
		for (int a = 0; a < attributes.getLength(); a++) {
			String attributeName = attributes.getLocalName(a);
			String attributeValue = attributeName.equalsIgnoreCase("class") ? attributes.getValue(a) + " " + qName : attributes.getValue(a);
			HtmlAttribute htmlAttr = new HtmlAttribute(attributeName, attributeValue);		
			tag.attributes.add(htmlAttr);
			//System.out.println(tabs + attributeName + "=" + attributeValue);
		}
		this.resultHtml += tag.toString();
		//System.out.println(tabs + tag.toString());
		tabs += "\t";
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		tabs = tabs.substring(0, tabs.length() - 1);
		HtmlTag tag = new HtmlTag("div", true);
		this.resultHtml += tag.toString();
		//System.out.println(tabs + tag.toString());
		//System.out.println(tabs + "End Element :" + qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (length > 0) {
			String str = new String(ch, start, length);
			this.resultHtml += str;
			//System.out.println(tabs + currentQName + " = " + str);
			//System.out.println(tabs + str);						
		}
	}
	
	public String getResultHtml() {
		//System.out.println(this.resultHtml);
		return this.resultHtml;
	}

}
