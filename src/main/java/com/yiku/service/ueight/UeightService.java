package com.yiku.service.ueight;

import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingProcessDTO;
import com.yiku.common.dto.ueight.UeightReqDTO;
import org.springframework.stereotype.Service;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/20 11:11
 */
public interface UeightService {

    /**
     * 增加U8参数
     * @param dingdingProcessDTO
     * @return
     */
    APIResultDTO<Integer> addUeight(DingdingProcessDTO dingdingProcessDTO);



}
