package com.yiku.service.dingding;

import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingResDTO;
import com.yiku.common.dto.dingding.DingdingUserDTO;

import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/7 10:06
 */
public interface DingdingTestService {


    //--------------token start-------------

    /**
     * 获取token
     */
    APIResultDTO<String> getToken();

    /**
     * 第三方企业应用token
     */
    APIResultDTO<String> getTokenForThird(String corpId, String key, String secret);

    //--------------token end-------------

    //--------------mailList start ------------

    /**
     * 钉钉注册接口
     */
    APIResultDTO<String> registerDingding();

    /**
     * 删除钉钉注册接口
     */
    APIResultDTO<DingdingResDTO> deleteDingdingForRegister();

    /**
     * 查询钉钉注册接口
     */
    APIResultDTO<DingdingResDTO> queryDingdingForRegister();

    /**
     * 实现钉钉通讯录增加删除接口
     */
    APIResultDTO<String> realizationMail(String userId);

    //--------------mailList end ------------

    //--------------toExamine start ------------
    /**
     * 获取审批实例详情
     */
    APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId);

    //--------------toExamine end ------------
}
