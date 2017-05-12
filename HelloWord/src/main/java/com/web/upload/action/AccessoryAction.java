package com.web.upload.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.upload.pojo.Accessory;
import com.web.upload.service.AccessoryService;
import com.web.upload.util.OssUploadUtil;

import sun.misc.BASE64Decoder;



@Controller
@RequestMapping("/accessory")
public class AccessoryAction {
	@Autowired
	private AccessoryService accessoryService;
	
	Logger logger = Logger.getLogger(AccessoryAction.class);
	
	@RequestMapping(value="/editorUpload", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> editorUpload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String uploadPath = "2017wechat/";
                String module = request.getParameter("module")==null?"editor":request.getParameter("module");
//                String maxSizeStr = request.getParameter("maxSize")==null?"4194304":request.getParameter("maxSize");
                String fileType = "image";//request.getParameter("fileType")==null?"all":request.getParameter("fileType");
                String dataUrl = request.getParameter("dataUrl")==null?"":request.getParameter("dataUrl");
                
                
                //定义允许上传的文件扩展名
                HashMap<String, String> extMap = new HashMap<String, String>();
                extMap.put("image", "gif,jpg,jpeg,png,bmp");
                extMap.put("flash", "swf,flv");
                extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
                extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
                extMap.put("pda", "apk");

                //最大文件大小
//                long maxSize = Long.valueOf(maxSizeStr);

                BASE64Decoder decoder = new BASE64Decoder();  
                //Base64解码  
                byte[] b = decoder.decodeBuffer(dataUrl);  
                InputStream inputStream = new ByteArrayInputStream(b); 
//                File item = File.createTempFile("pattern", ".suffix");
//                item.deleteOnExit();
//        		OutputStream os = new FileOutputStream(item);
//                int bytesRead = 0;
//                byte[] buffer = new byte[8192];
//                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//                }
//                os.close();
//                inputStream.close();
                String fileExt = dataUrl.substring(dataUrl.indexOf("/") + 1, dataUrl.indexOf(";"));
//                	String fileName = item.getName();
//                	long fileSize = item.getTotalSpace();
//                		//检查文件大小
//                		if(fileSize > maxSize){
//                			logger.info("上传文件大小超过"+ Math.floor(maxSize/(1024*1024)) +"M限制。");
//                			result.put("message", "上传文件大小超过"+ Math.floor(maxSize/(1024*1024)) +"M限制。");
//                			return result;
//                		}
                		//检查扩展名
//                		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                			if(!Arrays.<String>asList(extMap.get(fileType).split(",")).contains(fileExt)){
                				logger.info("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
                				result.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(fileType) + "格式。");
                				return result;
                			}

                		//生成文件名并写入文件
                		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) ;
                		String url = "";
                		try{
//                			Map<String, Object> map = BceUploadUtil.putInputStreamToObject(inputStream, uploadPath + module +"/"+ newFileName+ "." + fileExt);
                			Map<String, Object> map = OssUploadUtil.putInputStreamToObject(inputStream, uploadPath + module +"/"+ newFileName+ "." + fileExt);
                			url = (String)map.get("backUrl");
                			inputStream.close();
                		}catch(Exception e){
                			logger.info("上传文件失败。");
                			result.put("message", "上传文件失败.");
                			return result;
                		}
                		Accessory accessory =new Accessory();
                		accessory.setCreatetime(System.currentTimeMillis());
//                		accessory.setFilesize(String.valueOf(fileSize));
                		accessory.setMinitype(fileType);
                		accessory.setModule(module);
                		accessory.setName(newFileName);
                		accessory.setUrl(url);
                		
                		accessoryService.add(accessory);
//                		String url = "redirect:/myproject/static/kindeditor/redirect.html?s=" + obj.toJSONString() + "#" + obj.toJSONString();
//                        logger.info(String.format("上传成功文件路径 :%s", url));
                		result.put("id", accessory.getId());
                		result.put("error", 0);
                		result.put("url", url);
                		result.put("name", newFileName);
                		result.put("module", module);
//                		result.put("fileSize", fileSize);
                		result.put("miniType", fileExt);
                		
                		return result;
        } catch (Exception e) {
        	logger.info("上传失败！");
        }
        return null;
    }
	
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	@ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String uploadPath = "2017wechat/";
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
                		Accessory accessory =new Accessory();
                		accessory.setCreatetime(System.currentTimeMillis());
                		accessory.setFilesize(String.valueOf(fileSize));
                		accessory.setMinitype(fileType);
                		accessory.setModule(module);
                		accessory.setName(fileName);
                		accessory.setUrl(url);
                		
                		accessoryService.add(accessory);
//                		String url = "redirect:/myproject/static/kindeditor/redirect.html?s=" + obj.toJSONString() + "#" + obj.toJSONString();
//                        logger.info(String.format("上传成功文件路径 :%s", url));
                		result.put("id", accessory.getId());
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
