package com.yiku.service.tsum.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yiku.common.config.properties.TsumProperties;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.constant.enums.dingding.DingdingResEnum;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.Tsum.TsumParameterReqDTO;
import com.yiku.common.dto.dingding.DingdingExtResDTO;
import com.yiku.common.dto.dingding.DingdingFormDTO;
import com.yiku.common.dto.dingding.DingdingRowValueResDTO;
import com.yiku.common.dto.expenseList.*;
import com.yiku.common.openAPI.OpenAPI;
import com.yiku.common.util.LogUtil;
import com.yiku.dao.entity.aabusiType.AABusiTypeBean;
import com.yiku.dao.entity.aaexentity.AAExpenseItemBean;
import com.yiku.dao.entity.aapartner.AAPartnerBean;
import com.yiku.dao.entity.aasettleStyle.AASettleStyleBean;
import com.yiku.dao.entity.eapEnumItem.EapEnumItemBean;
import com.yiku.dao.framework.inf.YiKuDAO;
import com.yiku.service.tsum.TsumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 20:21
 */
@Service
public class TsumServiceImpl implements TsumService {


    @Resource
    TsumProperties tsumProperties;

    private YiKuDAO<AABusiTypeBean, Integer> aaBusiTypeBeanDAO;

    private YiKuDAO<EapEnumItemBean, Integer> eapEnumItemBeanDAO;

    private YiKuDAO<AAPartnerBean, Integer> aaPartnerBeanDAO;

    private YiKuDAO<AASettleStyleBean, Integer> aaSettleStyleDAO;

    private YiKuDAO<AAExpenseItemBean, Integer> aaExpenseItemBeanDAO;

    @Resource
    public void setCsexpensevoucherBean(YiKuDAO<AAExpenseItemBean, Integer> aaExpenseItemBeanDAO) {
        this.aaExpenseItemBeanDAO = aaExpenseItemBeanDAO;
        this.aaExpenseItemBeanDAO.setPerfix(AAExpenseItemBean.class.getName());
    }


    @Resource
    public void setEapEnumItemBeanDAO(YiKuDAO<EapEnumItemBean, Integer> eapEnumItemBeanDAO) {
        this.eapEnumItemBeanDAO = eapEnumItemBeanDAO;
        this.eapEnumItemBeanDAO.setPerfix(EapEnumItemBean.class.getName());
    }

    @Resource
    public void setAaBusiTypeBeanDAO(YiKuDAO<AABusiTypeBean, Integer> aaBusiTypeBeanDAO) {
        this.aaBusiTypeBeanDAO = aaBusiTypeBeanDAO;
        this.aaBusiTypeBeanDAO.setPerfix(AABusiTypeBean.class.getName());
    }

    @Resource
    public void setAaPartnerBeanDAO(YiKuDAO<AAPartnerBean, Integer> aaPartnerBeanDAO) {
        this.aaPartnerBeanDAO = aaPartnerBeanDAO;
        this.aaPartnerBeanDAO.setPerfix(AAPartnerBean.class.getName());
    }

    @Resource
    public void setAaSettleStyleDAO(YiKuDAO<AASettleStyleBean, Integer> aaSettleStyleDAO) {
        this.aaSettleStyleDAO = aaSettleStyleDAO;
        this.aaSettleStyleDAO.setPerfix(AASettleStyleBean.class.getName());
    }


    @Override
    public APIResultDTO<String> loginTSum() {
        APIResultDTO<String> apiResultDTO = new APIResultDTO<String>();
        OpenAPI openAPI = new OpenAPI();
        try {
            String login = openAPI.login(tsumProperties.getUserName(),
                    tsumProperties.getPassWord(), tsumProperties.getAccount(),
                    tsumProperties.getUrl(), tsumProperties.getAppkey(),
                    tsumProperties.getAppSecret(), tsumProperties.getPemFile());
            JSONObject jsonObject = JSONObject.parseObject(login);
            String result = jsonObject.getString("result");
            if (FinalDatas.TRUE.equalsIgnoreCase(result)) {
                LogUtil.info("????????????", login);
            } else {
                LogUtil.error("????????????", login);
            }
            apiResultDTO.setErrorMessage(result);
        } catch (Exception e) {
            LogUtil.error(e, "????????????");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<String> getTsumExpenseItem(List<DingdingFormDTO> dingdingFormDTOS) {
        APIResultDTO<String> apiResultDTO = new APIResultDTO<String>();
        OpenAPI openAPI = new OpenAPI();

        ExpenseListDTO dto = new ExpenseListDTO();
        ExpenselistQueryDTO expenselistQueryDTO = new ExpenselistQueryDTO();

        //?????????????????????T+???????????????????????????
        expenselistQueryDTO.setExternalCode(String.valueOf(System.currentTimeMillis()));
        for (DingdingFormDTO dingdingFormDTO : dingdingFormDTOS) {

            //????????????????????????????????????
            if (DingdingResEnum.DATE.getMsg().equals(dingdingFormDTO.getName())) {
                expenselistQueryDTO.setVoucherDate(dingdingFormDTO.getValue());
            }
            //T+???????????????????????????????????????code??????????????????????????????
            if (DingdingResEnum.BUSINESS_TYPE.getMsg().equals(dingdingFormDTO.getName())) {
                BusinessType businessType = new BusinessType();
                //??????????????????.
                DingdingExtResDTO dingdingExtResDTO = JSONObject.parseObject(dingdingFormDTO.getExt_value(), DingdingExtResDTO.class);
                AABusiTypeBean aaBusiTypeBean = this.aaBusiTypeBeanDAO.selectByPrimaryKey(Integer.valueOf(dingdingExtResDTO.getKey()));
                businessType.setCode(aaBusiTypeBean.getCode());
                expenselistQueryDTO.setBusinessType(businessType);
            }
            //T+???????????????????????????????????????code??????????????????????????????
            if (DingdingResEnum.BILL_TYPE.getMsg().equals(dingdingFormDTO.getName())) {
                BillType billType = new BillType();
                //??????????????????.
                DingdingExtResDTO dingdingExtResDTO = JSONObject.parseObject(dingdingFormDTO.getExt_value(), DingdingExtResDTO.class);
                EapEnumItemBean eapEnumItemBean = this.eapEnumItemBeanDAO.selectByPrimaryKey(Integer.valueOf(dingdingExtResDTO.getKey()));
                billType.setCode(eapEnumItemBean.getCode());
                expenselistQueryDTO.setBillType(billType);
            }
            //T+??????????????????????????????????????????code,?????????????????????????????? BusinessType:37/???(??????) 38/???(??????)
            if (DingdingResEnum.CURRENT_UNIT.getMsg().equals(dingdingFormDTO.getName())) {
                if ("37".equals(expenselistQueryDTO.getBusinessType().getCode())) {
                    Partner partner = new Partner();
                    //??????????????????.
                    DingdingExtResDTO dingdingExtResDTO = JSONObject.parseObject(dingdingFormDTO.getExt_value(), DingdingExtResDTO.class);
                    AAPartnerBean aaPartnerBean = this.aaPartnerBeanDAO.selectByPrimaryKey(Integer.valueOf(dingdingExtResDTO.getKey()));
                    partner.setCode(aaPartnerBean.getCode());
                    expenselistQueryDTO.setPartner(partner);
                } else {
                    expenselistQueryDTO.setPartner(new Partner());
                }
            }
            //?????????????????????????????????????????????SettleStyle???code??????????????????????????????BankAccount???Name?????????????????????OrigAmount:????????????
            if (DingdingResEnum.DETAILSOF_CASHSETTLEMENT.getMsg().equals(dingdingFormDTO.getName())) {
                JSONArray jsonArray = JSONArray.parseArray(dingdingFormDTO.getValue());

                if (jsonArray.size() > 0) {
                    List<MultiSettles> multiSettles = new ArrayList<MultiSettles>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        String array = jsonArray.get(i).toString();

                        JSONObject jsonObject = JSONObject.parseObject(array);
                        String rowValue = jsonObject.getString("rowValue");
                        JSONArray array1 = JSONArray.parseArray(rowValue);

                        MultiSettles multiSettle = new MultiSettles();
                        for (int j = 0; j < array1.size(); j++) {
                            DingdingRowValueResDTO dingdingRowValueResDTO = JSONObject.parseObject(array1.get(j).toString(), DingdingRowValueResDTO.class);
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.TITLE_OF_ACCOUNT.getMsg())) {
                                BankAccount bankaccount = new BankAccount();
                                bankaccount.setName(dingdingRowValueResDTO.getValue());
                                multiSettle.setBankaccount(bankaccount);
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.SETTLEMENT_METHOD.getMsg())) {
                                Settlestyle settlestyle = new Settlestyle();
                                AASettleStyleBean aaSettleStyleBean = aaSettleStyleDAO.selectByPrimaryKey(Integer.valueOf(dingdingRowValueResDTO.getExtendValue().getKey()));
                                settlestyle.setCode(aaSettleStyleBean.getCode());
                                multiSettle.setSettlestyle(settlestyle);
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.INCOME_AMOUNT.getMsg())) {
                                multiSettle.setOrigAmount(Double.valueOf(dingdingRowValueResDTO.getValue()));
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.BILL_NO.getMsg())) {
                                multiSettle.setBillNo(dingdingRowValueResDTO.getValue());
                            }
                        }
                        multiSettles.add(multiSettle);
                    }
                    expenselistQueryDTO.setMultiSettles(multiSettles);
                }
            }
            //?????????????????????????????????expenseitem?????????????????????????????????expenseitem???code????????????????????????expenseitem???name????????????????????????
            // TaxRate????????????OrigAmount????????????OrigTax????????????OrigTax??????????????????
            if (DingdingResEnum.DETAILSOF_INCOMESTATEMENT.getMsg().equals(dingdingFormDTO.getName())) {
                JSONArray jsonArray = JSONArray.parseArray(dingdingFormDTO.getValue());
                if (jsonArray.size() > 0) {
                    List<Details> details = new ArrayList<Details>();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        String array = jsonArray.get(i).toString();
                        JSONObject jsonObject = JSONObject.parseObject(array);
                        String rowValue = jsonObject.getString("rowValue");
                        JSONArray array1 = JSONArray.parseArray(rowValue);
                        Details details1 = new Details();
                        for (int j = 0; j < array1.size(); j++) {
                            DingdingRowValueResDTO dingdingRowValueResDTO = JSONObject.parseObject(array1.get(j).toString(), DingdingRowValueResDTO.class);
//                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.SUBJECT.getMsg())) {
//                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.COST.getMsg())) {
                                Expenseitem expenseitem = new Expenseitem();
                                AAExpenseItemBean aaExpenseItemBean = this.aaExpenseItemBeanDAO.selectByPrimaryKey(Integer.valueOf(dingdingRowValueResDTO.getExtendValue().getKey()));
                                expenseitem.setCode(aaExpenseItemBean.getCode());
                                details1.setExpenseitem(expenseitem);
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.TAX_RATE.getMsg())) {
                                details1.setTaxRate(Double.valueOf(dingdingRowValueResDTO.getValue()));
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.AMOUNTOF_MONEY.getMsg())) {
                                details1.setOrigAmount(Double.valueOf(dingdingRowValueResDTO.getValue()));
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.TAX_AMOUNT.getMsg())) {
                                details1.setOrigTax(Double.valueOf(dingdingRowValueResDTO.getValue()));
                            }
                            if (dingdingRowValueResDTO.getLabel().equals(DingdingResEnum.AMOUNT_INCLUDING_TAX.getMsg())) {
                                details1.setOrigTaxAmount(Double.valueOf(dingdingRowValueResDTO.getValue()));
                            }
                        }
                        details.add(details1);
                    }
                    expenselistQueryDTO.setDetails(details);
                }
            }
        }
        dto.setDto(expenselistQueryDTO);
        try {
            APIResultDTO<String> apiResultDTO1 = loginTSum();
            if (FinalDatas.TRUE.equalsIgnoreCase(apiResultDTO1.getErrorMessage())) {
                String business = openAPI.business(JSON.toJSONString(dto), "expenseVoucher/CreateExpenseVoucher", tsumProperties.getUrl(), tsumProperties.getAppkey(), tsumProperties.getAppSecret(), tsumProperties.getPemFile());

                //??????T+????????????
                TsumParameterReqDTO tsumParameterReqDTO = new TsumParameterReqDTO();
                tsumParameterReqDTO.setVoucherCode(JSONObject.parseObject(business).getString("Code"));
                tsumParameterReqDTO.setBizCode("CS02");
                tsumParameterReqDTO.setAction("1");
                tsumParameterReqDTO.setOpinion("");
                tsumParameterReqDTO.setControlCode("");
                tsumParameterReqDTO.setSelNextNodeId("");
                parameterToExamine(tsumParameterReqDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public APIResultDTO<String> parameterToExamine(TsumParameterReqDTO tsumParameterReqDTO) {
        OpenAPI openAPI = new OpenAPI();
        try {
            String examineForTsum = openAPI.business(JSON.toJSONString(tsumParameterReqDTO),"WorkFlow/VoucherControlAuditByCode", tsumProperties.getUrl(), tsumProperties.getAppkey(), tsumProperties.getAppSecret(), tsumProperties.getPemFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
