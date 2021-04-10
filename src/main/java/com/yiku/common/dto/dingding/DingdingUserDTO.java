package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/5 13:27
 */
@Data
public class DingdingUserDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 角色列表。
     */
    private String role_list;

    /**
     * 用户信息
     */
    private String userid;

    /**
     * 部门id
     */
    private String dept_id_list;




}
