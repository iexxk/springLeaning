package com.exxk.xml;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/xml")
public class XmlController {
    private Logger logger=Logger.getLogger(String.valueOf(getClass()));

    /***
     * consumes为请求参数的格式 Content-Type设置为application/xml
     * produces为返回内容的格式 Content-Type设置为application/xml
     * @param xmlTestBean
     * @return
     */
    @RequestMapping(value = "/test",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.TEXT_XML_VALUE)
    @ResponseBody
    public XmlTestBean test(@RequestBody XmlTestBean xmlTestBean){
        logger.info("receive data: "+xmlTestBean.toString());
        xmlTestBean.setVersion(xmlTestBean.getVersion()+"0");
        XmlTestBean.Body body= xmlTestBean.getBody();
        Element name =body.getName();
        name.setAttr(name.getAttr()+"1");
        name.setValue(name.getValue()+"2");
        body.setName(name);
        body.setTxCode(new Element(body.getTxCode().getAttr()+"3",body.getTxCode().getValue()+"4"));
        xmlTestBean.setBody(body);
        return xmlTestBean;
    }
}
