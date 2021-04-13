package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/12 13:48
 */
@Data
public class DingdingReqDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * id
     */
    private Integer id;

    /**
     * 父类id
     */
    private Integer parentId;

    /**
     * corpId
     */
    private String corpId;

    /**
     * key
     */
    private String customKey;

    /**
     * secret
     */
    private String secret;

}
