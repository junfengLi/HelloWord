package com.web.commons.jqgrid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UINode implements Serializable {
	public static String ICON_FOLDER_OPEN = "icon-folder-open";
	private static final long serialVersionUID = 6606102985677391647L;
	private String id;
	private String text;
	private String state;
	private String checked;
	private String iconCls;
	private String selected;
	private HashMap<String, Object> attributes = null;
	private List<UINode> children = null;

	public UINode() {
	}

	public UINode(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public UINode(String id, String text, String state) {
		this.id = id;
		this.text = text;
		this.state = this.getState(state);
	}

	private String getState(String state) {
		if (state.equals("closed")) {
			return "closed";
		} else if (state.equals("open")) {
			this.iconCls = "icon-folder-open";
			return "open";
		} else {
			return "open";
		}
	}

	public UINode(String id, String text, String state, String href, String target) {
		this.id = id;
		this.text = text;
		this.state = this.getState(state);
		HashMap<String, Object> attributes = new HashMap<>();
		attributes.put("action", href);
		attributes.put("target", target);
		this.attributes = attributes;
	}

	public UINode(String id, String text, String state, HashMap<String, Object> attributes) {
		this.id = id;
		this.text = text;
		this.state = state;
		this.attributes = attributes;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getChecked() {
		return this.checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public HashMap<String, Object> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(HashMap<String, Object> attributes) {
		this.attributes = attributes;
	}

	public List<UINode> getChildren() {
		return this.children;
	}

	public void setChildren(List<UINode> children) {
		this.children = children;
	}

	public String getIconCls() {
		return this.iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getSelected() {
		return this.selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public void addChildNode(UINode node) {
		if (this.children == null) {
			
			this.children = new ArrayList<>();
		}

		this.children.add(node);
	}

	public void addAttributes(String key, String value) {
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
		}
		this.attributes.put(key, value);
	}

	public void removeChildNode(UINode node) {
		this.removeChildNode(node.getId());
	}

	public void removeChildNode(String nodeId) {
		if (this.children != null) {
			for (int i = 0; i < this.children.size(); ++i) {
				UINode child = (UINode) this.children.get(i);
				if (nodeId.equals(child.getId())) {
					this.children.remove(i);
					break;
				}
			}
		}

	}
}