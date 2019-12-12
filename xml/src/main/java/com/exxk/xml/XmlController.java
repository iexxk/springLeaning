package com.exxk.xml;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/xml")
public class XmlController {
    private Logger logger=Logger.getLogger(String.valueOf(getClass()));

    @RequestMapping(value = "/test",consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User test(User user){
        logger.info("receive data: "+user.toString());
        user.setAge(11);
        return user;
    }
}
