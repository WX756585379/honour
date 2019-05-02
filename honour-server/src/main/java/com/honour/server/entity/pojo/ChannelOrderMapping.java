package com.honour.server.entity.pojo;

import java.util.Date;

public class ChannelOrderMapping {
    private Long pid;

    private String channelcode;

    private Long innerorderid;

    private Long targetchannelorderid;

    private Long innerchannelunitid;

    private Long targetchannelunitid;

    private Long innerchannelrmid;

    private Long targetchannelrmid;

    private Long innerchannelproductid;

    private Long targetchannelproductid;

    private String userphone;

    private String resultmessage;

    private String status;

    private String createby;

    private Date createat;

    private String modifyby;

    private Date modifyat;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public Long getInnerorderid() {
        return innerorderid;
    }

    public void setInnerorderid(Long innerorderid) {
        this.innerorderid = innerorderid;
    }

    public Long getTargetchannelorderid() {
        return targetchannelorderid;
    }

    public void setTargetchannelorderid(Long targetchannelorderid) {
        this.targetchannelorderid = targetchannelorderid;
    }

    public Long getInnerchannelunitid() {
        return innerchannelunitid;
    }

    public void setInnerchannelunitid(Long innerchannelunitid) {
        this.innerchannelunitid = innerchannelunitid;
    }

    public Long getTargetchannelunitid() {
        return targetchannelunitid;
    }

    public void setTargetchannelunitid(Long targetchannelunitid) {
        this.targetchannelunitid = targetchannelunitid;
    }

    public Long getInnerchannelrmid() {
        return innerchannelrmid;
    }

    public void setInnerchannelrmid(Long innerchannelrmid) {
        this.innerchannelrmid = innerchannelrmid;
    }

    public Long getTargetchannelrmid() {
        return targetchannelrmid;
    }

    public void setTargetchannelrmid(Long targetchannelrmid) {
        this.targetchannelrmid = targetchannelrmid;
    }

    public Long getInnerchannelproductid() {
        return innerchannelproductid;
    }

    public void setInnerchannelproductid(Long innerchannelproductid) {
        this.innerchannelproductid = innerchannelproductid;
    }

    public Long getTargetchannelproductid() {
        return targetchannelproductid;
    }

    public void setTargetchannelproductid(Long targetchannelproductid) {
        this.targetchannelproductid = targetchannelproductid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getResultmessage() {
        return resultmessage;
    }

    public void setResultmessage(String resultmessage) {
        this.resultmessage = resultmessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public String getModifyby() {
        return modifyby;
    }

    public void setModifyby(String modifyby) {
        this.modifyby = modifyby;
    }

    public Date getModifyat() {
        return modifyat;
    }

    public void setModifyat(Date modifyat) {
        this.modifyat = modifyat;
    }
}