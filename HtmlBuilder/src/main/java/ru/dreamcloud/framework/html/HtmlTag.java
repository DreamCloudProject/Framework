package ru.dreamcloud.framework.html;

import java.util.List;

public class HtmlTag {
	private String tagHtmlStr = null;
	public List<HtmlAttribute> attributes = new HtmlAttributesList<HtmlAttribute>();

	public HtmlTag(String tagName,boolean closeTag){		
		if(!closeTag){
			this.tagHtmlStr = "<"+tagName+"@attributes>";	
		} else {
			this.tagHtmlStr = "</"+tagName+">";
		}
			
	}
	
	@Override
	public String toString() {
		return this.tagHtmlStr.replace("@attributes", this.attributes.toString());
	}

}
