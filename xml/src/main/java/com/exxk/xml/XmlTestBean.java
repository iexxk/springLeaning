package com.exxk.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "service")
@Data
public class XmlTestBean {
    @JacksonXmlProperty(isAttribute = true)
    private String version = "2.0";

    @JacksonXmlProperty(localName = "BODY")
    private Body body;

    @Data
    public class Body {

        @JacksonXmlProperty(localName = "TX_CODE")
        private Element txCode;

        @JacksonXmlProperty(localName = "Name")
        private Element name;

    }

    @JacksonXmlProperty(localName = "SYS_HEAD")
    private SysHead sysHead;

    @Data
    public class SysHead {
        @JacksonXmlProperty(localName = "TRAN_TIMESTAMP")
        private Element tranTimestamp;
    }


}
