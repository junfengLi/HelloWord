package com.web.open.action;  
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.commons.util.LogUtil;
import com.web.open.service.ServerHandler;
import com.web.open.util.SignUtil;

@Controller
public class WechatInterface {  
	@Autowired
	private ServerHandler serverHandler;
	
	@RequestMapping(value = "/wechatInterface/{openId}", method = RequestMethod.GET)  
	public void doGet(@PathVariable("openId") String openId,HttpServletRequest request,HttpServletResponse response) {  
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		if(StringUtils.isBlank(signature)){
			LogUtil.log("微信绑定校验参数signature非法");
			return;
		}
		if(StringUtils.isBlank(timestamp)){
			LogUtil.log("微信绑定校验参数timestamp非法");
			return;
		}
		if(StringUtils.isBlank(nonce)){
			LogUtil.log("微信绑定校验参数nonce非法");
			return;
		}
		if(StringUtils.isBlank(echostr)){
			LogUtil.log("微信绑定校验参数echostr非法");
			return;
		}

		PrintWriter out;
		try {
			out = response.getWriter();
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		out = null;
	}  
	
	
    @RequestMapping(value = "/wechatInterface/{openId}", method = RequestMethod.POST)  
    public void doPost(@PathVariable("openId") String openId,HttpServletRequest request,HttpServletResponse response) {  
    	try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		String respMessage = serverHandler.handleRequest(request, openId);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(respMessage);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = null;
    }  
}  
