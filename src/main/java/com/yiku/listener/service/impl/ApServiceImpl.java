package com.yiku.listener.service.impl;

import com.yiku.common.constant.FinalDatas;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingReqDTO;
import com.yiku.common.dto.dingding.DingdingResDTO;
import com.yiku.dao.entity.aabankAccount.AABankAccountBean;
import com.yiku.dao.entity.dingding.ConfigurationBean;
import com.yiku.dao.framework.inf.YiKuDAO;
import com.yiku.listener.service.ApService;
import com.yiku.service.dingding.DingdingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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
    DingdingService dingdingService;

    private YiKuDAO<ConfigurationBean, Integer> configurationBeanDAO;


    @Resource
    public void setAaBankAccountBeanDAO(YiKuDAO<ConfigurationBean, Integer> configurationBeanDAO) {
        this.configurationBeanDAO = configurationBeanDAO;
        this.configurationBeanDAO.setPerfix(ConfigurationBean.class.getName());
    }


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
                List<DingdingReqDTO> queryConfigurationList = configurationBeanDAO.executeListMethod("", "queryConfigurationList", DingdingReqDTO.class);
                for (DingdingReqDTO dingdingReqDTO : queryConfigurationList) {
                    APIResultDTO<DingdingResDTO> dingdingQueryDTO = dingdingService.queryDingdingForRegister(dingdingReqDTO);
                    DingdingResDTO dingdingQueryResDTO = dingdingQueryDTO.getData();
                    //判断是否已经注册
                    if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingQueryResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingQueryResDTO.getErrmsg())) {
                        APIResultDTO<DingdingResDTO> dingdingDeleteDTO = dingdingService.deleteDingdingForRegister(dingdingReqDTO);
                        DingdingResDTO dingdingDeleteResDTO = dingdingDeleteDTO.getData();
                        //判断是否已经删除
                        if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingDeleteResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingDeleteResDTO.getErrmsg())) {
                            dingdingService.registerDingding(dingdingReqDTO);
                        }
                    } else {
                        dingdingService.registerDingding(dingdingReqDTO);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
