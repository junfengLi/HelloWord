package com.web.open.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * ClassName:SubButton 具备触发事件的按钮
 * Function: TODO ADD FUNCTION
 * @Date	 2015	2015年8月27日		上午8:51:09
 * @see
 */
public class EventButton extends Button {
	
	private String id;

	private String key;
	private String type;
	private String url;
	
	private List<EventButton> sub_button;
	

	public List<EventButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<EventButton> sub_button) {
		this.sub_button = sub_button;
	}

	public void addButton(EventButton subButton){
		if(sub_button==null)
			sub_button = new ArrayList<EventButton>();
		sub_button.add(subButton);
	}
	public EventButton(){
		super();
	}
	
	public EventButton(String name){
		super(name);
	}
	
	public EventButton(String name,String key,String url,String type){
		super(name);
		this.key = key;
		this.url = url;
		this.type = type;
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
