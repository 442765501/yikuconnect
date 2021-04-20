package com.yiku.service.ueight.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.constant.enums.dingding.DingdingResEnum;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingExtResDTO;
import com.yiku.common.dto.dingding.DingdingFormDTO;
import com.yiku.common.dto.dingding.DingdingProcessDTO;
import com.yiku.common.dto.ueight.UeightReqDTO;
import com.yiku.common.dto.ueight.UeightReqExtDTO;
import com.yiku.dao.entity.ueight.KKAddUeightBean;
import com.yiku.dao.framework.inf.YiKuDAO;
import com.yiku.service.ueight.UeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/20 11:12
 */
@Service
public class UeightServiceImpl implements UeightService {

    private YiKuDAO<KKAddUeightBean, Integer> kkAddUeightBeanDAO;

    @Resource
    public void setCsexpensevoucherBean(YiKuDAO<KKAddUeightBean, Integer> kkAddUeightBeanDAO) {
        this.kkAddUeightBeanDAO = kkAddUeightBeanDAO;
        this.kkAddUeightBeanDAO.setPerfix(KKAddUeightBean.class.getName());
    }


    @Override
    public APIResultDTO<Integer> addUeight(DingdingProcessDTO dingdingProcessDTO) {
        APIResultDTO<Integer> apiResultDTO = new APIResultDTO<Integer>();

        //解析参数
        APIResultDTO<UeightReqDTO> ueightReqDTOAPIResultDTO = analyticParameters(dingdingProcessDTO);
        UeightReqDTO data = ueightReqDTOAPIResultDTO.getData();
        data.setExpensedetails(JSONObject.toJSONString(data.getUeightReqExtDTOList()));
        data.setIsdelete(FinalDatas.DEFALUT_ZERO);
//        KKAddUeightBean kkAddUeightBean = new KKAddUeightBean();
//        BeanUtils.copyProperties(data, kkAddUeightBean);
        int i = kkAddUeightBeanDAO.executeInsertMethod(data,"insertUeight");
        if (i > 0) {
            apiResultDTO.setFlag(true);
        }
        return apiResultDTO;
    }


    public APIResultDTO<UeightReqDTO> analyticParameters(DingdingProcessDTO dingdingProcessDTO) {
        APIResultDTO<UeightReqDTO> apiResultDTO = new APIResultDTO<UeightReqDTO>();

        UeightReqDTO ueightReqDTO = new UeightReqDTO();
        ueightReqDTO.setCreatetime(dingdingProcessDTO.getCreate_time());
        ueightReqDTO.setTitle(dingdingProcessDTO.getTitle());
        ueightReqDTO.setUserid(dingdingProcessDTO.getOriginator_userid());
        ueightReqDTO.setStatus(FinalDatas.DEFALUT_ZERO.toString());
        List<DingdingFormDTO> form_component_values = dingdingProcessDTO.getForm_component_values();
        for (DingdingFormDTO value : form_component_values) {
            if (DingdingResEnum.USERNAME.getMsg().equals(value.getName())) {
                ueightReqDTO.setUsername(value.getValue());
            }
            if (DingdingResEnum.DEPTNAME.getMsg().equals(value.getName())) {
                ueightReqDTO.setDeptname(value.getValue());
            }
            if (DingdingResEnum.PICTURE.getMsg().equals(value.getName())) {
                ueightReqDTO.setUrl(value.getValue());
            }
            if (DingdingResEnum.EXPENSE_DETAILS.getMsg().equals(value.getName())) {
                JSONArray jsonArray = JSONArray.parseArray(value.getValue());
                List<UeightReqExtDTO> list = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.get(i).toString());
                    String rowValue = jsonObject.getString("rowValue");
                    JSONArray json = JSONArray.parseArray(rowValue);
                    UeightReqExtDTO ueightReqExtDTO = new UeightReqExtDTO();
                    for (int z = 0; z < json.size(); z++) {
                        DingdingExtResDTO dingdingExtResDTO = JSONObject.parseObject(json.get(z).toString(), DingdingExtResDTO.class);
                        if (DingdingResEnum.AMOUNTOF_MONEY.getMsg().equals(dingdingExtResDTO.getLabel())) {
                            ueightReqExtDTO.setExpenseAmount(dingdingExtResDTO.getValue());
                        }
                        if (DingdingResEnum.PURPOSE.getMsg().equals(dingdingExtResDTO.getLabel())) {
                            ueightReqExtDTO.setPurpose(dingdingExtResDTO.getValue());
                        }
                    }
                    list.add(ueightReqExtDTO);
                }
                ueightReqDTO.setUeightReqExtDTOList(list);
            }
        }
        apiResultDTO.setData(ueightReqDTO);

        return apiResultDTO;
    }
}
