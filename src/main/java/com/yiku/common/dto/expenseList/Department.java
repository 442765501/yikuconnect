package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/26 11:21
 */
@Data
public class Department implements Serializable {

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
}
