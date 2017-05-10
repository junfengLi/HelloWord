package com.web.commons.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component  
public class Task {

	 //@Scheduled(cron="0/2 * * * * ? ") //间隔5秒执行  
	    public void taskCycle(){  
	        System.out.println("+++++++++++++++++++++++++++++++++++");  
	    }  
	    @Scheduled(cron="0 0 1 * *  ? ") //间隔5秒执行  
	    public void show(){  
	        System.out.println("+++++++++++++++++++++++++++++++++++");  
	    }  
//	    @Scheduled //间隔5秒执行  
	    public void print(){  
	        System.out.println("+++++++++++++++++++++++++++++++++++");  
	    }  
}
