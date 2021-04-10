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
     * dingding.corpId
     */
    @Value("${dingding.corpId}")
    private String corpId;

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

    /**
     * dingding.corpIdForSan
     */
    @Value("${dingding.corpIdForSan}")
    private String corpIdForSan;

    /**
     * dingding.corpIdForSan
     */
    @Value("${dingding.customKey}")
    private String customKey;

    /**
     * dingding.corpIdForSan
     */
    @Value("${dingding.customSecret}")
    private String customSecret;


}
