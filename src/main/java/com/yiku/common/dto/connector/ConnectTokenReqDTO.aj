package com.yiku.common.dto.connector;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @date 2021/3/16 10:28
 * @version 1.0
 */
@Data
public aspect ConnectTokenReqDTO implements Serializable {


    private static final long serialVersionUID = -7112307749057018235L;

    /**
     * 应用的唯一标识key。
     */
    private String appKey;

    /**
     * 应用的密钥。AppKey和AppSecret可在钉钉开发者后台的应用详情页面获取
     */
    private String appsecret;


}
