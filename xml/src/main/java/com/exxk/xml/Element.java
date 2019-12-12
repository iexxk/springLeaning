package com.exxk.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;

@Data
public class Element {
    @JacksonXmlProperty(isAttribute = true)
    private String attr="q";

    @JacksonXmlText
    private String value;

    public Element() {
    }

    public Element(String attr) {
        this.attr = attr;
    }

    public Element(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }
}
