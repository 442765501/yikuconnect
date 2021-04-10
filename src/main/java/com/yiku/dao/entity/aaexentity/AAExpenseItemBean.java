package com.yiku.dao.entity.aaexentity;

import java.util.Date;

public class AAExpenseItemBean {
    private Integer id;

    private String code;

    private String name;

    private Byte apportionflag;

    private Byte depth;

    private Byte isendnode;

    private Byte disabled;

    private String inid;

    private Integer idparent;

    private Integer dispatchmode;

    private Integer expensetype;

    private Date createdtime;

    private Integer taxrate;

    private Integer deductrate;

    private Byte isdeduct;

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

    public Byte getApportionflag() {
        return apportionflag;
    }

    public void setApportionflag(Byte apportionflag) {
        this.apportionflag = apportionflag;
    }

    public Byte getDepth() {
        return depth;
    }

    public void setDepth(Byte depth) {
        this.depth = depth;
    }

    public Byte getIsendnode() {
        return isendnode;
    }

    public void setIsendnode(Byte isendnode) {
        this.isendnode = isendnode;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getInid() {
        return inid;
    }

    public void setInid(String inid) {
        this.inid = inid == null ? null : inid.trim();
    }

    public Integer getIdparent() {
        return idparent;
    }

    public void setIdparent(Integer idparent) {
        this.idparent = idparent;
    }

    public Integer getDispatchmode() {
        return dispatchmode;
    }

    public void setDispatchmode(Integer dispatchmode) {
        this.dispatchmode = dispatchmode;
    }

    public Integer getExpensetype() {
        return expensetype;
    }

    public void setExpensetype(Integer expensetype) {
        this.expensetype = expensetype;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Integer getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(Integer taxrate) {
        this.taxrate = taxrate;
    }

    public Integer getDeductrate() {
        return deductrate;
    }

    public void setDeductrate(Integer deductrate) {
        this.deductrate = deductrate;
    }

    public Byte getIsdeduct() {
        return isdeduct;
    }

    public void setIsdeduct(Byte isdeduct) {
        this.isdeduct = isdeduct;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}