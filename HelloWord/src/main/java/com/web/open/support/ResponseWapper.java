package com.web.open.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.open.menu.Menu;
import com.web.open.msg.Article;
import com.web.open.msg.ImageMessage;
import com.web.open.msg.NewsMessage;
import com.web.open.msg.TextMessage;
import com.web.open.util.WechatConstants;
import com.web.open.util.WechatSender;
import com.web.open.util.WechatUtil;

import net.sf.json.JSONObject;

/**
 * 
 * ClassName:ResponseWapper 所有返回给wechat的数据封装
 * Function: TODO ADD FUNCTION
 * @Date	 2015	2015年8月27日		上午10:32:25
 * @see
 */
public class ResponseWapper {
	
	/**
	 * getMenuResponse: (自定义菜单请求串封装)
	 * @param menu
	 * @return  String    
	 * @date    2015年8月27日
	 */
	public static String getMenuResponse(Menu menu){
		if(menu == null){
			return null;
		}
		return JSONObject.fromObject(menu).toString();
	}
	
	/**
	 * 
	 * getTextResponse: (文本消息返回)
	 * @param requestMap
	 * @param content
	 * @return  String    
	 * @date    2015年8月28日
	 */
	public static String getTextResponse(Map<String ,String> requestMap,String content){
		String fromUserName = requestMap.get("FromUserName");// 发送方帐号（open_id）
		String toUserName = requestMap.get("ToUserName");// 公众帐号
		
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setContent(content);
		System.out.println(textMessage.toString());
		return textMessage.toString();
	}
	public static String getImgResponse(Map<String ,String> requestMap,List<Article> articles){
		String fromUserName = requestMap.get("FromUserName");// 发送方帐号（open_id）
		String toUserName = requestMap.get("ToUserName");// 公众帐号
		String msgType = "news";
		//
		NewsMessage picmsg = new NewsMessage();
		picmsg.setToUserName(fromUserName);
		picmsg.setFromUserName(toUserName);
		picmsg.setCreateTime(System.currentTimeMillis());
		picmsg.setMsgType(msgType);

		picmsg.setArticles(articles);
		picmsg.setArticleCount(articles.size());
		System.out.println(picmsg.toString());
		return picmsg.toString();
	}
	public static String getImageResponse(Map<String ,String> requestMap,String mediaId){
		String fromUserName = requestMap.get("FromUserName");// 发送方帐号（open_id）
		String toUserName = requestMap.get("ToUserName");// 公众帐号
		
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setToUserName(fromUserName);
		imageMessage.setFromUserName(toUserName);
		imageMessage.setCreateTime(System.currentTimeMillis());
		imageMessage.setMsgType("image");
		imageMessage.setPicUrl(mediaId);
		
		System.out.println(imageMessage.toString());
		return imageMessage.toString();
	}
	
	
	public static void sendKFmessage(String wechatId, String openId, String messageType, Map<String, Object> info){
		AccessToken token = WechatUtil.getToken(wechatId);
		
		String sendUrl = WechatConstants.WECHAT_API_KF_SENDMESSAGE_URL;
		sendUrl = sendUrl.replace("ACCESS_TOKEN", token.getToken());
		Map<String, Object> map = new HashMap<String, Object>();
		if ("image".equals(messageType)) {
			map.put("touser", openId);
			map.put("msgtype", messageType);
			map.put(messageType, info);
		}
		
		String postJson = JSONObject.fromObject(map).toString();
		WechatSender.httpRequest(sendUrl, "POST", postJson);
		
	}
}
