package com.yiku.common.dto.expenseList;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/25 15:37
 */
public class Details implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;

    /**
     * 单明细的收入
     */
    private Expenseitem expenseitem;

    /**
     * 税率
     */
    private Double taxRate;

    /**
     * 金额
     */
    private Double origAmount;

    /**
     * 税额
     */
    private Double origTax;

    /**
     *含税金额
     */
    private Double origTaxAmount;


    @JSONField(name = "TaxRate")
    public Double getTaxRate() {
        return taxRate;
    }

    public Expenseitem getExpenseitem() {
        return expenseitem;
    }

    public void setExpenseitem(Expenseitem expenseitem) {
        this.expenseitem = expenseitem;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
    @JSONField(name = "OrigAmount")
    public Double getOrigAmount() {
        return origAmount;
    }

    public void setOrigAmount(Double origAmount) {
        this.origAmount = origAmount;
    }
    @JSONField(name = "OrigTax")
    public Double getOrigTax() {
        return origTax;
    }

    public void setOrigTax(Double origTax) {
        this.origTax = origTax;
    }
    @JSONField(name = "OrigTaxAmount")
    public Double getOrigTaxAmount() {
        return origTaxAmount;
    }

    public void setOrigTaxAmount(Double origTaxAmount) {
        this.origTaxAmount = origTaxAmount;
    }


}
