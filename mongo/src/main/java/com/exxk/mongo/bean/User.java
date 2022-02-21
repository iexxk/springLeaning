package com.exxk.mongo.bean;

import cn.hutool.json.JSONObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@Document(collection = "user_info")
public class User extends BaseCompany implements Serializable {
    private static final long serialVersionUID = -168043532788755638L;

    /**
     * 设备id
     */
    @Id
    private String id;

    /**
     * 设备名称
     */
    @Field("term_name")
    private String termName;

    /**
     * 设备ip地址
     */
    @Field("term_ip")
    private String termIp;

    /**
     * 设备版本号
     */
    @Field("term_version")
    private String termVersion;

    /**
     * 产品id
     */
    @Field("product_id")
    private String productId;

    /**
     * 产品实体
     */
    @Field("user")
    private User user;

    /**
     * 区域Id
     */
    @Field(value = "area_id", targetType = FieldType.OBJECT_ID)
    private String areaId;



    /**
     * 区域路径
     */
    @Field("area_path")
    private String areaPath;

    /**
     * 设备状态更新时间
     */
    @Field("term_update_time")
    private Date termUpdateTime;

    /**
     * 设备创建时间
     */
    @Field("term_create_time")
    private Date termCreateTime;

    /**
     * 设备状态
     * 0 在线 心跳报文上来后表示在线
     * 1 离线 超过指定时间没有心跳则表示离线
     * 2 已激活 设备扫码接入的时候表示已激活
     */
    @Field("term_status")
    private Integer termStatus;

    /**
     * 设备状态明细
     */
    @Field("status_detail")
    private JSONObject statusDetail;

    /**
     * json格式的扩展属性
     */
    @Field("extend_attribute")
    private JSONObject extendAttribute;

    /**
     * 页面使用的查询参数
     */
    @Transient
    private JSONObject queryParams;

    /**
     * 接入的clientKey信息  如果为MQTT则为用户名信息
     */
    @Field("access_key")
    private String accessKey;
    /**
     * 接入的clientSecret信息  如果为MQTT则为密码信息
     */
    @Field("secret_key")
    private String secretKey;

    @Transient
    private String productType;

    @Transient
    private List<String> hideTermIds;

    @Transient
    private List<String> defaultTermIds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getTermIp() {
        return termIp;
    }

    public void setTermIp(String termIp) {
        this.termIp = termIp;
    }

    public String getTermVersion() {
        return termVersion;
    }

    public void setTermVersion(String termVersion) {
        this.termVersion = termVersion;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaPath() {
        return areaPath;
    }

    public void setAreaPath(String areaPath) {
        this.areaPath = areaPath;
    }

    public Date getTermUpdateTime() {
        return termUpdateTime;
    }

    public void setTermUpdateTime(Date termUpdateTime) {
        this.termUpdateTime = termUpdateTime;
    }

    public Date getTermCreateTime() {
        return termCreateTime;
    }

    public void setTermCreateTime(Date termCreateTime) {
        this.termCreateTime = termCreateTime;
    }

    public Integer getTermStatus() {
        return termStatus;
    }

    public void setTermStatus(Integer termStatus) {
        this.termStatus = termStatus;
    }

    public JSONObject getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(JSONObject statusDetail) {
        this.statusDetail = statusDetail;
    }

    public JSONObject getExtendAttribute() {
        return extendAttribute;
    }

    public void setExtendAttribute(JSONObject extendAttribute) {
        this.extendAttribute = extendAttribute;
    }

    public JSONObject getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(JSONObject queryParams) {
        this.queryParams = queryParams;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public List<String> getHideTermIds() {
        return hideTermIds;
    }

    public void setHideTermIds(List<String> hideTermIds) {
        this.hideTermIds = hideTermIds;
    }

    public List<String> getDefaultTermIds() {
        return defaultTermIds;
    }

    public void setDefaultTermIds(List<String> defaultTermIds) {
        this.defaultTermIds = defaultTermIds;
    }
}
