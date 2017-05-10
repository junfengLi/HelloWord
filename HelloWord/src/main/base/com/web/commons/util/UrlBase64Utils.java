package com.web.commons.util;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.UrlBase64;

/**
 * UrlBase64编码、解码工具类
 * 
 * @author zhengjian
 * @date 2015-8-19 下午5:15:16
 * 
 */
public class UrlBase64Utils {

	/**
	 * Base64编码
	 * 
	 * @author zhengjian
	 * @date 2015-8-19 下午5:15:34
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		byte[] bytes = null;
		try {
			bytes = UrlBase64.encode(str.getBytes("UTF-8"));
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Base64解码
	 * 
	 * @author zhengjian
	 * @date 2015-8-19 下午5:15:38
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		byte[] bytes=null;
		try {
			bytes = UrlBase64.decode(str.getBytes("UTF-8"));
			return new String(bytes,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
//		String str = "module=96150&loginName=mzj01";
//		String encode = encode(str);
//		System.out.println(encode);
//		String decode = decode("eyJyZXN1bHQiOiLoi4_muqrplYc65Y-N6aaI6Zi_6YGT5aSrO-eooOWfjuihl-mBkzrml6DlpITnkIblj43ppojvvIEiLCJwcm9ibGVtTnVtIjoiWVdXMjAxNTExMTkwMDEwIiwiY29tcGxldGVUaW1lIjoxNDQ3OTE4MDM4NjU1LCJldmVudGlkIjoiOGE5MDgyODkzM2IwYzk2YjAxMzNjZjRkZDQ5OTY1ZjAiLCJ2YWxpZGF0ZUtleSI6Inl3Z3JpZDk2MTUwIn0.");
//		System.out.println(decode);
		System.out.println(encode("li110110"));
		System.out.println(encode("Li110110"));
	}
}
