package com.web.commons.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;
  
public class ExFreeMarkerView extends FreeMarkerView {  
	public static final String REAL_PATH = "REAL_PATH";
	public static final String HAVE_HTML = "HAVE_HTML";
	public static final String STATIC_HTML = "STATIC_HTML";
     @Override  
    protected void doRender(Map<String, Object> model,  
            HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
        exposeModelAsRequestAttributes(model, request);  
        SimpleHash fmModel = buildTemplateModel(model, request, response);  
        if (logger.isDebugEnabled()) {  
            logger.debug("Rendering FreeMarker template [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");  
        }  
        Locale locale = RequestContextUtils.getLocale(request);  
        /* 
         * 在这里我们默认不生成静态文件,当ModelAndView有指定STATIC_HTML = true时,就会输出HTML文件 
         * 例如：ModelAndView modelAndView = new ModelAndView("htmlTest"); 
         *       modelAndView.addObject("STATICHTML", true);  
         */  
        if(Boolean.TRUE.equals(model.get(STATIC_HTML))){
        	String realPath = (String)model.get(REAL_PATH);   
        	if (Boolean.TRUE.equals(model.get(HAVE_HTML))) {
             	String requestHTML = realPath + ".htm";          
                request.getRequestDispatcher(requestHTML).forward(request, response);            	
     		} else {  
                 createHTML(getTemplate(locale), fmModel, request, response,realPath);  
             }
        } else {  
        	processTemplate(getTemplate(locale), fmModel, response);  
        }  
    }  
       
 	public static boolean hasCreatHtml(HttpServletRequest request, String path) {
 		 String basePath = request.getSession().getServletContext().getRealPath("/");  
 		 String requestHTML = path + ".htm";  
         //静态页面绝对路径  
         String htmlPath = basePath + requestHTML;  
         File htmlFile = new File(htmlPath);  
         if(!htmlFile.getParentFile().exists()){  
             htmlFile.getParentFile().mkdirs();  
         }  
         if(htmlFile.exists()){  
        	 return true;
         }
         return false;
 	}
    public void createHTML(Template template, SimpleHash model,HttpServletRequest request,  
            HttpServletResponse response, String realPath) throws ServletException, IOException {  
            //站点根目录的绝对路径  
            String basePath = request.getSession().getServletContext().getRealPath("/");  
            String requestHTML = realPath + ".htm";  
            //静态页面绝对路径  
            String htmlPath = basePath + requestHTML;  
            File htmlFile = new File(htmlPath);  
            if(!htmlFile.getParentFile().exists()){  
                htmlFile.getParentFile().mkdirs();  
            }  
              
            /** 
             * 如果静态页面已经存在,就不再创建静态页面. 
             */  
            if(!htmlFile.exists()){  
                try {
					htmlFile.createNewFile();
					Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));  
					try {
						template.process(model, out);
					} catch (TemplateException e) {
						e.printStackTrace();
					}  
					out.flush();  
					out.close();  
				} catch (IOException e) {
					e.printStackTrace();
				}  
                //处理模版    
            }  
            /*将请求转发到生成的htm文件*/  
			request.getRequestDispatcher(requestHTML).forward(request, response);
    }  
           
}  