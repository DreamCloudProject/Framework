package ru.dreamcloud.html;

public class HtmlElement {
	public HtmlTag startTag = null;
	public String textContent = null;
	public HtmlTag endTag = null;

	@Override
	public String toString() {
		return this.startTag.toString() + this.textContent + this.endTag.toString();
	}

}
