package com.web.upload.util;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.aliyun.oss.OSSClient;

public class OssUploadUtil {
	
	private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
	private static String accessKeyId = "LTAItMMSgMJlRSv0";
	private static String accessKeySecret = "nmo60maXqGmqK1p75dTJL1XSpdEpex";
	private static String bucketName = "2017demo";
	
	public static void main(String[] args) {
//		System.out.println(generatePresignedUrl("ceshi/ceshi2.jpg"));
//		File file = new File("E://Desert.png");
//		InputStream inputStream;
//		try {
//			inputStream = new FileInputStream(file);
//			putInputStreamToObject(inputStream,"7400597962/ceshi2.png");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		creatBucket(bucketName);
		//创建bucket
//		listObjects();
//		System.out.println(getNameByPath("001"));
//		deleteObject("001/002/003/");
		//deleteObjectByFolder("001/");
		
    }


	
	
	public static Map<String, Object> putInputStreamToObject(InputStream inputStream, String objectKey){
		Map<String, Object> map = new HashMap<String, Object>();
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.putObject(bucketName, objectKey, inputStream);
		map.put("backUrl", getObjectUrl(objectKey));
		client.shutdown();
		return map;
	}
	
	private static String getObjectUrl(String objectKey){
		return "http://" + bucketName +"." + endpoint +"/" + objectKey;
	}
	
	
	
	// 获取Bucket的存在信息
	public static boolean creatBucket(String bucketName){
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		boolean exists = client.doesBucketExist(bucketName);
		//如果没有则创建，如果有，则跳过
		if (!exists) {
			client.createBucket(bucketName);
		}
		client.shutdown();
		return true;
	}
	

}