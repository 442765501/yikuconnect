package com.yiku.common.dto.ueight;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/20 15:13
 */
@Data
public class UeightReqExtDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 费用金额
     */
    private String expenseAmount;

    /**
     * 用途
     */
    private String purpose;


}
