package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 11:06
 */
@Data
public class DingdingOperationDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 日期
     */
    private String date;

    /**
     * 运算结果
     */
    private String operation_result;

    /**
     * 运算类型
     */
    private String operation_type;

    /**
     * 用户Id
     */
    private String userid;

}
