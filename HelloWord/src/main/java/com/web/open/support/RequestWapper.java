package com.web.open.support;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.web.commons.util.LogUtil;

/**
 * 
 * ClassName:RequestWapper 微信服务端请求处理 Function: TODO ADD FUNCTION
 *
 * @author JohnYe
 * @version
 * @since
 * @Date 2015 2015年8月26日 下午2:51:04
 *
 * @see
 */
public class RequestWapper {

	
	/**
	 * 
	 * parseXml: (解析微信服务端请求)
	 * @param request
	 * @return  Map<String,String>    
	 * @date    2015年8月26日
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request, String openId) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();

			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();

			List<Element> elementList = root.elements();
			for (Element e : elementList) {
				map.put(e.getName(), e.getText());
			}
			inputStream.close();
			inputStream = null;
		} catch (Exception e) {
			LogUtil.log("微信服务端request请求非法"+e.toString());
		}
//		String projectPath = request.getContextPath();
//		map.put("projectPath", projectPath);
		map.put("openId", openId);
		return map;
	}
	
	
}
