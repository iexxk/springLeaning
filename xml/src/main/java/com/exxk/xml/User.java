package com.exxk.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;


@JacksonXmlRootElement
@Data
public class User {
    @JacksonXmlProperty
    private String name;
    @JacksonXmlProperty
    private int age;

    private Score score;

}
