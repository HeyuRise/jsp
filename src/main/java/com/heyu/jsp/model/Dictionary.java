package com.heyu.jsp.model;

import java.util.Date;

public class Dictionary {
    private Integer id;

    private String type;

    private Integer innerId;

    private String innerCode;

    private String valueStr;

    private Integer valueInt;

    private Date valueTime;

    private String paramStr1;

    private String paramStr2;

    private Integer paramInt1;

    private Integer paramInt2;

    private Integer enable;

    private String description;

    private Date innerCreateTime;

    private Date innerUpdateTime;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public String getInnerCode() {
        return innerCode;
    }

    public void setInnerCode(String innerCode) {
        this.innerCode = innerCode == null ? null : innerCode.trim();
    }

    public String getValueStr() {
        return valueStr;
    }

    public void setValueStr(String valueStr) {
        this.valueStr = valueStr == null ? null : valueStr.trim();
    }

    public Integer getValueInt() {
        return valueInt;
    }

    public void setValueInt(Integer valueInt) {
        this.valueInt = valueInt;
    }

    public Date getValueTime() {
        return valueTime;
    }

    public void setValueTime(Date valueTime) {
        this.valueTime = valueTime;
    }

    public String getParamStr1() {
        return paramStr1;
    }

    public void setParamStr1(String paramStr1) {
        this.paramStr1 = paramStr1 == null ? null : paramStr1.trim();
    }

    public String getParamStr2() {
        return paramStr2;
    }

    public void setParamStr2(String paramStr2) {
        this.paramStr2 = paramStr2 == null ? null : paramStr2.trim();
    }

    public Integer getParamInt1() {
        return paramInt1;
    }

    public void setParamInt1(Integer paramInt1) {
        this.paramInt1 = paramInt1;
    }

    public Integer getParamInt2() {
        return paramInt2;
    }

    public void setParamInt2(Integer paramInt2) {
        this.paramInt2 = paramInt2;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getInnerCreateTime() {
        return innerCreateTime;
    }

    public void setInnerCreateTime(Date innerCreateTime) {
        this.innerCreateTime = innerCreateTime;
    }

    public Date getInnerUpdateTime() {
        return innerUpdateTime;
    }

    public void setInnerUpdateTime(Date innerUpdateTime) {
        this.innerUpdateTime = innerUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}