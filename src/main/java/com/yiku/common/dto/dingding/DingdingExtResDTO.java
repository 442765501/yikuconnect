package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/31 13:43
 */
@Data
public class DingdingExtResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * label
     */
    private String label;

    /**
     * key
     */
    private String key;
}
