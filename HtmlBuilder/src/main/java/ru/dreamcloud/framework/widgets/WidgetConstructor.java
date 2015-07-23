package ru.dreamcloud.framework.widgets;

import ru.dreamcloud.framework.html.HtmlTag;

public class WidgetConstructor {
	
	private WidgetValidator validator = new WidgetValidator();	
	
	public WidgetValidator getValidator() {
		return validator;
	}

	public void setValidator(WidgetValidator validator) {
		this.validator = validator;
	}

	public HtmlTag constructHtmlTag(String tagName, boolean closeTag){
		HtmlTag tag;		
		if (this.validator.validate(tagName)) {
			tag = new HtmlTag("div",closeTag);		
		} else {
			tag = new HtmlTag(tagName,closeTag);
		}
		return tag;
	}

}
