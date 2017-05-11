package com.web.open.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.commons.util.ConfigUtil;
import com.web.commons.util.LogUtil;
import com.web.commons.util.UrlBase64Utils;
import com.web.open.msg.Article;
import com.web.open.service.ServerHandler;
import com.web.open.support.JsApiToken;
import com.web.open.support.RequestWapper;
import com.web.open.support.ResponseWapper;
import com.web.open.util.WechatConstants;
import com.web.wechat.pojo.Keyword;
import com.web.wechat.pojo.MessageImage;
import com.web.wechat.pojo.MessageImg;
import com.web.wechat.pojo.MessageText;
import com.web.wechat.service.KeywordService;
import com.web.wechat.util.MessageTypeEnum;
import com.web.wechat.util.ServiceTypeEnum;

import net.sf.json.JSONObject;

/**
 * ClassName:ServerHandlerImpl
 * @Date	 2015	2015年8月26日		下午2:58:22
 */
@Component("serverHandler")
public class ServerHandlerImpl implements ServerHandler {

	@Autowired
	private KeywordService keywordService;

	@Override
	public String handleRequest(HttpServletRequest request, String openId) {
		Map<String, String> requestMap = RequestWapper.parseXml(request, openId);
		String wechatId = requestMap.get("ToUserName");// 公众帐号
		if(requestMap==null || requestMap.isEmpty())
			return null;
		
		String msgType = requestMap.get("MsgType");
		if(StringUtils.isBlank(msgType)){
			return null;
		}
		
		String returnInfo = "";
		if(!WechatConstants.REQ_MSG_TYPE_EVENT.equalsIgnoreCase(msgType) ){
			//普通消息处理
			returnInfo = msgHandler(requestMap);
		}else{
			//事件消息处理
			String eventType = requestMap.get("Event");
			if(StringUtils.isBlank(eventType)){
				return null;
			}
			
			if (WechatConstants.REQ_MSG_TYPE_EVENT_SUBSCRIBE.equalsIgnoreCase(eventType) ) { // 订阅
				String keyword = "";
				List<Keyword> keywords = keywordService.findByKeywordType(ServiceTypeEnum.SUBSCRIBE.getKey(), wechatId);
				if (CollectionUtils.isNotEmpty(keywords)) {
					keyword = keywords.get(0).getMessagetype();
				}
				returnInfo = keywordBusiness(requestMap,keyword);
			} else if (WechatConstants.REQ_MSG_TYPE_EVENT_UNSUBSCRIBE.equalsIgnoreCase(eventType)) { // 取消订阅
				// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
			} else if (WechatConstants.REQ_MSG_TYPE_EVENT_CLICK.equalsIgnoreCase(eventType)) { // 自定义菜单点击事件
//				returnInfo = eventHandler(requestMap);	
				returnInfo = msgHandler(requestMap);	
			}
		}
		return returnInfo;
	}



	@Override
	public String getWechatUser(String openId) {
//		String accessTokenURL = "/user/info?access_token=";
//				+getToken().getToken()+"&openid="+ openId+"&lang=zh_CN";
		String resonStr = "";
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpPost httppost = new HttpPost(accessTokenURL);
//		CloseableHttpResponse response;
//		try {
//			response = httpclient.execute(httppost);
//			HttpEntity entity = response.getEntity();
//			if (entity != null) {
//				resonStr = EntityUtils.toString(entity, "UTF-8");
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {  
//			e.printStackTrace();
//		}
		JSONObject jsonObject = JSONObject.fromObject(resonStr);
		return jsonObject.toString();
	}
	
//	@Override
//	public String eventHandler(Map<String, String> requestMap) {
//		// TODO Auto-generated method stub
//		String msgType = requestMap.get("EventKey");
//		String respMessage = ResponseWapper.getTextResponse(requestMap,"谢谢你的关注，业务还在建设中...");
//		if(StringUtils.isBlank(msgType)){
//			LogUtil.log("微信消息传递出错，EventKey未空");
//			return null;
//		}
//		
//		//TODO 根据关键字查询，获取数据库数据
////		respMessage = keywordBusiness(requestMap,msgType,"event");
//		return respMessage;
//	}

	@Override
	public String msgHandler(Map<String, String> requestMap) {
		String msgType = requestMap.get("MsgType");
		String respMessage = "";
		String serviceType = "";
		if(StringUtils.isBlank(msgType)){
			LogUtil.log("微信消息传递出错，MsgType为空");
			return null;
		}
		
		String keyword = requestMap.get("Content");
		String wechatId = requestMap.get("ToUserName");// 公众帐号
		if (WechatConstants.REQ_MSG_TYPE_TEXT.equals(msgType)) { // 文本消息
			//respMessage = keywordBusiness(requestMap,keyword);
		} else if (WechatConstants.REQ_MSG_TYPE_IMAGE.equals(msgType)) { // 图片消息
			serviceType = ServiceTypeEnum.IMAGE.getKey();
		} else if (WechatConstants.REQ_MSG_TYPE_LOCATION.equals(msgType)) { // 地理位置消息
			serviceType = ServiceTypeEnum.LOCATION.getKey();
		} else if (WechatConstants.REQ_MSG_TYPE_LINK.equals(msgType)) { // 链接消息
			serviceType = ServiceTypeEnum.LINK.getKey();
		} else if (WechatConstants.REQ_MSG_TYPE_VOICE.equals(msgType)) { // 音频消息
			serviceType = ServiceTypeEnum.VOICE.getKey();
		} else if (WechatConstants.REQ_MSG_TYPE_VIDEO.equals(msgType)) { // 视频消息
			serviceType = ServiceTypeEnum.VIDEO.getKey();
		} else{
			serviceType = ServiceTypeEnum.DEFAULT.getKey();
		}
		if (StringUtils.isNotBlank(serviceType)) {
			List<Keyword> keywords = keywordService.findByKeywordType(serviceType, wechatId);
			if (CollectionUtils.isNotEmpty(keywords)) {
				keyword = keywords.get(0).getMessagetype();
			}
		}
		respMessage = keywordBusiness(requestMap,keyword);
		return respMessage;
	}

	/**
	 * 
	 * keywordBusiness: (根据关键字处理返回信息)
	 *
	 * @param requestMap
	 */
	@SuppressWarnings("unchecked")
	private String keywordBusiness(Map<String,String> requestMap,String keyword){
		String wechatId = requestMap.get("ToUserName");// 公众帐号
		String openId = requestMap.get("FromUserName");// 公众帐号
		String respMessage = "";
			//TODO 根据 keyword 和flag获取自动回复表数据，获取关键字的列表，进行处理
			Map<String, Object> messageMap = new HashMap<String, Object>();
			messageMap = keywordService.findByKeyword(requestMap, keyword, wechatId);
			if (MessageTypeEnum.TEXT.getKey().equals(messageMap.get("Messagetype"))) {
				MessageText messageText = (MessageText)messageMap.get("object");
				respMessage = ResponseWapper.getTextResponse(requestMap,messageText.getContent());
			} else if (MessageTypeEnum.IMG.getKey().equals(messageMap.get("Messagetype"))) {
				List<MessageImg> messageImgs = (List<MessageImg>)messageMap.get("object");
				List<Article> articles = new ArrayList<Article>();
				for (MessageImg messageImg : messageImgs) {
					Article article = new Article();
					article.setTitle(messageImg.getTitle());
					if (StringUtils.isNotBlank(messageImg.getImage())) {
						if (messageImg.getImage().contains("http:")) {
							article.setPicUrl(messageImg.getImage());
						} 
					}
					if (StringUtils.isNotBlank(messageImg.getDescription())) {
						article.setDescription(messageImg.getDescription());
					}
					
					String setUrl = "";
					if(StringUtils.isNotBlank(messageImg.getSeturl())){
						setUrl = messageImg.getSeturl() ;
					} else {
						setUrl = ConfigUtil.BASE_PATH_WECHAT_SHOW + "/"
								+ UrlBase64Utils.encode(wechatId) + "/" 
								+ messageImg.getId();
					}
					article.setUrl(setUrl);
					articles.add(article);
				}
				respMessage = ResponseWapper.getImgResponse(requestMap, articles);
			} else if (MessageTypeEnum.IMAGE.getKey().equals(messageMap.get("Messagetype"))) {
				List<MessageImage> messageImages = (List<MessageImage>)messageMap.get("object");
				if (CollectionUtils.isNotEmpty(messageImages)) {
					if (messageImages.size() == 1) {
						respMessage = ResponseWapper.getImageResponse(requestMap, messageImages.get(0).getMediaid());
					} else {
						for (int i=0; i < messageImages.size(); i++) {
							MessageImage messageImage = messageImages.get(i);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("media_id", messageImage.getMediaid());
							ResponseWapper.sendKFmessage(wechatId, openId, "image", map);
						}
					}
				}
			}
		return respMessage;
	}

	@Override
	public JsApiToken getJsToken(String url) {
		//获取数据库里的token
//		getToken();
		
//		WechatApp wechatApp = wechatAppService.findByToken(Constants.GLOBAL_TOKEN);
//		String js_ticket = null;
//		if(wechatApp!=null){
//			js_ticket = wechatApp.getJsapiTicketToken();
//		}else{
//			LogUtil.log("获取js token 失败");
//			return null;
//		}
//		
//		if(js_ticket==null){
//			LogUtil.log("js token 为空，重新获取");
//			js_ticket = getJsApiToken(wechatApp);
//		}else{
//			//判断token是否已经失效
//			long expiresInterval=Long.parseLong(Constants.WECHAT_TOKEN_EXPIRES_INTERVAL);
//			long jsExpires = wechatApp.getExpiresInJsapi();
//			if(jsExpires-expiresInterval>System.currentTimeMillis()){
//				js_ticket=wechatApp.getJsapiTicketToken();
//			}else{
//				LogUtil.log("js token 超时，重新获取");
//				js_ticket = getJsApiToken(wechatApp);
//			}
//		}
//		
		JsApiToken jsToken = new JsApiToken();
//		jsToken.setAppId(wechatApp.getAppId());
//		jsToken.setExpiresInJsapi(wechatApp.getExpiresInJsapi());
//		jsToken.setJsapiTicketToken(js_ticket);
//		
//		String noncestr = UUID.randomUUID().toString();
//		jsToken.setNoncestr(noncestr);
//		String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//		jsToken.setTimestamp(timestamp);
//		
//		String signatureStr = "jsapi_ticket=" + js_ticket + "&noncestr="
//				+ noncestr + "&timestamp=" + timestamp + "&url=" + url;
//
//		String signature = SignUtil.getJsSignature(signatureStr);
//		jsToken.setSignature(signature);
		
		return jsToken;
	}
	
	
	@Override
	public String getOpenIdByCode(String code) {
//		String getUrl = Constants.WECHAT_AUTH_TOKEN_API_URL + "?appid="+Constants.WECHAT_APPID
//				+"&secret="+Constants.WECHAT_APPSECRET
//				+"&code="+code+"&grant_type=authorization_code";
//		JSONObject jsonObject =WechatSender.httpRequest(getUrl,"GET",null);
//		LogUtil.log("openId jsonobject : "+jsonObject.toString());
//		if(jsonObject!=null){
//			if(jsonObject.has("openid")){
//				return jsonObject.getString("openid");
//			}
//		}
		return null;
	}

}
