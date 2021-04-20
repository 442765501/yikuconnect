package com.yiku.service.tsum;

import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.Tsum.TsumParameterReqDTO;
import com.yiku.common.dto.dingding.DingdingFormDTO;

import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 20:20
 */
public interface TsumService {

    /**
     * 登入T+接口
     * @return
     */
    APIResultDTO<String> loginTSum();

    /**
     * 调用t+费用单
     * @param dingdingFormDTOS
     * @return
     */
    APIResultDTO<String>getTsumExpenseItem(List<DingdingFormDTO> dingdingFormDTOS);



}
