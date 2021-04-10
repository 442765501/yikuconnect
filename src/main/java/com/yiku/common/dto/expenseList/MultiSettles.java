package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/25 15:32
 */
@Data
public class MultiSettles implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;


    /**
     * 结算
     */
    private Settlestyle settlestyle;

    /**
     * 银行收入
     */
    private BankAccount bankaccount;

    /**
     * 收入金额
     */
    private Double OrigAmount;

    /**
     * 票据单号
     */
    private String billNo;


}
