package com.exxk.kafka;

import lombok.Data;

import java.util.Date;

/***
 * 消息实体类
 */
@Data
public class Message {
    /**
     * id
     */
    private Long id;    //id

    /**
     * 消息
     */
    private String msg;
    /**
     * 时间戳
     */
    private Date sendTime;

}