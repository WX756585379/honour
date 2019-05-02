package com.honour.server.entity.pojo;

import java.util.Date;

public class ChannelRoomModelMapping {
    private Long pid;

    private String channelcode;

    private Long innerchannelrmid;

    private Long targetchannelrmid;

    private Long innerchannelunitid;

    private Long innerrmid;

    private Long innersalermid;

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

    public Long getInnerchannelunitid() {
        return innerchannelunitid;
    }

    public void setInnerchannelunitid(Long innerchannelunitid) {
        this.innerchannelunitid = innerchannelunitid;
    }

    public Long getInnerrmid() {
        return innerrmid;
    }

    public void setInnerrmid(Long innerrmid) {
        this.innerrmid = innerrmid;
    }

    public Long getInnersalermid() {
        return innersalermid;
    }

    public void setInnersalermid(Long innersalermid) {
        this.innersalermid = innersalermid;
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