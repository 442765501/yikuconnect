package com.yiku.dao.entity.aabusiType;

import java.util.Date;

public class AABusiTypeBean {
    private String code;

    private String name;

    private Byte disabled;

    private String updatedby;

    private Byte issystem;

    private String expressionname;

    private Integer id;

    private Integer idrdstylein;

    private Integer idrdstyleout;

    private Integer businessvoucher;

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

    public Byte getIssystem() {
        return issystem;
    }

    public void setIssystem(Byte issystem) {
        this.issystem = issystem;
    }

    public String getExpressionname() {
        return expressionname;
    }

    public void setExpressionname(String expressionname) {
        this.expressionname = expressionname == null ? null : expressionname.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdrdstylein() {
        return idrdstylein;
    }

    public void setIdrdstylein(Integer idrdstylein) {
        this.idrdstylein = idrdstylein;
    }

    public Integer getIdrdstyleout() {
        return idrdstyleout;
    }

    public void setIdrdstyleout(Integer idrdstyleout) {
        this.idrdstyleout = idrdstyleout;
    }

    public Integer getBusinessvoucher() {
        return businessvoucher;
    }

    public void setBusinessvoucher(Integer businessvoucher) {
        this.businessvoucher = businessvoucher;
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