package ru.dreamcloud.framework.html;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ru.dreamcloud.framework.widgets.WidgetConstructor;
import ru.dreamcloud.framework.widgets.WidgetValidator;

public class HtmlHandler extends DefaultHandler {
	private String currentQName;
	private String resultHtml = new String();
	private HtmlPage htmlPage = new HtmlPage();
	private String tabs = new String();
	private WidgetConstructor constructor = new WidgetConstructor();
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentQName = qName;
		HtmlTag tag = constructor.constructHtmlTag(qName, false);
		//System.out.println(tabs + "Start Element :" + qName);		
		for (int a = 0; a < attributes.getLength(); a++) {
			String attributeName = attributes.getLocalName(a);
			boolean isAddToClass = attributeName.equalsIgnoreCase("class") && constructor.getValidator().validate(qName);
			String attributeValue = isAddToClass ? attributes.getValue(a) + " " + qName : attributes.getValue(a);
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
		HtmlTag tag = constructor.constructHtmlTag(qName, true);
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
