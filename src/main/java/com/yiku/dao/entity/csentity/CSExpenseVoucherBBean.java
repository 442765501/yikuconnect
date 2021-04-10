package com.yiku.dao.entity.csentity;

import java.math.BigDecimal;

public class CSExpenseVoucherBBean {
    private Integer id;

    private String code;

    private BigDecimal origamount;

    private BigDecimal amount;

    private BigDecimal origtax;

    private BigDecimal tax;

    private BigDecimal origtaxamount;

    private BigDecimal taxamount;

    private BigDecimal origsettleamount;

    private BigDecimal settleamount;

    private BigDecimal taxrate;

    private Byte isapportion;

    private Byte istax;

    private Byte ismodifyorigtax;

    private String priuserdefnvc1;

    private BigDecimal priuserdefdecm1;

    private String priuserdefnvc2;

    private BigDecimal priuserdefdecm2;

    private String priuserdefnvc3;

    private BigDecimal priuserdefdecm3;

    private String priuserdefnvc4;

    private BigDecimal priuserdefdecm4;

    private String pubuserdefnvc1;

    private BigDecimal pubuserdefdecm1;

    private String pubuserdefnvc2;

    private BigDecimal pubuserdefdecm2;

    private String pubuserdefnvc3;

    private BigDecimal pubuserdefdecm3;

    private String pubuserdefnvc4;

    private BigDecimal pubuserdefdecm4;

    private String expenseitemuse;

    private Integer idexpenseitem;

    private Integer idproject;

    private Integer idexpensevoucherdto;

    private Integer saleorderdetailid;

    private Integer idexpenseallocationvouchertype;

    private String detailaccessory;

    private Byte isdeduct;

    private BigDecimal deductrate;

    private BigDecimal origdeductamount;

    private BigDecimal deductamount;

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

    public BigDecimal getOrigamount() {
        return origamount;
    }

    public void setOrigamount(BigDecimal origamount) {
        this.origamount = origamount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOrigtax() {
        return origtax;
    }

    public void setOrigtax(BigDecimal origtax) {
        this.origtax = origtax;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getOrigtaxamount() {
        return origtaxamount;
    }

    public void setOrigtaxamount(BigDecimal origtaxamount) {
        this.origtaxamount = origtaxamount;
    }

    public BigDecimal getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(BigDecimal taxamount) {
        this.taxamount = taxamount;
    }

    public BigDecimal getOrigsettleamount() {
        return origsettleamount;
    }

    public void setOrigsettleamount(BigDecimal origsettleamount) {
        this.origsettleamount = origsettleamount;
    }

    public BigDecimal getSettleamount() {
        return settleamount;
    }

    public void setSettleamount(BigDecimal settleamount) {
        this.settleamount = settleamount;
    }

    public BigDecimal getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(BigDecimal taxrate) {
        this.taxrate = taxrate;
    }

    public Byte getIsapportion() {
        return isapportion;
    }

    public void setIsapportion(Byte isapportion) {
        this.isapportion = isapportion;
    }

    public Byte getIstax() {
        return istax;
    }

    public void setIstax(Byte istax) {
        this.istax = istax;
    }

    public Byte getIsmodifyorigtax() {
        return ismodifyorigtax;
    }

    public void setIsmodifyorigtax(Byte ismodifyorigtax) {
        this.ismodifyorigtax = ismodifyorigtax;
    }

    public String getPriuserdefnvc1() {
        return priuserdefnvc1;
    }

    public void setPriuserdefnvc1(String priuserdefnvc1) {
        this.priuserdefnvc1 = priuserdefnvc1 == null ? null : priuserdefnvc1.trim();
    }

    public BigDecimal getPriuserdefdecm1() {
        return priuserdefdecm1;
    }

    public void setPriuserdefdecm1(BigDecimal priuserdefdecm1) {
        this.priuserdefdecm1 = priuserdefdecm1;
    }

    public String getPriuserdefnvc2() {
        return priuserdefnvc2;
    }

    public void setPriuserdefnvc2(String priuserdefnvc2) {
        this.priuserdefnvc2 = priuserdefnvc2 == null ? null : priuserdefnvc2.trim();
    }

    public BigDecimal getPriuserdefdecm2() {
        return priuserdefdecm2;
    }

    public void setPriuserdefdecm2(BigDecimal priuserdefdecm2) {
        this.priuserdefdecm2 = priuserdefdecm2;
    }

    public String getPriuserdefnvc3() {
        return priuserdefnvc3;
    }

    public void setPriuserdefnvc3(String priuserdefnvc3) {
        this.priuserdefnvc3 = priuserdefnvc3 == null ? null : priuserdefnvc3.trim();
    }

    public BigDecimal getPriuserdefdecm3() {
        return priuserdefdecm3;
    }

    public void setPriuserdefdecm3(BigDecimal priuserdefdecm3) {
        this.priuserdefdecm3 = priuserdefdecm3;
    }

    public String getPriuserdefnvc4() {
        return priuserdefnvc4;
    }

    public void setPriuserdefnvc4(String priuserdefnvc4) {
        this.priuserdefnvc4 = priuserdefnvc4 == null ? null : priuserdefnvc4.trim();
    }

    public BigDecimal getPriuserdefdecm4() {
        return priuserdefdecm4;
    }

    public void setPriuserdefdecm4(BigDecimal priuserdefdecm4) {
        this.priuserdefdecm4 = priuserdefdecm4;
    }

    public String getPubuserdefnvc1() {
        return pubuserdefnvc1;
    }

    public void setPubuserdefnvc1(String pubuserdefnvc1) {
        this.pubuserdefnvc1 = pubuserdefnvc1 == null ? null : pubuserdefnvc1.trim();
    }

    public BigDecimal getPubuserdefdecm1() {
        return pubuserdefdecm1;
    }

    public void setPubuserdefdecm1(BigDecimal pubuserdefdecm1) {
        this.pubuserdefdecm1 = pubuserdefdecm1;
    }

    public String getPubuserdefnvc2() {
        return pubuserdefnvc2;
    }

    public void setPubuserdefnvc2(String pubuserdefnvc2) {
        this.pubuserdefnvc2 = pubuserdefnvc2 == null ? null : pubuserdefnvc2.trim();
    }

    public BigDecimal getPubuserdefdecm2() {
        return pubuserdefdecm2;
    }

    public void setPubuserdefdecm2(BigDecimal pubuserdefdecm2) {
        this.pubuserdefdecm2 = pubuserdefdecm2;
    }

    public String getPubuserdefnvc3() {
        return pubuserdefnvc3;
    }

    public void setPubuserdefnvc3(String pubuserdefnvc3) {
        this.pubuserdefnvc3 = pubuserdefnvc3 == null ? null : pubuserdefnvc3.trim();
    }

    public BigDecimal getPubuserdefdecm3() {
        return pubuserdefdecm3;
    }

    public void setPubuserdefdecm3(BigDecimal pubuserdefdecm3) {
        this.pubuserdefdecm3 = pubuserdefdecm3;
    }

    public String getPubuserdefnvc4() {
        return pubuserdefnvc4;
    }

    public void setPubuserdefnvc4(String pubuserdefnvc4) {
        this.pubuserdefnvc4 = pubuserdefnvc4 == null ? null : pubuserdefnvc4.trim();
    }

    public BigDecimal getPubuserdefdecm4() {
        return pubuserdefdecm4;
    }

    public void setPubuserdefdecm4(BigDecimal pubuserdefdecm4) {
        this.pubuserdefdecm4 = pubuserdefdecm4;
    }

    public String getExpenseitemuse() {
        return expenseitemuse;
    }

    public void setExpenseitemuse(String expenseitemuse) {
        this.expenseitemuse = expenseitemuse == null ? null : expenseitemuse.trim();
    }

    public Integer getIdexpenseitem() {
        return idexpenseitem;
    }

    public void setIdexpenseitem(Integer idexpenseitem) {
        this.idexpenseitem = idexpenseitem;
    }

    public Integer getIdproject() {
        return idproject;
    }

    public void setIdproject(Integer idproject) {
        this.idproject = idproject;
    }

    public Integer getIdexpensevoucherdto() {
        return idexpensevoucherdto;
    }

    public void setIdexpensevoucherdto(Integer idexpensevoucherdto) {
        this.idexpensevoucherdto = idexpensevoucherdto;
    }

    public Integer getSaleorderdetailid() {
        return saleorderdetailid;
    }

    public void setSaleorderdetailid(Integer saleorderdetailid) {
        this.saleorderdetailid = saleorderdetailid;
    }

    public Integer getIdexpenseallocationvouchertype() {
        return idexpenseallocationvouchertype;
    }

    public void setIdexpenseallocationvouchertype(Integer idexpenseallocationvouchertype) {
        this.idexpenseallocationvouchertype = idexpenseallocationvouchertype;
    }

    public String getDetailaccessory() {
        return detailaccessory;
    }

    public void setDetailaccessory(String detailaccessory) {
        this.detailaccessory = detailaccessory == null ? null : detailaccessory.trim();
    }

    public Byte getIsdeduct() {
        return isdeduct;
    }

    public void setIsdeduct(Byte isdeduct) {
        this.isdeduct = isdeduct;
    }

    public BigDecimal getDeductrate() {
        return deductrate;
    }

    public void setDeductrate(BigDecimal deductrate) {
        this.deductrate = deductrate;
    }

    public BigDecimal getOrigdeductamount() {
        return origdeductamount;
    }

    public void setOrigdeductamount(BigDecimal origdeductamount) {
        this.origdeductamount = origdeductamount;
    }

    public BigDecimal getDeductamount() {
        return deductamount;
    }

    public void setDeductamount(BigDecimal deductamount) {
        this.deductamount = deductamount;
    }

    public byte[] getTs() {
        return ts;
    }

    public void setTs(byte[] ts) {
        this.ts = ts;
    }
}