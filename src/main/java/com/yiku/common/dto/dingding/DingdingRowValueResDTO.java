package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 16:26
 */
@Data
public class DingdingRowValueResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 组件类型
     */
    private String componentType;

    /**
     * 标签
     */
    private String label;

    /**
     * 扩展值
     */
    private DingdingExtResDTO extendValue;

    /**
     * 值
     */
    private String value;

    /**
     * key
     */
    private String key;
}
