package com.yiku.service.dingding.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.yiku.common.config.properties.DingdingProperties;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.*;
import com.yiku.common.util.LogUtil;
import com.yiku.common.util.StringUtil;
import com.yiku.dao.entity.dingding.ConfigurationBean;
import com.yiku.dao.framework.inf.YiKuDAO;
import com.yiku.service.dingding.DingdingService;
import com.yiku.service.tsum.TsumService;
import com.yiku.service.ueight.UeightService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/7 9:05
 */
@Service
public class DingdingServiceImpl implements DingdingService {

    @Resource
    DingdingProperties dingdingProperties;

    @Resource
    TsumService tsumService;

    @Resource
    UeightService ueightService;

    private YiKuDAO<ConfigurationBean, Integer> configurationBeanDAO;


    @Resource
    public void setAaBankAccountBeanDAO(YiKuDAO<ConfigurationBean, Integer> configurationBeanDAO) {
        this.configurationBeanDAO = configurationBeanDAO;
        this.configurationBeanDAO.setPerfix(ConfigurationBean.class.getName());
    }

    @Override
    public APIResultDTO<String> getToken() {
        APIResultDTO apiResultDTO = new APIResultDTO();
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/gettoken");
            OapiGettokenRequest request = new OapiGettokenRequest();
            request.setHttpMethod("GET");
            request.setAppkey(dingdingProperties.getAccessKey());
            request.setAppsecret(dingdingProperties.getAccessSecret());
            OapiGettokenResponse response = client.execute(request);
            String body = response.getBody();
            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
            String access_token = jsonObject.getString("access_token");
            apiResultDTO.setData(access_token);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<String> getTokenForThird(String corpId, String key, String secret) {
        APIResultDTO<String> apiResultDTO = new APIResultDTO<String>();
        try {
            //????????????token???????????????????????????. ?????????????????????corpId,key secret?????????
            DefaultDingTalkClient defaultDingTalkClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/service/get_corp_token");
            OapiServiceGetCorpTokenRequest tokenRequest = new OapiServiceGetCorpTokenRequest();
            tokenRequest.setAuthCorpid(corpId);
            OapiServiceGetCorpTokenResponse execute = defaultDingTalkClient.execute(tokenRequest, key, secret, "test");
            String body = execute.getBody();
            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
            String tokenForThird = jsonObject.getString("access_token");
            apiResultDTO.setData(tokenForThird);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<String> registerDingding(DingdingReqDTO dingdingReqDTO) {
        //??????????????????????????????token
        try {
            APIResultDTO<String> tokenForThird = getTokenForThird(dingdingReqDTO.getCorpId(), dingdingReqDTO.getCustomKey(), dingdingReqDTO.getSecret());
            String token = tokenForThird.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/register_call_back");
            OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
            request.setUrl(dingdingProperties.getCallBackUrl() + "?customkey=" + dingdingReqDTO.getCustomKey());//????????????
            request.setAesKey("1XdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NTb");   //??????????????????????????? ?????????????????????
            request.setToken("1992287b54c63b48a5cd9c1221827a7b");              //??????????????????????????? ?????????????????????
            //??????????????????
            request.setCallBackTag(Arrays.asList("bpms_instance_change", "user_modify_org",
                    "user_add_org", "label_user_change", "label_conf_add", "label_conf_del",
                    "label_conf_modify", "label_user_change"));
            //????????????
            OapiCallBackRegisterCallBackResponse response = client.execute(request, token);
            //??????????????????
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(response.getBody(), DingdingResDTO.class);
            //???????????????????????????????????????
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}" + dingdingResDTO.getErrmsg(), "????????????????????????");
            }
        } catch (ApiException e) {
            LogUtil.error(e, "????????????????????????");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public APIResultDTO<DingdingResDTO> deleteDingdingForRegister(DingdingReqDTO dingdingReqDTO) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getTokenForThird(dingdingReqDTO.getCorpId(), dingdingReqDTO.getCustomKey(), dingdingReqDTO.getSecret());//???????????????token
            String tokenForThird = tokenResultDTO.getData();
            DingTalkClient deleteClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/delete_call_back");
            OapiCallBackDeleteCallBackRequest req = new OapiCallBackDeleteCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackDeleteCallBackResponse rsp = deleteClient.execute(req, tokenForThird);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            //???????????????????????????????????????
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}", "??????????????????????????????");
                apiResultDTO.setData(dingdingResDTO);
            }
        } catch (Exception e) {
            LogUtil.error(e, "??????????????????????????????");
            e.printStackTrace();
        }
        return apiResultDTO;

    }

    @Override
    public APIResultDTO<DingdingResDTO> queryDingdingForRegister(DingdingReqDTO dingdingReqDTO) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getTokenForThird(dingdingReqDTO.getCorpId(), dingdingReqDTO.getCustomKey(), dingdingReqDTO.getSecret());//???????????????token
            String data = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/get_call_back");
            OapiCallBackGetCallBackRequest req = new OapiCallBackGetCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackGetCallBackResponse rsp = client.execute(req, data);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            LogUtil.info("????????????????????????????????????", rsp.getBody());
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            LogUtil.error(e, "????????????????????????????????????");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId,String customkey,String type) {
        try {
            DingdingReqDTO dingdingReqDTO= configurationBeanDAO.executeSelectOneMethod(customkey, "queryConfigurationListByKey", DingdingReqDTO.class);
            APIResultDTO<String> tokenResultDTO = getTokenForThird(dingdingReqDTO.getCorpId(),dingdingReqDTO.getCustomKey(),dingdingReqDTO.getSecret());//??????token
            String token = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/processinstance/get");
            OapiProcessinstanceGetRequest req = new OapiProcessinstanceGetRequest();
            req.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);

            //??????T+??????,??????????????????
            String processInstance = dingdingResDTO.getProcess_instance();
            DingdingProcessDTO dingdingProcessDTO = JSONObject.parseObject(processInstance, DingdingProcessDTO.class);
            if (null != dingdingProcessDTO) {
                if("start".equals(type)){
                    //??????U8????????????
                    APIResultDTO<Integer> integerAPIResultDTO = ueightService.addUeight(dingdingProcessDTO);
                    if(integerAPIResultDTO.isFlag()){
                        LogUtil.info("{}", "????????????");
                    }
                }
                List<DingdingFormDTO> formComponentValues = dingdingProcessDTO.getForm_component_values();
                tsumService.getTsumExpenseItem(formComponentValues);
            }
        } catch (ApiException e) {
            LogUtil.error(e, "??????processInstanceId??????????????????");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public APIResultDTO<String> addUserByRoleId(String userId, String customKey) {
        APIResultDTO<String> stringAPIResultDTO = new APIResultDTO<String>();
        if (StringUtil.isBlank(customKey)) {
            return stringAPIResultDTO;
        }
        //??????key?????????????????????
        DingdingReqDTO dingdingReqDTOForVillage = configurationBeanDAO.executeSelectOneMethod(customKey, "queryConfigurationListByKey", DingdingReqDTO.class);
        //??????key????????????????????????Id
        DingdingReqDTO dingdingReqDTOForCity = configurationBeanDAO.executeSelectOneMethod(customKey, "queryConfigurationItemByKey", DingdingReqDTO.class);
        //????????????Id???????????????????????????
        ConfigurationBean configurationBeanForCity = configurationBeanDAO.selectByPrimaryKey(dingdingReqDTOForCity.getParentId());

        //????????????????????????token
        //??????token
        APIResultDTO<String> tokenForThirdVillage = getTokenForThird(dingdingReqDTOForVillage.getCorpId(), dingdingReqDTOForVillage.getCustomKey(), dingdingReqDTOForVillage.getSecret());
        String tokenForVillage = tokenForThirdVillage.getData();

        //??????token
        APIResultDTO<String> tokenForThirdCity = getTokenForThird(configurationBeanForCity.getCorpid(), configurationBeanForCity.getCustomkey(), configurationBeanForCity.getSecret());
        String tokenForCity = tokenForThirdCity.getData();

        //????????????????????????
        APIResultDTO<List<DingdingUserDTO>> userForVillage = getUserByUserId(tokenForVillage, userId);
        List<DingdingUserDTO> userDTOListForVillage = userForVillage.getData();

        //?????????????????????
        APIResultDTO<List<DingdingUserDTO>> userForCity = getUserByUserId(tokenForCity, userId);
        List<DingdingUserDTO> userDTOListForCity = userForCity.getData();

        //???????????????????????????
        for (DingdingUserDTO dingdingUserDTOForVillage : userDTOListForVillage) {


            //???????????????????????????
            for (DingdingUserDTO dingdingUserDTOForCity : userDTOListForCity) {

                //--------------------????????????-----------------
                //??????????????????????????????id
                List<String> roleIdForCity = new ArrayList<String>();
                //--------------------------------------------

                //????????????????????????????????????
                if (dingdingUserDTOForVillage.getUserid().equals(dingdingUserDTOForCity.getUserid())) {
                    JSONArray jsonArrayForVillage = JSONArray.parseArray(dingdingUserDTOForVillage.getRole_list());
                    //???????????????????????????????????????????????????(????????????????????????????????????)
                    if (StringUtil.isBlank(dingdingUserDTOForCity.getRole_list())) {
                        for (int i = 0; i < jsonArrayForVillage.size(); i++) {
                            DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(jsonArrayForVillage.get(i).toString(), DingdingRoleDTO.class);
                            String returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleDTO.getName());
                            APIResultDTO<DingdingResDTO> apiResultDTO = addUserByCity(tokenForCity, dingdingUserDTOForCity.getUserid(), returnRoleIdForCity);
                            //????????????????????????????????????????????????
                            if (FinalDatas.DEFALUT_ZERO == apiResultDTO.getData().getErrcode()) {
                                //?????????????????????????????????????????????.  ????????????????????????????????????.???????????????????????????????????????????????????id
                                String deptIdList = dingdingUserDTOForCity.getDept_id_list();
                                JSONArray deptIdToArray = JSONArray.parseArray(deptIdList);

                                boolean flag = false;
                                for (int q = 0; q < deptIdToArray.size(); q++) {
                                    if (false == flag) {
                                        //??????id
                                        String deptId = deptIdToArray.get(q).toString();
                                        //????????????????????????
                                        String organizationForVillage = getOrganization(tokenForVillage);
                                        //??????????????????id
                                        String deptInfoByDeptId = getDeptInfoByDeptId(tokenForCity, deptId, organizationForVillage);
                                        if (StringUtil.isNotBlank(deptInfoByDeptId)) {
                                            for (int e = 0; e < jsonArrayForVillage.size(); e++) {
                                                DingdingRoleDTO dingdingRoleDTOForVillage = JSONObject.parseObject(jsonArrayForVillage.get(e).toString(), DingdingRoleDTO.class);
                                                //???????????????????????????????????????????????????????????????id
                                                String returnRoleIdToCity = returnRoleIdForCity(tokenForCity, dingdingRoleDTOForVillage.getName());
                                                APIResultDTO<DingdingResDTO> dtoapiResultDTO = updateRoleScope(tokenForCity, dingdingUserDTOForVillage.getUserid(), returnRoleIdToCity, deptInfoByDeptId);
                                                if (FinalDatas.DEFALUT_ZERO == dtoapiResultDTO.getData().getErrcode()) {
                                                    stringAPIResultDTO.setFlag(true);
                                                    flag=true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    } else {
                        //??????????????????????????????
                        for (int i = 0; i < jsonArrayForVillage.size(); i++) {
                            DingdingRoleDTO dingdingRoleDTOForVillage = JSONObject.parseObject(jsonArrayForVillage.get(i).toString(), DingdingRoleDTO.class);
                            //??????????????????????????????????????????????????????????????????Id.
                            String returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleDTOForVillage.getName());
                            //????????????.??????????????????????????????????????????????????????????????????
                            if (StringUtils.isNotBlank(returnRoleIdForCity)) {
                                String roleListForCity = dingdingUserDTOForCity.getRole_list();
                                JSONArray arrayForCityRole = JSONArray.parseArray(roleListForCity);
                                for (int z = 0; z < arrayForCityRole.size(); z++) {
                                    DingdingRoleDTO dingdingRoleDTOForCity = JSONObject.parseObject(arrayForCityRole.get(z).toString(), DingdingRoleDTO.class);
                                    //????????????????????????????????????????????????????????????????????????????????????????????????????????????
                                    if (returnRoleIdForCity.equals(dingdingRoleDTOForCity.getId())) {
                                        break;
                                    } else {
                                        roleIdForCity.add(returnRoleIdForCity);
                                    }
                                }
                            }
                        }

                    }

                    if (!CollectionUtils.isEmpty(roleIdForCity)) {
                        for (String roleIdToCity : roleIdForCity) {
                            APIResultDTO<DingdingResDTO> apiResultDTO = addUserByCity(tokenForCity, dingdingUserDTOForCity.getUserid(), roleIdToCity);
                            if (FinalDatas.DEFALUT_ZERO == apiResultDTO.getData().getErrcode()) {
                                //?????????????????????????????????????????????.  ????????????????????????????????????.???????????????????????????????????????????????????id
                                String deptIdList = dingdingUserDTOForCity.getDept_id_list();
                                JSONArray deptIdToArray = JSONArray.parseArray(deptIdList);
                                for (int q = 0; q < deptIdToArray.size(); q++) {
                                    //??????id
                                    String deptId = deptIdToArray.get(q).toString();
                                    //????????????????????????
                                    String organizationForVillage = getOrganization(tokenForVillage);
                                    //??????????????????id
                                    String deptInfoByDeptId = getDeptInfoByDeptId(tokenForCity, deptId, organizationForVillage);
                                    if (StringUtil.isNotBlank(deptInfoByDeptId)) {
                                        for (int i = 0; i < jsonArrayForVillage.size(); i++) {
                                            DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(jsonArrayForVillage.get(i).toString(), DingdingRoleDTO.class);
                                            //???????????????????????????????????????????????????????????????id
                                            String returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleDTO.getName());
                                            APIResultDTO<DingdingResDTO> dtoapiResultDTO = updateRoleScope(tokenForCity, dingdingUserDTOForVillage.getUserid(), returnRoleIdForCity, deptInfoByDeptId);
                                            if (FinalDatas.DEFALUT_ZERO == dtoapiResultDTO.getData().getErrcode()) {
                                                stringAPIResultDTO.setFlag(true);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return stringAPIResultDTO;
    }

    @Override
    public APIResultDTO<String> removeUserByRoleId(String userId, String customKey, String roleIdList) {

        APIResultDTO<String> stringAPIResultDTO = new APIResultDTO<String>();
        if (StringUtil.isBlank(customKey)) {
            return stringAPIResultDTO;
        }

        //??????key?????????????????????
        DingdingReqDTO dingdingReqDTOForVillage = configurationBeanDAO.executeSelectOneMethod(customKey, "queryConfigurationListByKey", DingdingReqDTO.class);
        //??????key????????????????????????Id
        DingdingReqDTO dingdingReqDTOForCity = configurationBeanDAO.executeSelectOneMethod(customKey, "queryConfigurationItemByKey", DingdingReqDTO.class);
        //????????????Id???????????????????????????
        ConfigurationBean configurationBeanForCity = configurationBeanDAO.selectByPrimaryKey(dingdingReqDTOForCity.getParentId());

        //????????????????????????token ??????????????????????????????.
        //??????token
        APIResultDTO<String> tokenForThirdVillage = getTokenForThird(dingdingReqDTOForVillage.getCorpId(), dingdingReqDTOForVillage.getCustomKey(), dingdingReqDTOForVillage.getSecret());
        String tokenForVillage = tokenForThirdVillage.getData();

        //??????token
        APIResultDTO<String> tokenForThirdCity = getTokenForThird(configurationBeanForCity.getCorpid(), configurationBeanForCity.getCustomkey(), configurationBeanForCity.getSecret());
        String tokenForCity = tokenForThirdCity.getData();

        //????????????????????????
        APIResultDTO<List<DingdingUserDTO>> userForVillage = getUserByUserId(tokenForVillage, userId);
        List<DingdingUserDTO> userDTOListForVillage = userForVillage.getData();

        //?????????????????????
        APIResultDTO<List<DingdingUserDTO>> userForCity = getUserByUserId(tokenForCity, userId);
        List<DingdingUserDTO> userDTOListForCity = userForCity.getData();

        //???????????????????????????(??????????????????)
        for (DingdingUserDTO dingdingUserDTO : userDTOListForCity) {

            //--------------------????????????-----------------
            //??????????????????????????????id
            List<String> roleIdForCity = new ArrayList<String>();
            //--------------------------------------------

            JSONArray jsonArrayForCity = JSONArray.parseArray(dingdingUserDTO.getRole_list());
            //?????????????????????????????????????????????????????????????????????
            if (null == jsonArrayForCity) {
                continue;
            } else {
                JSONArray jsonArray = JSONArray.parseArray(roleIdList);
                for (int i = 0; i < jsonArrayForCity.size(); i++) {
                    DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(jsonArrayForCity.get(i).toString(), DingdingRoleDTO.class);
                    for (int z = 0; z < jsonArray.size(); z++) {
                        String roleId = jsonArray.get(z).toString();
                        //???????????????id????????????????????????????????????.
                        String roleNameForVillage = returnRoleNameForVillage(tokenForVillage, roleId);
                        //????????????????????????????????????????????????. ???????????????????????????????????????. ???????????????.(???????????????????????????)
                        if (dingdingRoleDTO.getName().equals(roleNameForVillage)) {
                            APIResultDTO<DingdingResDTO> apiResultDTO = removeUserForCity(tokenForCity, dingdingUserDTO.getUserid(), dingdingRoleDTO.getId());
                            if (FinalDatas.DEFALUT_ZERO == apiResultDTO.getData().getErrcode()) {
                                stringAPIResultDTO.setFlag(true);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        return stringAPIResultDTO;
    }

    //????????????id??????????????????
    public APIResultDTO<List<DingdingUserDTO>> getUserByUserId(String token, String userId) {
        //???????????????token???????????????????????????
        APIResultDTO<List<DingdingUserDTO>> apiResultDTO = new APIResultDTO<List<DingdingUserDTO>>();
        List<DingdingUserDTO> dingdingUserDTOList = new ArrayList<DingdingUserDTO>();
        try {
            DingTalkClient dingTalkClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/v2/user/get");
            OapiV2UserGetRequest request = new OapiV2UserGetRequest();
            JSONArray jsonArray = JSONArray.parseArray(userId);
            for (int i = 0; i < jsonArray.size(); i++) {
                String userForId = jsonArray.get(i).toString();
                request.setUserid(userForId);
                request.setLanguage("zh_CN");
                OapiV2UserGetResponse rsp = dingTalkClient.execute(request, token);
                DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
                DingdingUserDTO dingdingUserDTO = JSONObject.parseObject(dingdingResDTO.getResult(), DingdingUserDTO.class);
                dingdingUserDTOList.add(dingdingUserDTO);
            }
            apiResultDTO.setData(dingdingUserDTOList);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    //??????????????????
    public APIResultDTO<DingdingResDTO> addUserByCity(String token, String userIds, String roleIds) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/role/addrolesforemps");
            OapiRoleAddrolesforempsRequest req = new OapiRoleAddrolesforempsRequest();
            req.setRoleIds(roleIds);
            req.setUserIds(userIds);
            OapiRoleAddrolesforempsResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            LogUtil.info(e, "????????????????????????");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    //??????????????????
    public APIResultDTO<DingdingResDTO> removeUserForCity(String tokenForCity, String UserId, String roleId) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/role/removerolesforemps");
            OapiRoleRemoverolesforempsRequest req = new OapiRoleRemoverolesforempsRequest();
            req.setRoleIds(roleId);
            req.setUserIds(UserId);
            OapiRoleRemoverolesforempsResponse rsp = client.execute(req, tokenForCity);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    //??????????????????????????????
    public APIResultDTO<DingdingResDTO> updateRoleScope(String token, String userId, String roleId, String deptId) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/role/scope/update");
            OapiRoleScopeUpdateRequest req = new OapiRoleScopeUpdateRequest();
            req.setUserid(userId);
            req.setRoleId(Long.valueOf(roleId));
            req.setDeptIds(deptId);
            OapiRoleScopeUpdateResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }


    //??????????????????
    public String getOrganization(String token) {
        String regionName = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/industry/organization/get");
            OapiIndustryOrganizationGetRequest req = new OapiIndustryOrganizationGetRequest();
            OapiIndustryOrganizationGetResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            DingdingOrganizationResDTO dingdingOrganizationResDTO = JSONObject.parseObject(dingdingResDTO.getResult(), DingdingOrganizationResDTO.class);
            String regionLocation = dingdingOrganizationResDTO.getRegion_location();
            regionName = regionLocation.substring(regionLocation.lastIndexOf("_") + 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return regionName;
    }

    //?????????????????????????????????
    public String getDeptInfoByDeptId(String token, String deptId, String organizationForVillage) {
        String deptIdForCity = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/v2/department/listsub");
            OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
            req.setDeptId(Long.valueOf(deptId));
            req.setLanguage("zh_CN");
            OapiV2DepartmentListsubResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            JSONArray jsonArray = JSONArray.parseArray(dingdingResDTO.getResult());
            for (int i = 0; i < jsonArray.size(); i++) {
                DingdingDepartmentResDTO dingdingDepartmentResDTO = JSONObject.parseObject(jsonArray.get(i).toString(), DingdingDepartmentResDTO.class);
                if (organizationForVillage.equals(dingdingDepartmentResDTO.getName())) {
                    deptIdForCity = dingdingDepartmentResDTO.getDept_id();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return deptIdForCity;
    }

    //????????????id
    public String returnRoleIdForCity(String token, String roleName) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/role/list");
            OapiRoleListRequest req = new OapiRoleListRequest();
            req.setSize(20L);
            req.setOffset(0L);
            OapiRoleListResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            String result = dingdingResDTO.getResult();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArrayForGoup = JSONArray.parseArray(jsonObject.getString("list"));
            for (int i = 0; i < jsonArrayForGoup.size(); i++) {
                DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArrayForGoup.get(i).toString(), DingdingRoleDTO.class);
                JSONArray jsonArrayForRole = JSONArray.parseArray(roleDTO.getRoles());
                for (int j = 0; j < jsonArrayForRole.size(); j++) {
                    DingdingRoleExtDTO roleExtDTO = JSONObject.parseObject(jsonArrayForRole.get(j).toString(), DingdingRoleExtDTO.class);
                    if (roleName.equals(roleExtDTO.getName())) {
                        return roleExtDTO.getId();
                    }
                }
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    //??????????????????
    public String returnRoleNameForVillage(String token, String roleId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/role/list");
            OapiRoleListRequest req = new OapiRoleListRequest();
            req.setSize(20L);
            req.setOffset(0L);
            OapiRoleListResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            String result = dingdingResDTO.getResult();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONArray jsonArrayForGoup = JSONArray.parseArray(jsonObject.getString("list"));
            for (int i = 0; i < jsonArrayForGoup.size(); i++) {
                DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArrayForGoup.get(i).toString(), DingdingRoleDTO.class);
                JSONArray jsonArrayForRole = JSONArray.parseArray(roleDTO.getRoles());
                for (int j = 0; j < jsonArrayForRole.size(); j++) {
                    DingdingRoleExtDTO roleExtDTO = JSONObject.parseObject(jsonArrayForRole.get(j).toString(), DingdingRoleExtDTO.class);
                    if (roleId.equals(roleExtDTO.getId())) {
                        return roleExtDTO.getName();
                    }
                }
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
