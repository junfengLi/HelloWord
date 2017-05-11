package com.web.open.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.web.commons.util.ConfigUtil;
import com.web.commons.util.LogUtil;
import com.web.commons.util.SpringContext;
import com.web.open.menu.EventButton;
import com.web.open.menu.Menu;
import com.web.open.support.AccessToken;
import com.web.wechat.pojo.Wechat;
import com.web.wechat.service.WechatService;
import com.web.wechat.util.WechatTypeEnum;

public class WechatUtil {

	/**
	 * 返回需要授权的链接地址
	 * @param url
	 * @return
	 */
	public String getOAuthUrl(String url){
//		return Constants.WECHAT_AUTH_API_URL + "?appid=" + Constants.WECHAT_APPID
//			+ "&redirect_uri=" + URLEncoder.encode(url) + "&response_type=code&scope=" 
//			+ Constants.WECHAT_AUTH_SCOPE_BASE + "&state=STATE#wechat_redirect";
		
		return "";
	}
	
	/**
	 * 其他业务查询accesstoken，都需要根据WeChatId获取。
	 * @param wechatId
	 * @return
	 */
	public static AccessToken getToken(String wechatId){
		WechatService wechatService = (WechatService) SpringContext.getBean("wechatService");
		Wechat wechat = wechatService.findByWechatId(wechatId);
		if (wechat != null) {
			//判断token是否已经失效
			long dbExpires = 0l;
			dbExpires = wechat.getExpiresin();
				
			if(dbExpires > System.currentTimeMillis()){
				//token 有效，返回当前数据库里token
				AccessToken token = new AccessToken(wechat.getAccesstoken(), dbExpires);
				return token;
			}else{
				AccessToken accessToken = getToken(wechat.getAppid(), wechat.getAppsecred());
				wechat.setAccesstoken(accessToken.getToken());
				wechat.setExpiresin(accessToken.getExpiresIn());
				wechatService.add(wechat);
				return accessToken;
			}			
		} else {
			return null;
		}
	}
	
	
	/**
	 * 新增编辑时，根据AppId和Appsecred获取类型使用
	 * @param appId
	 * @param appSecred
	 * @return
	 */
	public static AccessToken getToken(String appId, String appSecred){
		String apiUrl = WechatConstants.WECHAT_API_TOKEN_URL.replace("APPID", appId).replace("APPSECRET", appSecred);
		JSONObject jsonObject =WechatSender.httpRequest(apiUrl,"GET",null);
		String error = getWechatError(jsonObject);
		if("0".equals(error)){
			if(!jsonObject.has("expires_in")){
				LogUtil.log("返回expires_in 不存在");
				return null;
			}
			if(!jsonObject.has("access_token")){
				LogUtil.log("返回access_token 不存在");
				return null;
			}
			long newExpiresin = jsonObject.getLong("expires_in");
			String accessToken = jsonObject.getString("access_token");
			
			long expires = System.currentTimeMillis() + newExpiresin*1000;
			
			AccessToken token = new AccessToken(accessToken, expires);
			return token;
		}else{
			LogUtil.log("appId:"+ appId +"****appSecred:" + appSecred + "****返回结果异常："+error);
			return null;
		}
	}
	
	/**
	 * 获取短连接，可判断是否是认证服务号
	 * @param token
	 * @param longUrl
	 * @return
	 */
	public static String getShortUrl(String token, String longUrl){
		String apiUrl = WechatConstants.WECHAT_API_SHORT_URL.replace("ACCESS_TOKEN", token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("long_url", longUrl);
		map.put("action", "long2short");
		String postJson = JSONObject.fromObject(map).toString();
		JSONObject jsonObject =WechatSender.httpRequest(apiUrl,"POST",postJson);
		String error = getWechatError(jsonObject);
		if("0".equals(error)){
			String shorgUrl = jsonObject.getString("short_url");
			return shorgUrl;
		}else{
			LogUtil.log("****返回结果异常："+error);
			return error;
		}
			
	}
	
	/**
	 * 获取短连接，可判断是否是认证订阅号
	 * @param token
	 * @param longUrl
	 * @return
	 */
	public static String getMaterialCount(String token){
		String apiUrl = WechatConstants.WECHAT_API_MATERIAL_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject =WechatSender.httpRequest(apiUrl,"GET",null);
		String error = getWechatError(jsonObject);
		if("0".equals(error)){
			return jsonObject.toString();
		}else{
			LogUtil.log("****返回结果异常："+error);
			return error;
		}
			
	}
	
	/**
	 * 获取短连接，可判断是否是认证订阅号
	 * @param token
	 * @param longUrl
	 * @return
	 */
	public static String getMenu(String token){
		String apiUrl = WechatConstants.WECHAT_API_MENU_GET_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject =WechatSender.httpRequest(apiUrl,"GET",null);
		String error = getWechatError(jsonObject);
		if("0".equals(error)){
			return jsonObject.toString();
		}else{
			LogUtil.log("****返回结果异常："+error);
			return error;
		}
	}
	
	/**
	 * 创建自定义菜单
	 * @param menu
	 * @param token
	 * @return
	 */
	public static String createMenu(Menu menu, String token) {
		if(menu == null){
			LogUtil.log("warring:菜单对象未空");
			return null;
		}
		Map<String, Object> sendMap = new HashMap<String, Object>();
		List<EventButton> eventButtons = menu.getButton();
		List<Map<String, Object>> sendMaps = new ArrayList<Map<String, Object>>();
		for (EventButton eventButton : eventButtons) {
			List<EventButton> subButtons = eventButton.getSub_button();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", eventButton.getName());
			if (CollectionUtils.isNotEmpty(subButtons)) {
				List<Map<String, Object>> sendSubMaps = new ArrayList<Map<String, Object>>();
				for (EventButton subEventButton : subButtons) {
					Map<String, Object> subMap = new HashMap<String, Object>();
					subMap.put("name", subEventButton.getName());
					subMap.put("type", subEventButton.getType());
					if (ConfigUtil.MENU_EVENT_VIEW.equals(subEventButton.getType())) {
						subMap.put("url", subEventButton.getUrl());
					} else {
						subMap.put("key", subEventButton.getKey());
					}
					sendSubMaps.add(subMap);
				}
				map.put("sub_button", sendSubMaps);
			} else {
				map.put("type", eventButton.getType());
				if (ConfigUtil.MENU_EVENT_VIEW.equals(eventButton.getType())) {
					map.put("url", eventButton.getUrl());
				} else {
					map.put("key", eventButton.getKey());
				}
			}
			sendMaps.add(map);
		}
		sendMap.put("button", sendMaps);
		String menuJson = JSONObject.fromObject(sendMap).toString();;
		
		//发送给wechat 并获取返回
		String apiUrl = WechatConstants.WECHAT_API_MENU_CREAT_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject =WechatSender.httpRequest(apiUrl,"POST",menuJson);
		return getWechatError(jsonObject);
	}
	
	/**
	 * 返回是公众号的类型【认证服务号，未认证服务号，认证订阅号，未认证订阅号】
	 * @param token
	 * @return
	 */
	public static String getWechatType(String token){
		String shortUrl = getShortUrl(token, "http://www.baidu.com");
		String materialCount = getMaterialCount(token);
		String menu = getMenu(token);
		if (StringUtils.isNotBlank(shortUrl)&&!shortUrl.matches(".*48001.*") ) {
			return WechatTypeEnum.F_R.getKey();
		} else if (StringUtils.isNotBlank(materialCount)&&!materialCount.matches(".*48001.*") ) {
			return WechatTypeEnum.D_R.getKey();
		} else if (StringUtils.isNotBlank(menu)&&!menu.matches(".*48001.*") ) {
			return WechatTypeEnum.F_W.getKey();
		} else {
			return WechatTypeEnum.D_W.getKey();
		}
	}
	
	
	/**
	 * 返回错误提示
	 * @author Administrator
	 */
	private static String getWechatError(JSONObject jsonObject){
		if (jsonObject.toString().matches(".*errcode.*")) {
			String code = jsonObject.getString("errcode");
			if (WechatErrorEnum.A2.getKey().equals(code)) {
				return WechatErrorEnum.A2.getKey();
			} else {
				String msg = WechatErrorEnum.A1.getDesc();
				if(StringUtils.isNotBlank(code)){
					if (WechatErrorEnum.A2.getKey().equals(code)) {
						msg = WechatErrorEnum.A2.getKey();
					} else {
						msg = WechatErrorEnum.getDescByKey(code);
					}
				}
				if (StringUtils.isBlank(msg)) {
					return WechatErrorEnum.A1.getDesc();
				} else {
					return "返回码：["+code +"]" +msg;	
				}
			}
		} else {
			return WechatErrorEnum.A2.getKey();
		}
	}
	
	
	/**
	 * 上传微信素材图片
	 * @author lijunfeng
	 * 2016年7月26日 上午11:07:50
	 * @param path
	 * @param imgUrl
	 * @param length
	 * @param name
	 * @return
	 */
	public static String updateWechatImage(String imgUrl, String length, String name, String token, String type) {
		String path = WechatConstants.WECHAT_API_UPLOAD_MEDIA;
		path = path.replace("ACCESS_TOKEN", token);
		path = path.replace("TYPE", type);
		String result = null;
		BufferedReader reader = null;
			URL url;
			HttpURLConnection con;
			URL urlin;   
           
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			try {
				urlin = new URL(imgUrl);    
	            HttpURLConnection conn = (HttpURLConnection)urlin.openConnection();    
	            conn.setRequestMethod("GET");    
	            conn.setConnectTimeout(5 * 1000);    
	            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据 
				
				url = new URL(path);	
				
				con = (HttpURLConnection) url.openConnection();
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false); // post方式不能使用缓存
				// 设置请求头信息
				con.setRequestProperty("Connection", "Keep-Alive");
				con.setRequestProperty("Charset", "UTF-8");
				con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
				// 请求正文信息
				// 第一部分：
				StringBuilder sb = new StringBuilder();
				sb.append("--"); // 必须多两道线
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + length + "\";filename=\"" + name
						+ "\"\r\n");
				sb.append("Content-Type:application/octet-stream\r\n\r\n");
				byte[] head = sb.toString().getBytes("utf-8");
				// 获得输出流
				DataInputStream in = new DataInputStream(inStream); 
				OutputStream out = new DataOutputStream(con.getOutputStream());
				// 输出表头
				out.write(head);
				// 文件正文部分
				// 把文件已流文件的方式 推入到url中
				int bytes = 0;
				byte[] bufferOut = new byte[1024];
				while ((bytes = in.read(bufferOut)) != -1) {
					out.write(bufferOut, 0, bytes);
				}
				in.close();
				// 结尾部分
				byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
				out.write(foot);
				out.flush();
				out.close();
				// 定义BufferedReader输入流来读取URL的响应
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
				result = reader.readLine();
				
				if (reader != null) {
					reader.close();
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	
		return result;
	}
	public static void main(String[] args) {
//		String appid="wxdde21708a5b94ba9";
//				String appsecret="d4624c36b6795d1d99dcf0547af5443d";
//		getToken(appid, appsecret);
//		System.out.println(getToken(appid, appsecret).getToken());
		String token = "jIGeCqtXa7iZ1cGoRjrzK53QnnAG376KyThtQq1I_sClXsSjg8UTW2FDB1hkcioaZjg7BF7UGMFtWthHmF7Mdd2OWpb5GTmyhfgql0QQO3TVvQ3ReP6z6rjW_DMUFzRTREPdAHAUHS";
//		System.out.println(getShortUrl(token, "http://www.baidu.com"));
//		System.out.println(getMaterialCount(token));
		
//		String url = "http://wechat2016.oss-cn-hangzhou.aliyuncs.com/public_wechat/ji/wechatImg/20160711204228_855.png";
//		String lenght="9253";
//		String name = "20160711204228_855.png";
//		System.out.println(updateWechatImage(url, lenght, name, token, "image"));
		
	}
}
