package com.web.open.util;


public enum WechatErrorEnum {
	A1("-1","系统繁忙，此时请开发者稍候再试"),
	A2("0","请求成功"),
	A3("40001","获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口"),
	A4("40002","不合法的凭证类型"),
	A5("40003","不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID"),
	A6("40004","不合法的媒体文件类型"),
	A7("40005","不合法的文件类型"),
	A8("40006","不合法的文件大小"),
	A9("40007","不合法的媒体文件id"),
	A10("40008","不合法的消息类型"),
	A11("40009","不合法的图片文件大小"),
	A12("40010","不合法的语音文件大小"),
	A13("40011","不合法的视频文件大小"),
	A14("40012","不合法的缩略图文件大小"),
	A15("40013","不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写"),
	A16("40014","不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
	A17("40015","不合法的菜单类型"),
	A18("40016","不合法的按钮个数"),
	A19("40017","不合法的按钮个数"),
	A20("40018","不合法的按钮名字长度"),
	A21("40019","不合法的按钮KEY长度"),
	A22("40020","不合法的按钮URL长度"),
	A23("40021","不合法的菜单版本号"),
	A24("40022","不合法的子菜单级数"),
	A25("40023","不合法的子菜单按钮个数"),
	A26("40024","不合法的子菜单按钮类型"),
	A27("40025","不合法的子菜单按钮名字长度"),
	A28("40026","不合法的子菜单按钮KEY长度"),
	A29("40027","不合法的子菜单按钮URL长度"),
	A30("40028","不合法的自定义菜单使用用户"),
	A31("40029","不合法的oauth_code"),
	A32("40030","不合法的refresh_token"),
	A33("40031","不合法的openid列表"),
	A34("40032","不合法的openid列表长度"),
	A35("40033","不合法的请求字符，不能包含\\uxxxx格式的字符"),
	A36("40035","不合法的参数"),
	A37("40038","不合法的请求格式"),
	A38("40039","不合法的URL长度"),
	A39("40050","不合法的分组id"),
	A40("40051","分组名字不合法"),
	A41("40117","分组名字不合法"),
	A42("40118","media_id大小不合法"),
	A43("40119","button类型错误"),
	A44("40120","button类型错误"),
	A45("40121","不合法的media_id类型"),
	A46("40132","微信号不合法"),
	A47("40137","不支持的图片格式"),
	A48("41001","缺少access_token参数"),
	A49("41002","缺少appid参数"),
	A50("41003","缺少refresh_token参数"),
	A51("41004","缺少secret参数"),
	A52("41005","缺少多媒体文件数据"),
	A53("41006","缺少media_id参数"),
	A54("41007","缺少子菜单数据"),
	A55("41008","缺少oauth code"),
	A56("41009","缺少openid"),
	A57("42001","access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明"),
	A58("42002","refresh_token超时"),
	A59("42003","oauth_code超时"),
	A60("43001","需要GET请求"),
	A61("43002","需要POST请求"),
	A62("43003","需要HTTPS请求"),
	A63("43004","需要接收者关注"),
	A64("43005","需要好友关系"),
	A65("44001","多媒体文件为空"),
	A66("44002","POST的数据包为空"),
	A67("44003","图文消息内容为空"),
	A68("44004","文本消息内容为空"),
	A69("45001","多媒体文件大小超过限制"),
	A70("45002","消息内容超过限制"),
	A71("45003","标题字段超过限制"),
	A72("45004","描述字段超过限制"),
	A73("45005","链接字段超过限制"),
	A74("45006","图片链接字段超过限制"),
	A75("45007","语音播放时间超过限制"),
	A76("45008","图文消息超过限制"),
	A77("45009","接口调用超过限制"),
	A78("45010","创建菜单个数超过限制"),
	A79("45015","回复时间超过限制"),
	A80("45016","系统分组，不允许修改"),
	A81("45017","分组名字过长"),
	A82("45018","分组数量超过上限"),
	A83("46001","不存在媒体数据"),
	A84("46002","不存在的菜单版本"),
	A85("46003","不存在的菜单数据"),
	A86("46004","不存在的用户"),
	A87("47001","解析JSON/XML内容错误"),
	A88("48001","api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限"),
	A89("50001","用户未授权该api"),
	A90("50002","用户受限，可能是违规后接口被封禁"),
	A91("61451","参数错误(invalid parameter)"),
	A92("61452","无效客服账号(invalid kf_account)"),
	A93("61453","客服帐号已存在(kf_account exsited)"),
	A94("61454","客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalid kf_acount length)"),
	A95("61455","客服帐号名包含非法字符(仅允许英文+数字)(illegal character in kf_account)"),
	A96("61456","客服帐号个数超过限制(10个客服账号)(kf_account count exceeded)"),
	A97("61457","无效头像文件类型(invalid file type)"),
	A98("61450","系统错误(system error)"),
	A99("61500","日期格式错误"),
	A100("61501","日期范围错误"),
	A101("9001001","POST数据参数不合法"),
	A102("9001002","远端服务不可用"),
	A103("9001003","Ticket不合法"),
	A104("9001004","获取摇周边用户信息失败"),
	A105("9001005","获取商户信息失败"),
	A106("9001006","获取OpenID失败"),
	A107("9001007","上传文件缺失"),
	A108("9001008","上传素材的文件类型不合法"),
	A109("9001009","上传素材的文件尺寸不合法"),
	A110("9001010","上传失败"),
	A111("9001020","帐号不合法"),
	A112("9001021","已有设备激活率低于50%，不能新增设备"),
	A113("9001022","设备申请数不合法，必须为大于0的数字"),
	A114("9001023","已存在审核中的设备ID申请"),
	A115("9001024","一次查询设备ID数量不能超过50"),
	A116("9001025","设备ID不合法"),
	A117("9001026","页面ID不合法"),
	A118("9001027","页面参数不合法"),
	A119("9001028","一次删除页面ID数量不能超过10"),
	A120("9001029","页面已应用在设备中，请先解除应用关系再删除"),
	A121("9001030","一次查询页面ID数量不能超过50"),
	A122("9001031","时间区间不合法"),
	A123("9001032","保存设备与页面的绑定关系参数错误"),
	A124("9001033","门店ID不合法"),
	A125("9001034","设备备注信息过长"),
	A126("9001035","设备申请参数不合法"),
	A127("9001036","查询起始值begin不合法");

	
	private final String key;
	private final String desc;
	
	
	WechatErrorEnum(String key, String desc){
		this.key = key;
		this.desc = desc;
	}
	
	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}
	

	public static String getDescByKey(String Key) {
		for (WechatErrorEnum type : WechatErrorEnum.values()) {
			if (type.getKey().equals(Key))
				return type.getDesc();
		}
		return "";
	}
	
}
