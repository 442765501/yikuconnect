package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 11:03
 */
@Data
public class DingdingFormDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 组件类型
     */
    private String component_type;

    /**
     * id
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 值
     */
    private String value;

    /**
     * 外部值
     */
    private String ext_value;


}
