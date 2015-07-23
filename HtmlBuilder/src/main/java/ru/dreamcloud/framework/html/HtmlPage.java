package ru.dreamcloud.framework.html;

import java.util.List;

import ru.dreamcloud.framework.utils.StringBracketsFreeList;

public class HtmlPage {
	
	public List<HtmlElement> headElements = new StringBracketsFreeList<HtmlElement>();
	public List<HtmlElement> bodyElements = new StringBracketsFreeList<HtmlElement>();
	
	@Override
	public String toString() {	
		return "<html><head>"+this.headElements.toString()+"</head><body>"+this.bodyElements.toString() +"</body></html>";
	}
	
	

}
