package com.web.open.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;

import com.web.commons.util.LogUtil;
/**
 * 
 * ClassName:SignUtil 校验工具类
 * @Date	 2015	2015年8月26日		下午2:39:47
 * @see
 */
public class SignUtil {

	/**
	 * 
	 * checkSignature: (校验签名)
	 *
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return    
	 * @return  boolean    
	 * @author  JohnYe 
	 * @date    2015年9月2日
	 */
	public static boolean checkSignature(String signature, String timestamp,
			String nonce) {
		String[] arr = { WechatConstants.GLOBAL_TOKEN, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");

			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		boolean result = tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
		LogUtil.log("(校验签名)完成，结果:" + ( result ? "成功":"失败"));
		return result;
	}

	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest = strDigest + byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4 & 0xF)];
		tempArr[1] = Digit[(mByte & 0xF)];

		String s = new String(tempArr);
		return s;
	}
	
	/**
	 * 
	 * getJsSignature: (获取js认证)
	 *
	 * @param string1
	 * @return    
	 * @return  String    
	 * @author  JohnYe 
	 * @date    2015年9月2日
	 */
	public static String getJsSignature(String string1) {
		String gensignature = null;
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			gensignature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return gensignature;
	}
	
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}