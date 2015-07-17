package ru.dreamcloud.html;

public class HtmlAttribute {
	public String name = null;
	public String value = null;
	
	public HtmlAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return this.name + "=\"" + this.value + "\"";
	}
}
