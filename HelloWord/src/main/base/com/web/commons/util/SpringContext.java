package com.web.commons.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringContext {

	public SpringContext() {
	}

	public static Object getBean(String beanId) {
		WebApplicationContext wac = ContextLoader
				.getCurrentWebApplicationContext();
		return wac.getBean(beanId);
	}

}
