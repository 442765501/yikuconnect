package com.yiku.service.dingding;

import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingReqDTO;
import com.yiku.common.dto.dingding.DingdingResDTO;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/7 10:06
 */
public interface DingdingService {


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
    APIResultDTO<String> registerDingding(DingdingReqDTO dingdingReqDTO);

    /**
     * 删除钉钉注册接口
     */
    APIResultDTO<DingdingResDTO> deleteDingdingForRegister(DingdingReqDTO dingdingReqDTO);

    /**
     * 查询钉钉注册接口
     */
    APIResultDTO<DingdingResDTO> queryDingdingForRegister(DingdingReqDTO dingdingReqDTO);

    /**
     * 钉钉通讯录人员角色增加接口
     */
    APIResultDTO<String> addUserByRoleId(String userId, String customkey);

    /**
     * 钉钉通讯录人员角色删除接口
     */
    APIResultDTO<String> removeUserByRoleId(String userId, String customKey,String roleIdList);

    //--------------mailList end ------------

    //--------------toExamine start ------------

    /**
     * 获取审批实例详情
     */
    APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId);

    //--------------toExamine end ------------
}
