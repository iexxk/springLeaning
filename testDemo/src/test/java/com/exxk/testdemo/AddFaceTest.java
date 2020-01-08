package com.exxk.testdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;

/**
 * <p>Description: </p>  
 * @author xiangqing  
 * @date 2019年8月9日  
 * @version 1.0  
*/

public class AddFaceTest {

	private static final Logger log = LoggerFactory.getLogger("aaa");

	public void createGroupTest() {
		
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		sendMsgHttp("aaaa".getBytes());
	}

	public static byte[] sendMsgHttp(Object paramObj) {
		// 日志-开始处理
		if (log.isInfoEnabled()) {
			log.info("HTTP通讯处理开始。。。");
		}
		// 参数初始化
		byte[] inData = null;
		byte[] outData = null;
		URL url = null;
		URLConnection conn = null;
		// 读取输入的数据
		if ((paramObj instanceof byte[])) {
			inData = (byte[]) paramObj;
		} else {
			if (log.isErrorEnabled()) {
				log.error("数据错误：输入的参数必须是byte[]或CompositeData类型的数据");
			}
			return null;
		}
		OutputStream os = null;
		BufferedInputStream is = null;
		try {
			// 建立连接
			url = new URL("http://127.0.0.1:8080/xmbankaccess/facecompare");
//			url = new URL("http://127.0.0.1:9980/xmbankaccess/facecompare");
			conn = url.openConnection();
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			conn.setDoOutput(true);
			if (log.isInfoEnabled()) {
				log.info("URL连接已打开。。。");
			}
			// 发送请求数据
			os = conn.getOutputStream();
			if (log.isDebugEnabled()) {
				log.debug("向Servlet发送的请求数据为：" + new String(inData, "UTF-8"));
			}
			os.write(inData);
			os.flush();
			// 读取响应数据
			is = new BufferedInputStream(conn.getInputStream());
			int availableSize = 0;
			byte[] buffer = new byte[8192];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((availableSize = is.read(buffer)) != -1) {
				baos.write(buffer, 0, availableSize);
			}
			outData = baos.toByteArray();
			baos.close();
			// 登记响应的数据
			if (log.isInfoEnabled()) {
				log.info("获取servlet响应的长度为：" + outData.length);
			}
			if (log.isDebugEnabled()) {
				log.debug("获取servlet响应的数据为："
						+ new String(outData, "UTF-8").trim());
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("通讯发生异常：", e);
				log.error("请求的HTTPURL：");

				//错误码改造
				// 区别获取错误信息并返回
				if (e instanceof SocketTimeoutException) {
					if (e.getMessage().equals("connect timed out")) {
						log.error("与@建立连接超时");
					} else if (e.getMessage().equals("Socket closed")) {
						log.error("连接被@异常关闭");
					} else {
						log.error("读取@数据超时");
					}
					// else if(e.getMessage().equals("Connection Refused")){
					// throw new ExEsbException(ExEsbException.SYS_CODE +
					// this.errorCode,
					// "链接被拒绝");
					// }
				}
				else if (e instanceof ConnectException) {
					log.error("与@连接被拒绝");
				} else if (e instanceof SocketException) {
					log.error("与@建立连接异常");
				}

				try {
					log.error("请求的数据：" + new String(inData, "UTF-8"));
				} catch (UnsupportedEncodingException e1) {
					log.error(e.getMessage());
				}
			}
		} finally {
			try {
				if (null != os) {
					os.close();
				}
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				if (log.isErrorEnabled())
					log.error(e.getMessage());
			}
		}
		// 判断响应的内容是否为空，空则直接返回
		if (outData == null) {
			outData = "aa".getBytes();
		}
		return outData;
	}

}
