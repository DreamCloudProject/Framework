package ru.dreamcloud.framework.widgets;

public class WidgetValidator {
	
	public boolean validate(String tagName){
		boolean result = false;
		if(tagName.equalsIgnoreCase("widget")){
			result = true;
		}
		return result;		
	}

}
