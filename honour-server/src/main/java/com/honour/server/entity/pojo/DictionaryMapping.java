package com.honour.server.entity.pojo;

import java.util.Date;

public class DictionaryMapping {
    private Long pid;

    private String channelcode;

    private String mappingtypecode;

    private String innervalue;

    private String innervalueremark;

    private String targetvalue;

    private String targetvalueremark;

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

    public String getMappingtypecode() {
        return mappingtypecode;
    }

    public void setMappingtypecode(String mappingtypecode) {
        this.mappingtypecode = mappingtypecode;
    }

    public String getInnervalue() {
        return innervalue;
    }

    public void setInnervalue(String innervalue) {
        this.innervalue = innervalue;
    }

    public String getInnervalueremark() {
        return innervalueremark;
    }

    public void setInnervalueremark(String innervalueremark) {
        this.innervalueremark = innervalueremark;
    }

    public String getTargetvalue() {
        return targetvalue;
    }

    public void setTargetvalue(String targetvalue) {
        this.targetvalue = targetvalue;
    }

    public String getTargetvalueremark() {
        return targetvalueremark;
    }

    public void setTargetvalueremark(String targetvalueremark) {
        this.targetvalueremark = targetvalueremark;
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