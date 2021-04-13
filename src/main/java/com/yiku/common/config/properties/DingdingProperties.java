package com.yiku.common.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 9:47
 */
@Data
public class DingdingProperties {

    /**
     * dingding.token
     */
    @Value("${dingding.token}")
    private String token;

    /**
     * dingding. encodingAesKey
     */
    @Value("${dingding.encodingAesKey}")
    private String encodingAesKey;

    /**
     * dingding.url
     */
    @Value("${dingding.url}")
    private String url;

    /**
     * dingding.accesskey
     */
    @Value("${dingding.accesskey}")
    private String accessKey;
    /**
     * dingding.accesssecret
     */
    @Value("${dingding.accesssecret}")
    private String accessSecret;

    /**
     * dingding.callBackUrl
     */
    @Value("${dingding.callBackUrl}")
    private String callBackUrl;



}
