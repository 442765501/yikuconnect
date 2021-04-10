package com.yiku.common.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/27 16:06
 */
@Data
public class TsumProperties implements Serializable {

    /**
     * T+ appkey
     */
    @Value("${tsum.appkey}")
    private String appkey;

    /**
     * T+ appSecret
     */
    @Value("${tsum.appSecret}")
    private String appSecret;

    /**
     * T+ url
     */
    @Value("${tsum.url}")
    private String url;

    /**
     * T+ username
     */
    @Value("${tsum.name}")
    private String userName;

    /**
     * T+ passWord
     */
    @Value("${tsum.passWord}")
    private String passWord;

    /**
     * T+ account
     */
    @Value("${tsum.account}")
    private String account;

    /**
     * T+ pemFile
     */
    @Value("${tsum.pemFile}")
    private String pemFile;

}
