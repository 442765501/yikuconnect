package com.yiku.dao.entity.aacurrency;

import java.math.BigDecimal;
import java.util.Date;

public class AACurrencyBean {
    private String code;

    private String name;

    private String currencysign;

    private Byte isnative;

    private BigDecimal maxerror;

    private BigDecimal exchangerate;

    private Byte disabled;

    private String updatedby;

    private Integer id;

    private Integer caldirection;

    private Date madedate;

    private Date updated;

    private Date createdtime;

    private Integer exchangeratetype;

    private Integer accountdate;

    private Integer accountyear;

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

    public String getCurrencysign() {
        return currencysign;
    }

    public void setCurrencysign(String currencysign) {
        this.currencysign = currencysign == null ? null : currencysign.trim();
    }

    public Byte getIsnative() {
        return isnative;
    }

    public void setIsnative(Byte isnative) {
        this.isnative = isnative;
    }

    public BigDecimal getMaxerror() {
        return maxerror;
    }

    public void setMaxerror(BigDecimal maxerror) {
        this.maxerror = maxerror;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaldirection() {
        return caldirection;
    }

    public void setCaldirection(Integer caldirection) {
        this.caldirection = caldirection;
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

    public Integer getExchangeratetype() {
        return exchangeratetype;
    }

    public void setExchangeratetype(Integer exchangeratetype) {
        this.exchangeratetype = exchangeratetype;
    }

    public Integer getAccountdate() {
        return accountdate;
    }

    public void setAccountdate(Integer accountdate) {
        this.accountdate = accountdate;
    }

    public Integer getAccountyear() {
        return accountyear;
    }

    public void setAccountyear(Integer accountyear) {
        this.accountyear = accountyear;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}