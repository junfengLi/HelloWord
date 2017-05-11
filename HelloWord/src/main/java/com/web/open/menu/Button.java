package com.web.open.menu;

/**
 * 
 * ClassName:Button 微信自定义菜单按钮
 * @Date	 2015	2015年8月27日		上午8:44:37
 *
 * @see
 */
public class Button {

	public Button(){}
	
	public Button(String name){
		this.name = name;
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
