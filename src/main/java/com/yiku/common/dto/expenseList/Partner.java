package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/20 10:10
 */
@Data
public class Partner implements Serializable {

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
     * 客户id
     */
    private Integer customId;

    /**
     * 客户名称
     */
    private String customName;

}
