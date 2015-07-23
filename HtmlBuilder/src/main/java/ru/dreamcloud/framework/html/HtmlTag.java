package ru.dreamcloud.framework.html;

import java.util.List;

import ru.dreamcloud.framework.utils.StringBracketsFreeList;

public class HtmlTag {
	private String tagHtmlStr = null;
	public List<HtmlAttribute> attributes = new StringBracketsFreeList<HtmlAttribute>();

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
