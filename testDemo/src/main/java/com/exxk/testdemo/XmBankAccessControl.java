package com.exxk.testdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/xmbankaccess")
public class XmBankAccessControl {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * @return
     */
    @RequestMapping(value = "/facecompare")
//    @ResponseBody
    public void facecompare(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
        logger.info("begin");
        byte[] reqByte = readReqData(req);
        String str = new String(reqByte);
        logger.info(str);
    }


    /**
     * ?功能：读取请求报文
     *
     * @param request
     * @return 返回请求报文
     * @throws IOException
     */
    private byte[] readReqData(HttpServletRequest request) throws IOException {

        BufferedInputStream bis = null;
        byte[] reqBuff = null;
        try {
            bis = new BufferedInputStream(request.getInputStream());
            byte[] buff = new byte[1024];
            int len = 0;
            int count = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = bis.read(buff, 0, buff.length)) != -1) {
                baos.write(buff, 0, len);
                count += len;
            }
            baos.close();
            reqBuff = new byte[count];
            System.arraycopy(baos.toByteArray(), 0, reqBuff, 0, count);

        } catch (IOException e) {
            logger.error("读请求信息异常：", e);
        } finally {
            if (bis != null) {
                bis.close();
                bis = null;
            }
        }
        return reqBuff;
    }


}
