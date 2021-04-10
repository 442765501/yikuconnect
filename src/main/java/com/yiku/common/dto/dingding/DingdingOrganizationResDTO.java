package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/8 21:19
 */
@Data
public class DingdingOrganizationResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;


    /**
     * 地区
     */
    private String region_location;
}
