package com.exxk.mongo.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName BaseCompany
 * @Description
 * @Author dajie
 * @Date 2021/5/12 5:40 下午
 * @Version V1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseCompany extends BaseInfo {

    @Field(value = "_org_info", targetType = FieldType.OBJECT_ID)
    private String orgInfo;

    @Field("_org_path")
    private String orgPath;

    @Field("_org_name")
    private String orgName;

    @Indexed
    @Field("_company_ids")
    private List<String> companyIds;

    @Transient
    private String queryCompanyId;

    /**
     * 将companyIds设置为单个
     */
    public void setSingleCompanyIds(String companyId) {
        companyIds = Collections.singletonList(companyId);
    }

    /**
     * 获取第一个companyId
     */
    public String getFirstCompanyId() {
        if (companyIds == null) {
            return null;
        }
        return companyIds.get(0);
    }
}
