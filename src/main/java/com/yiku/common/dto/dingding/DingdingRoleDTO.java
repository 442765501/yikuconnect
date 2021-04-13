package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/5 13:33
 */
@Data
public class DingdingRoleDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;


    /**
     * 角色组名称。
     */
    private String group_name;

    /**
     *角色组ID
     */
    private String groupId;


    /**
     * 角色组
     */
    private String roles;


    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;


}
