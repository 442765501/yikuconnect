package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/25 15:35
 */
@Data
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;

    /**
     * id
     */
    private Integer id;

    /**
     * 账号名称
     */
    private String name;

    /**
     * 账号名称编码
     */
    private String code;
}
