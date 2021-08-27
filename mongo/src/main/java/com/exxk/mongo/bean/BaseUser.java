package com.exxk.mongo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;


@Document(collection = "base_user_info")
public class BaseUser implements Serializable {
    private static final long serialVersionUID = -367612951546908830L;
    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("age")
    private Integer age;

    @Indexed
    @Field("_company_ids")
    private List<String> companyIds;

    public List<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
