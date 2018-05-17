package com.pcbwx.ebes.model;

import java.util.Date;

public class Express {
    private Integer expressId;

    private String orderId;

    private String innerOrderId;

    private String mailNo;

    private String mailNoChild;

    private String destCode;

    private String senderCompany;

    private String senderName;

    private String senderTel;

    private String senderMobile;

    private String senderProvince;

    private String senderCity;

    private String senderCounty;

    private String senderAddress;

    private String recipientsCompany;

    private String recipientsName;

    private String recipientsTel;

    private String recipientsMobile;

    private String recipientsProvince;

    private String recipientsCity;

    private String recipientsCounty;

    private String address;

    private Integer count;

    private String creator;

    private String payTypeName;

    private String payAccount;

    private String money;

    private Integer productId;

    private Integer parcelNumber;

    private Integer filterResult;

    private String backReason;

    private String remarkCode;

    private String status;

    private Date createTime;

    private Date updateTime;

    public Integer getExpressId() {
        return expressId;
    }

    public void setExpressId(Integer expressId) {
        this.expressId = expressId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getInnerOrderId() {
        return innerOrderId;
    }

    public void setInnerOrderId(String innerOrderId) {
        this.innerOrderId = innerOrderId == null ? null : innerOrderId.trim();
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo == null ? null : mailNo.trim();
    }

    public String getMailNoChild() {
        return mailNoChild;
    }

    public void setMailNoChild(String mailNoChild) {
        this.mailNoChild = mailNoChild == null ? null : mailNoChild.trim();
    }

    public String getDestCode() {
        return destCode;
    }

    public void setDestCode(String destCode) {
        this.destCode = destCode == null ? null : destCode.trim();
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany == null ? null : senderCompany.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel == null ? null : senderTel.trim();
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile == null ? null : senderMobile.trim();
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince == null ? null : senderProvince.trim();
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity == null ? null : senderCity.trim();
    }

    public String getSenderCounty() {
        return senderCounty;
    }

    public void setSenderCounty(String senderCounty) {
        this.senderCounty = senderCounty == null ? null : senderCounty.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress == null ? null : senderAddress.trim();
    }

    public String getRecipientsCompany() {
        return recipientsCompany;
    }

    public void setRecipientsCompany(String recipientsCompany) {
        this.recipientsCompany = recipientsCompany == null ? null : recipientsCompany.trim();
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName == null ? null : recipientsName.trim();
    }

    public String getRecipientsTel() {
        return recipientsTel;
    }

    public void setRecipientsTel(String recipientsTel) {
        this.recipientsTel = recipientsTel == null ? null : recipientsTel.trim();
    }

    public String getRecipientsMobile() {
        return recipientsMobile;
    }

    public void setRecipientsMobile(String recipientsMobile) {
        this.recipientsMobile = recipientsMobile == null ? null : recipientsMobile.trim();
    }

    public String getRecipientsProvince() {
        return recipientsProvince;
    }

    public void setRecipientsProvince(String recipientsProvince) {
        this.recipientsProvince = recipientsProvince == null ? null : recipientsProvince.trim();
    }

    public String getRecipientsCity() {
        return recipientsCity;
    }

    public void setRecipientsCity(String recipientsCity) {
        this.recipientsCity = recipientsCity == null ? null : recipientsCity.trim();
    }

    public String getRecipientsCounty() {
        return recipientsCounty;
    }

    public void setRecipientsCounty(String recipientsCounty) {
        this.recipientsCounty = recipientsCounty == null ? null : recipientsCounty.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getParcelNumber() {
        return parcelNumber;
    }

    public void setParcelNumber(Integer parcelNumber) {
        this.parcelNumber = parcelNumber;
    }

    public Integer getFilterResult() {
        return filterResult;
    }

    public void setFilterResult(Integer filterResult) {
        this.filterResult = filterResult;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason == null ? null : backReason.trim();
    }

    public String getRemarkCode() {
        return remarkCode;
    }

    public void setRemarkCode(String remarkCode) {
        this.remarkCode = remarkCode == null ? null : remarkCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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