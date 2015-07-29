package ru.dreamcloud.framework.widgets;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WidgetProcessor {
	
	private String processedPage;	

	public String getProcessedPage() {
		return processedPage;
	}

	public void setProcessedPage(String processedPage) {
		this.processedPage = processedPage;
	}

	public void preProcessPage(){		
	}
	
	public void processPage(Document doc){
		String initJs = "CKEDITOR.disableAutoInline=true;";
		doc.title("DreamCloud Framework Page");
		doc.head().appendElement("script").attr("src", "js/ckeditor/ckeditor.js");
		Elements widgets = doc.getElementsByTag("widget-text");
		for (org.jsoup.nodes.Element element : widgets) {				
			Attributes attrs = element.attributes();
			org.jsoup.nodes.Element div = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"),"",attrs);
			div.addClass(element.tagName());				
			div.attr("contenteditable", "true");
			div.append(element.html());				
			initJs += "CKEDITOR.inline('"+div.attributes().get("id")+"');"; 
			element.replaceWith(div);				
		}			
		doc.body().appendElement("script").appendText(initJs);
		processedPage = doc.html();
	}
	
	public void postProcessPage(){		
	}

}
