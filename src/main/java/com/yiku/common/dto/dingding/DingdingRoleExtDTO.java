package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/6 9:59
 */
@Data
public class DingdingRoleExtDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;


    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;
}

