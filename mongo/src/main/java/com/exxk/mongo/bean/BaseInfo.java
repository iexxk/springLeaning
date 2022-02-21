package com.exxk.mongo.bean;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @ClassName BaseInfo
 * @Description 基础信息类
 * @Author dajie
 * @Date 2021/7/1 3:51 下午
 */

@Data
public class BaseInfo {

    @Field("_create_time")
    private Date createTime;

    @Field("_create_author")
    private String createAuthor;

    @Field("_update_time")
    private Date updateTime;

    @Field("_update_author")
    private String updateAuthor;

}
