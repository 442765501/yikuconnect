package com.yiku.dao.entity.aasettleStyle;

import java.util.Date;

public class AASettleStyleBean {
    private String code;

    private String name;

    private Byte disabled;

    private String updatedby;

    private Byte chequemanagement;

    private Byte virtualpay;

    private Integer id;

    private Integer idbankaccount;

    private Date madedate;

    private Date updated;

    private Date createdtime;

    private byte[] ts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby == null ? null : updatedby.trim();
    }

    public Byte getChequemanagement() {
        return chequemanagement;
    }

    public void setChequemanagement(Byte chequemanagement) {
        this.chequemanagement = chequemanagement;
    }

    public Byte getVirtualpay() {
        return virtualpay;
    }

    public void setVirtualpay(Byte virtualpay) {
        this.virtualpay = virtualpay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdbankaccount() {
        return idbankaccount;
    }

    public void setIdbankaccount(Integer idbankaccount) {
        this.idbankaccount = idbankaccount;
    }

    public Date getMadedate() {
        return madedate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}