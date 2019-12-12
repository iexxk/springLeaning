package com.exxk.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

@Configuration
public class Config {


    /**
     * jackson 转xml 全局配置
     *
     * @param builder
     * @return
     */
    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter(
            Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(true).build();
//        设置全局返回显示 <?xml version='1.0' encoding='UTF-8'?>
        ((XmlMapper) mapper).enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        //<?xml version='1.0' encoding='UTF-8'?> 改为双引号 <?xml version="1.0" encoding="UTF-8"?>
        String propName = com.ctc.wstx.api.WstxOutputProperties.P_USE_DOUBLE_QUOTES_IN_XML_DECL;
        ((XmlMapper) mapper).getFactory()
                .getXMLOutputFactory()
                .setProperty(propName, true);
        return new MappingJackson2XmlHttpMessageConverter(mapper);
    }

}
