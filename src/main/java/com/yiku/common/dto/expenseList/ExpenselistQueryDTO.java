package com.yiku.common.dto.expenseList;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/16 16:26
 */

public class ExpenselistQueryDTO implements Serializable {
    private static final long serialVersionUID = 197018972999527001L;

    /**
     * id
     */
    private Integer id;


    /**
     * 名字
     */
    private String name;

    /**
     * 编码
     */
    private String code;


    /**
     * 将要生成的收入单单据日期
     */
    private String voucherDate;

    /**
     * 由外部单据生成T+收入单的外部单据号
     */
    private String externalCode;


    /**
     * T+系统中存在的业务类型，其中code表示该业务类型的编码
     * 37 往来费用 38 现金费用
     */
    private BusinessType businessType;

    /**
     * T+系统中存在的往来单位，其中的code,表示该往来单位的编码
     */
    private Partner partner;

    /**
     * 对应T+的收入单中的备注字段
     */
    private String memo;

    /**
     * T+系统中存在的票据类型，其中code表示该票据类型的编码
     * 00:运费发票 , 01:专用发票 , 02:收据，03:普通发票-内含, 04:普通发票
     */
    private BillType billType;

    /**
     * 对应收入单的现结金额明细
     */
    private List<MultiSettles> multiSettles;

    /**
     * 收入单的明细
     */
    private List<Details> details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JSONField(name = "VoucherDate")
    public String getVoucherDate() {
        return voucherDate;
    }


    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    @JSONField(name = "ExternalCode")
    public String getExternalCode() {
        return externalCode;
    }


    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    @JSONField(name = "BusinessType")
    public BusinessType getBusinessType() {
        return businessType;
    }


    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    @JSONField(name = "Partner")
    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @JSONField(name = "Memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    @JSONField(name = "MultiSettles")
    public List<MultiSettles> getMultiSettles() {
        return multiSettles;
    }

    public void setMultiSettles(List<MultiSettles> multiSettles) {
        this.multiSettles = multiSettles;
    }

    @JSONField(name = "Details")
    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    //  /**
//     * 公共参数key
//     */
//    private String appKey;
//
//    /**
//     * 公共参数appSecret
//     */
//
//    private String appSecret;
//
//    /**
//     * 登入名字
//     */
//    private String userName;
//
//    /**
//     * 登入密码
//     */
//    private String Password;
//
//    /**
//     * 账套号
//     */
//    private String AccountNumber;
//
//    /**
//     * 登录日期
//     */
//    private String loginDate;


}
