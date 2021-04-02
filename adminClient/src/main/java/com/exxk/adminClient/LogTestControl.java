package com.exxk.adminClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log")
public class LogTestControl {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /***
     * @return
     */
    @RequestMapping(value = "/test")
    public void test() {
        logger.info("test info");
        logger.warn("test warn");
        logger.debug("test debug");
        logger.error("test error");
    }
}
