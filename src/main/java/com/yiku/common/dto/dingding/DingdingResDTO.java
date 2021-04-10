package com.yiku.common.dto.dingding;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 17:27
 */
@Data
public class DingdingResDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * errcode
     */
    private Integer errcode;

    /**
     * errmsg
     */
    private String errmsg;

    /**
     * process_instance
     */
    private String process_instance;

    /**
     * request_id
     */
    private String request_id;

    /**
     * result
     */
    private String result;


}
