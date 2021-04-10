package com.yiku.service.dingding;

import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingResDTO;
import com.yiku.common.dto.dingding.DingdingUserDTO;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 16:37
 */
public interface DingdingService {

    /**
     * 获取token
     */
    APIResultDTO<String> getToken();


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
     * 获取审批实例详情
     */
    APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId);

    /**
     * 根据userid获取用户详情
     */
    APIResultDTO<DingdingUserDTO> getUserByUserId(String userId);

    /**
     * 批量增加员工角色
     */
    APIResultDTO<DingdingUserDTO> addUserByCity(String userIds,String roleIds);


}
