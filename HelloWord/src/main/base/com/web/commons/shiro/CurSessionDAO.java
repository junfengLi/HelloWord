package com.web.commons.shiro;

import java.util.Date;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;


public class CurSessionDAO extends EnterpriseCacheSessionDAO implements SessionListener{

	@Override
	public void delete(Session session) {
		System.out.println(new Date().toString() + "-------------------------------delete-------------------------------"); 
		if (null != session) {
			// 根据sessionid取出登录日志
			String sessionidstr = session.getId().toString();
			if (null != sessionidstr && !"".equals(sessionidstr)) {
				super.delete(session);
			}
		}
	}
	
	@Override 
	public void onStart(Session session) { 
	// TODO Auto-generated method stub 
		System.out.println("-------------------------------onStart-------------------------------"); 
	} 

	@Override 
	public void onStop(Session session) { 
	// TODO Auto-generated method stub 
		System.out.println("----------------------------------onStop-------------------------------");	
	} 
	
	@Override 
	public void onExpiration(Session session) { 
	// TODO 
		System.out.println("-------------------------------onExpiration-------------------------------");	
	} 

}
