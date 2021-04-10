package com.yiku.dao.entity.eapEnumItem;

public class EapEnumItemBean {
    private Integer id;

    private String code;

    private String name;

    private Byte customuse;

    private Byte isextend;

    private Byte isdeleted;

    private Integer position;

    private String expressionname;

    private Integer idenum;

    private String defaultvalue;

    private String remark;

    private byte[] ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getCustomuse() {
        return customuse;
    }

    public void setCustomuse(Byte customuse) {
        this.customuse = customuse;
    }

    public Byte getIsextend() {
        return isextend;
    }

    public void setIsextend(Byte isextend) {
        this.isextend = isextend;
    }

    public Byte getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Byte isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getExpressionname() {
        return expressionname;
    }

    public void setExpressionname(String expressionname) {
        this.expressionname = expressionname == null ? null : expressionname.trim();
    }

    public Integer getIdenum() {
        return idenum;
    }

    public void setIdenum(Integer idenum) {
        this.idenum = idenum;
    }

    public String getDefaultvalue() {
        return defaultvalue;
    }

    public void setDefaultvalue(String defaultvalue) {
        this.defaultvalue = defaultvalue == null ? null : defaultvalue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}