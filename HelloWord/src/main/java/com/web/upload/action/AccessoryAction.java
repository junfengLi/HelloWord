package com.web.upload.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.upload.util.OssUploadUtil;



@Controller
@RequestMapping("/accessory")
public class AccessoryAction {
	
	Logger logger = Logger.getLogger(AccessoryAction.class);
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String uploadPath = "2017company_demo/";
                String module = request.getParameter("module")==null?"editor":request.getParameter("module");
                String maxSizeStr = request.getParameter("maxSize")==null?"4194304":request.getParameter("maxSize");
                String fileType = request.getParameter("fileType")==null?"all":request.getParameter("fileType");
                String flag = request.getParameter("flag")==null?"":request.getParameter("flag");

                //定义允许上传的文件扩展名
                HashMap<String, String> extMap = new HashMap<String, String>();
                extMap.put("image", "gif,jpg,jpeg,png,bmp");
                extMap.put("flash", "swf,flv");
                extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
                extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
                extMap.put("pda", "apk");

                //最大文件大小
                long maxSize = Long.valueOf(maxSizeStr);

                response.setContentType("text/html; charset=utf-8");

                if(!ServletFileUpload.isMultipartContent(request)){
                	logger.info("未选择文件");
                	result.put("message", "未选择文件");
                	return result;
                }

                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setHeaderEncoding("utf-8");
                List items = upload.parseRequest(request);
                Iterator itr = items.iterator();
                while (itr.hasNext()) {
                	FileItem item = (FileItem) itr.next();
                	String fileName = item.getName();
                	long fileSize = item.getSize();
                	if (!item.isFormField()) {
                		//检查文件大小
                		if(item.getSize() > maxSize){
                			logger.info("上传文件大小超过"+ Math.floor(maxSize/(1024*1024)) +"M限制。");
                			result.put("message", "上传文件大小超过"+ Math.floor(maxSize/(1024*1024)) +"M限制。");
                			return result;
                		}
                		//检查扩展名
                		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                		if("all".equals(fileType)==false){
                			if(!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)){
                				logger.info("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
                				result.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
                				return result;
                			}
                		}

                		//生成文件名并写入文件
                		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) ;
                		String url = "";
                		try{
                			InputStream inputStream = item.getInputStream();
//                			Map<String, Object> map = BceUploadUtil.putInputStreamToObject(inputStream, uploadPath + module +"/"+ newFileName+ "." + fileExt);
                			Map<String, Object> map = OssUploadUtil.putInputStreamToObject(inputStream, uploadPath + module +"/"+ newFileName+ "." + fileExt);
                			url = (String)map.get("backUrl");
                		}catch(Exception e){
                			logger.info("上传文件失败。");
                			result.put("message", "上传文件失败.");
                			return result;
                		}
                		
                		//TODO 保存

                		result.put("error", 0);
                		result.put("url", url);
                		result.put("name", fileName);
                		result.put("module", module);
                		result.put("fileSize", fileSize);
                		result.put("miniType", fileExt);
                		return result;
                	}
                }
        } catch (Exception e) {
        	logger.info("上传失败！");
        }
        return null;
    }
	
	@RequestMapping({ "/delete" })
	@ResponseBody
	public Map<String, Object> delete(@RequestParam("accessoryId") String accessoryId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			//TODO 删除
			result.put("success", Boolean.valueOf(true));
			result.put("message", "删除成功。");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", Boolean.valueOf(false));
			result.put("message", "删除失败。");
		}
		return result;
	}
}
