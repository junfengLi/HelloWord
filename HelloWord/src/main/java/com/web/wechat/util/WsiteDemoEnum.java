package com.web.wechat.util;

import java.util.ArrayList;
import java.util.List;

import com.web.commons.jqgrid.UINode;



public enum WsiteDemoEnum {
	
	INDEX("index", "首页模板风格", "menu"),
	INDEX_001	("index_001", "	模板1", "index"),	
	INDEX_002	("index_002", "	模板2", "index"),	
	INDEX_003	("index_003", "	模板3", "index"),	
	INDEX_004	("index_004", "	模板4", "index"),	
	INDEX_005	("index_005", "	模板5", "index"),	
	INDEX_006	("index_006", "	模板6", "index"),	
	INDEX_007	("index_007", "	模板7", "index"),	
	INDEX_008	("index_008", "	模板8", "index"),	
	INDEX_009	("index_009", "	模板9", "index"),	
	INDEX_010	("index_010", "	模板10", "index"),	
	INDEX_011	("index_011", "	模板11", "index"),	
	INDEX_012	("index_012", "	模板12", "index"),	
	INDEX_013	("index_013", "	模板13", "index"),	
	INDEX_014	("index_014", "	模板14", "index"),	
	INDEX_015	("index_015", "	模板15", "index"),	
	INDEX_016	("index_016", "	模板16", "index"),	
	INDEX_017	("index_017", "	模板17", "index"),	
	INDEX_018	("index_018", "	模板18", "index"),	
	INDEX_019	("index_019", "	模板19", "index"),	
	INDEX_020	("index_020", "	模板20", "index"),	

	LIST("list", "列表页模板风格", "menu"),
	LIST_001("list_001", "模板一", "list"),
	LIST_002("list_002", "模板二", "list"),
	LIST_003("list_003", "模板三", "list"),
	LIST_004("list_004", "模板四", "list"),
	LIST_005("list_005", "模板五", "list"),
	CONTENT("content", "详情页模板风格", "menu"),
	CON_001("con_001", "模板一", "content"),
	CON_002("con_002", "模板二", "content"),
	CON_003("con_003", "模板三", "content");
	
	
	private final String key;
	private final String desc;
	private final String seq;
	
	public final static String MENU = "menu";
	
	WsiteDemoEnum(String key, String desc, String seq){
		this.key = key;
		this.desc = desc;
		this.seq = seq;
	}
	
	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}
	
	public String getSeq() {
		return seq;
	}

	public static String getDescByKey(String Key) {
		for (WsiteDemoEnum type : WsiteDemoEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}

	public static List<UINode> getUINodesBySeq(String seq) {
		List<UINode> easyUINodes = new ArrayList<UINode>();
		for (WsiteDemoEnum type : WsiteDemoEnum.values()) {
			if (type.getSeq().equals(seq)){
				UINode easyUINode = new UINode(type.getKey(), type.getDesc());
				easyUINode.addAttributes("demoType", seq);
				easyUINodes.add(easyUINode);
				
			}
		}
		return easyUINodes;
	}
}
