package com.exxk.testdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test")
public class TestControl {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/glob")
    public String glob() {
        String userNmae = messageSource.getMessage("user.name", null, LocaleContextHolder.getLocale());
        logger.info("glob :" + userNmae);
        return userNmae;
    }

}
