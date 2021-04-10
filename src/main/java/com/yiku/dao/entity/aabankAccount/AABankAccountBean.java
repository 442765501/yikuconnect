package com.yiku.dao.entity.aabankAccount;

import java.math.BigDecimal;
import java.util.Date;

public class AABankAccountBean {
    private String code;

    private String name;

    private String bankno;

    private Byte disabled;

    private String updatedby;

    private Byte isusingdeficitcontrol;

    private Byte isusingbankcheck;

    private BigDecimal newbalanceAbandon;

    private Byte virtualpay;

    private Integer id;

    private Integer idcurrency;

    private Integer iddepartment;

    private Integer idmarketingorgan;

    private Integer balancedirection;

    private Integer bankname;

    private Integer banknotype;

    private Date madedate;

    private Date updated;

    private Date dateofusingbankcheck;

    private Date createdtime;

    private String weixinappid;

    private String weixinpaykey;

    private String alipayserviceno;

    private String alipayappid;

    private String weixinmerchantno;

    private String weixinserviceno;

    private String weixinserviceappid;

    private String alipaypublickey;

    private String merchantpublickey;

    private String merchantprivatekey;

    private Integer encryptionmethod;

    private String imagefile;

    private String alipayserviceappid;

    private String accountcode;

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

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
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

    public Byte getIsusingdeficitcontrol() {
        return isusingdeficitcontrol;
    }

    public void setIsusingdeficitcontrol(Byte isusingdeficitcontrol) {
        this.isusingdeficitcontrol = isusingdeficitcontrol;
    }

    public Byte getIsusingbankcheck() {
        return isusingbankcheck;
    }

    public void setIsusingbankcheck(Byte isusingbankcheck) {
        this.isusingbankcheck = isusingbankcheck;
    }

    public BigDecimal getNewbalanceAbandon() {
        return newbalanceAbandon;
    }

    public void setNewbalanceAbandon(BigDecimal newbalanceAbandon) {
        this.newbalanceAbandon = newbalanceAbandon;
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

    public Integer getIdcurrency() {
        return idcurrency;
    }

    public void setIdcurrency(Integer idcurrency) {
        this.idcurrency = idcurrency;
    }

    public Integer getIddepartment() {
        return iddepartment;
    }

    public void setIddepartment(Integer iddepartment) {
        this.iddepartment = iddepartment;
    }

    public Integer getIdmarketingorgan() {
        return idmarketingorgan;
    }

    public void setIdmarketingorgan(Integer idmarketingorgan) {
        this.idmarketingorgan = idmarketingorgan;
    }

    public Integer getBalancedirection() {
        return balancedirection;
    }

    public void setBalancedirection(Integer balancedirection) {
        this.balancedirection = balancedirection;
    }

    public Integer getBankname() {
        return bankname;
    }

    public void setBankname(Integer bankname) {
        this.bankname = bankname;
    }

    public Integer getBanknotype() {
        return banknotype;
    }

    public void setBanknotype(Integer banknotype) {
        this.banknotype = banknotype;
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

    public Date getDateofusingbankcheck() {
        return dateofusingbankcheck;
    }

    public void setDateofusingbankcheck(Date dateofusingbankcheck) {
        this.dateofusingbankcheck = dateofusingbankcheck;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getWeixinappid() {
        return weixinappid;
    }

    public void setWeixinappid(String weixinappid) {
        this.weixinappid = weixinappid == null ? null : weixinappid.trim();
    }

    public String getWeixinpaykey() {
        return weixinpaykey;
    }

    public void setWeixinpaykey(String weixinpaykey) {
        this.weixinpaykey = weixinpaykey == null ? null : weixinpaykey.trim();
    }

    public String getAlipayserviceno() {
        return alipayserviceno;
    }

    public void setAlipayserviceno(String alipayserviceno) {
        this.alipayserviceno = alipayserviceno == null ? null : alipayserviceno.trim();
    }

    public String getAlipayappid() {
        return alipayappid;
    }

    public void setAlipayappid(String alipayappid) {
        this.alipayappid = alipayappid == null ? null : alipayappid.trim();
    }

    public String getWeixinmerchantno() {
        return weixinmerchantno;
    }

    public void setWeixinmerchantno(String weixinmerchantno) {
        this.weixinmerchantno = weixinmerchantno == null ? null : weixinmerchantno.trim();
    }

    public String getWeixinserviceno() {
        return weixinserviceno;
    }

    public void setWeixinserviceno(String weixinserviceno) {
        this.weixinserviceno = weixinserviceno == null ? null : weixinserviceno.trim();
    }

    public String getWeixinserviceappid() {
        return weixinserviceappid;
    }

    public void setWeixinserviceappid(String weixinserviceappid) {
        this.weixinserviceappid = weixinserviceappid == null ? null : weixinserviceappid.trim();
    }

    public String getAlipaypublickey() {
        return alipaypublickey;
    }

    public void setAlipaypublickey(String alipaypublickey) {
        this.alipaypublickey = alipaypublickey == null ? null : alipaypublickey.trim();
    }

    public String getMerchantpublickey() {
        return merchantpublickey;
    }

    public void setMerchantpublickey(String merchantpublickey) {
        this.merchantpublickey = merchantpublickey == null ? null : merchantpublickey.trim();
    }

    public String getMerchantprivatekey() {
        return merchantprivatekey;
    }

    public void setMerchantprivatekey(String merchantprivatekey) {
        this.merchantprivatekey = merchantprivatekey == null ? null : merchantprivatekey.trim();
    }

    public Integer getEncryptionmethod() {
        return encryptionmethod;
    }

    public void setEncryptionmethod(Integer encryptionmethod) {
        this.encryptionmethod = encryptionmethod;
    }

    public String getImagefile() {
        return imagefile;
    }

    public void setImagefile(String imagefile) {
        this.imagefile = imagefile == null ? null : imagefile.trim();
    }

    public String getAlipayserviceappid() {
        return alipayserviceappid;
    }

    public void setAlipayserviceappid(String alipayserviceappid) {
        this.alipayserviceappid = alipayserviceappid == null ? null : alipayserviceappid.trim();
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode == null ? null : accountcode.trim();
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}