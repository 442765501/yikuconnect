package com.yiku.listener.service.impl;

import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingResDTO;
import com.yiku.listener.service.ApService;
import com.yiku.service.dingding.DingdingService;
import com.yiku.service.dingding.DingdingTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/27 18:29
 */
@Service
public class ApServiceImpl implements ApService {

    @Resource
    DingdingTestService dingdingTestService;

    @Override
    public void start() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.execute(new registerThread());
    }


    private class registerThread extends Thread {
        public registerThread() {
            super();
        }

        //判断是否已经注册接口.如果注册接口则先删除在重新注册 如果没注册则直接注册
        @Override
        public void run() {
            try {
                APIResultDTO<DingdingResDTO> dingdingQueryDTO = dingdingTestService.queryDingdingForRegister();
                DingdingResDTO dingdingQueryResDTO = dingdingQueryDTO.getData();
                //判断是否已经注册
                if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingQueryResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingQueryResDTO.getErrmsg())) {
                    APIResultDTO<DingdingResDTO> dingdingDeleteDTO = dingdingTestService.deleteDingdingForRegister();
                    DingdingResDTO dingdingDeleteResDTO = dingdingDeleteDTO.getData();
                    //判断是否已经删除
                    if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingDeleteResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingDeleteResDTO.getErrmsg())) {
                        dingdingTestService.registerDingding();
                    }
                } else {
                    dingdingTestService.registerDingding();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
