package com.exxk.testdemo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;


/**  
 * <p>Description: 通用工具类</p>  
 * @author xiangqing  
 * @date 2018年3月27日  
 * @version 1.0  
*/
public class CommonUtil {

	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);



	/**
	 * megbox 图片MD5加密
	 */
	public static String imageMD5(File file)  {
		String md5 = CommonUtil.md5EncryptForFile(file);
		byte[] c = hexStringToByteArray(md5);
		String result = CommonUtil.encodeBase64(c)
				.replace("+", "-")
				.replace("/", "_");
//			System.out.println("imageMd5:"+result+" ,长度："+result.length());
		return result;
	}

	/***
	 * string类型 16(hex)进制 转byte 16(hex)进制
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}


    /**
     * 获取文件MD5值
     * @param file
     * @return
     */
    public static String md5EncryptForFile(File file) {
    	String returnVal = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			returnVal = DigestUtils.md5Hex(fis);
		} catch (Exception e) {
			logger.error("md5EncryptForFile for file:" + file.getAbsolutePath() + " error:", e);

		}
		return returnVal;
    }

	/**
	 * base64编码处理
	 * @return
	 */
	public static String encodeBase64(byte[] bytes) {
		if (bytes != null ) {
			Base64 decoder = new Base64();
			return decoder.encodeAsString(bytes);
		}
		return "";

	}

}
