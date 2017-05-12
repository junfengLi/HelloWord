package com.web.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringContext {
//	implements ApplicationContextAware {

	
//	private static ApplicationContext applicationContext;

//	public static Object getBean(String beanName) {
//		return applicationContext.getBean(beanName);
//	}

//	public static <T> T getBean(String beanName, Class<T> clazz) {
//		return clazz.cast(getBean(beanName));
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
//		SpringContext.applicationContext = arg0;
//	}

	
//	public SpringContext() {
//	}
//
	public static Object getBean(String beanId) {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		return wac.getBean(beanId);
	}

}
