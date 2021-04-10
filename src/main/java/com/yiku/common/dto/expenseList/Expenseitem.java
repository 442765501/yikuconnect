package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/25 15:38
 */
@Data
public class Expenseitem implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;


    /**
     * 收入编码
     */
    private String code;

}
