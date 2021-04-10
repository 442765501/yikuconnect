package com.yiku.common.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * Project Name: kc-apiservice
 * Package Name: cn.kingcar.apiservice.api.dto
 * Function: 通用返回结果集
 * user: San Date: 2018/2/9
 */
@Data
public class APIResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;
    private boolean flag = false;
    private T data;
    private String ErrorMessage;
    private String statusCode;

}
