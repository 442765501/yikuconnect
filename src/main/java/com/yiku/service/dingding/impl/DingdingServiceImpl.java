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
import com.yiku.common.constant.enums.dingding.DingdingResEnum;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.*;
import com.yiku.common.util.LogUtil;
import com.yiku.service.dingding.DingdingService;
import com.yiku.service.tsum.TsumService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 16:43
 */
@Service
public class DingdingServiceImpl implements DingdingService {

    @Resource
    DingdingProperties dingdingProperties;

    @Resource
    TsumService tsumService;


    @Override
    public APIResultDTO<String> getToken() {
        APIResultDTO apiResultDTO = new APIResultDTO();
        try {
//            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/gettoken");
//            OapiGettokenRequest request = new OapiGettokenRequest();
//            request.setHttpMethod("GET");
//            request.setAppkey(dingdingProperties.getAccessKey());
//            request.setAppsecret(dingdingProperties.getAccessSecret());
//            OapiGettokenResponse response = client.execute(request);
//            String body = response.getBody();
//            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
//            String access_token = jsonObject.getString("access_token");
//            apiResultDTO.setData(access_token);
            //第三方企业应用
            String access_token = getStringForCityToken(dingdingProperties.getCorpIdForSan(), dingdingProperties.getCustomKey(), dingdingProperties.getCustomSecret());
            apiResultDTO.setData(access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }


    @Override
    public APIResultDTO<String> registerDingding() {
        APIResultDTO<String> tokenResultDTO = getToken();//获取token
        try {
            String data = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/register_call_back");
            OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
            request.setUrl(dingdingProperties.getCallBackUrl());
            request.setAesKey("1XdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NTb");   //随机生成可参考文档 这边我取固定值
            request.setToken("1992287b54c63b48a5cd9c1221827a7b");              //随机生成可参考文档 这边我取固定值
            request.setCallBackTag(Arrays.asList("bpms_instance_change", "user_modify_org", "user_add_org", "label_user_change", "label_conf_add", "label_conf_del", "label_conf_modify", "label_user_change"));
            //回调接口
            OapiCallBackRegisterCallBackResponse response = client.execute(request, data);
            //解析回调参数
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(response.getBody(), DingdingResDTO.class);

            //判断两个都正确则为注册成功
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}", "钉钉接口注册成功");
            }
        } catch (Exception e) {
            LogUtil.error(e, "钉钉接口注册失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public APIResultDTO<DingdingResDTO> deleteDingdingForRegister() {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getToken();//获取token
            String data = tokenResultDTO.getData();
            DingTalkClient deleteClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/delete_call_back");
            OapiCallBackDeleteCallBackRequest req = new OapiCallBackDeleteCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackDeleteCallBackResponse rsp = deleteClient.execute(req, data);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            //判断两个都正确则为删除成功
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}", "钉钉接口删除成功");
                apiResultDTO.setData(dingdingResDTO);
            }
        } catch (Exception e) {
            LogUtil.error(e, "钉钉接口删除失败");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<DingdingResDTO> queryDingdingForRegister() {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getToken();//获取token
            String data = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/get_call_back");
            OapiCallBackGetCallBackRequest req = new OapiCallBackGetCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackGetCallBackResponse rsp = client.execute(req, data);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            LogUtil.info("{}", "钉钉接口查询成功", JSONObject.toJSONString(rsp.getBody()));
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            LogUtil.error(e, "钉钉接口查询失败");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId) {
        try {
            APIResultDTO<String> tokenResultDTO = getToken();//获取token
            String data = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/processinstance/get");
            OapiProcessinstanceGetRequest req = new OapiProcessinstanceGetRequest();
            req.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse rsp = client.execute(req, data);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);

            //调用T+接口,解析返回参数
            String processInstance = dingdingResDTO.getProcess_instance();
            DingdingProcessDTO dingdingProcessDTO = JSONObject.parseObject(processInstance, DingdingProcessDTO.class);
            List<DingdingFormDTO> formComponentValues = dingdingProcessDTO.getForm_component_values();
            tsumService.getTsumExpenseItem(formComponentValues);

        } catch (ApiException e) {
            LogUtil.error(e, "根据processInstanceId调用参数异常");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public APIResultDTO<DingdingUserDTO> getUserByUserId(String userId) {
        APIResultDTO<DingdingUserDTO> apiResultDTO = new APIResultDTO<DingdingUserDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getToken();//获取token
            String data = tokenResultDTO.getData();

            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
            OapiV2UserGetRequest req = new OapiV2UserGetRequest();
            JSONArray jsonArray = JSONArray.parseArray(userId);
            for (int i = 0; i < jsonArray.size(); i++) {
                String newUserId = jsonArray.get(i).toString();
                req.setUserid(newUserId);
                req.setLanguage("zh_CN");
                //获取到第村级用户的信息
                OapiV2UserGetResponse rsp = client.execute(req, data);
                DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
                DingdingUserDTO dingdingUserDTO = JSONObject.parseObject(dingdingResDTO.getResult(), DingdingUserDTO.class);
                //获取市级的用户信息.
                JSONArray userForCity = getUserForCity(req, newUserId);

                if (null == userForCity) {
                    //获取市级token
                    String tokenForCity = getStringForCityToken("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");
                    DingTalkClient dingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/list");
                    OapiRoleListRequest req11 = new OapiRoleListRequest();
                    req11.setSize(20L);
                    req11.setOffset(0L);
                    OapiRoleListResponse rsp11 = dingTalkClient.execute(req11, tokenForCity);
                    DingdingResDTO dingdingResDTO11 = JSONObject.parseObject(rsp11.getBody(), DingdingResDTO.class);
                    String result = dingdingResDTO11.getResult();
                    JSONObject jsonObject1 = JSONObject.parseObject(result);
                    JSONArray jsonArray1 = JSONArray.parseArray(jsonObject1.getString("list"));
                    for (int a = 0; a < jsonArray1.size(); a++) {
                        DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArray1.get(a).toString(), DingdingRoleDTO.class);
                        if (DingdingResEnum.ROLEOFVILLAGEORGANIZATIONS.getMsg().equals(roleDTO.getName())) {
                            JSONArray jsonArray11 = JSONArray.parseArray(roleDTO.getRoles());
                            for (int i1 = 0; i1 < jsonArray11.size(); i1++) {
                                DingdingRoleExtDTO dto = JSONObject.parseObject(jsonArray11.get(i1).toString(), DingdingRoleExtDTO.class);
                                if (DingdingResEnum.SECRETARY.getMsg().equals(dto.getName())) {
                                    APIResultDTO<DingdingUserDTO> apiResultDTO1 = addUserByCity(newUserId, dto.getId());
                                    if (true == apiResultDTO1.isFlag()) {
                                        return apiResultDTO;
                                    }
                                }
                            }
                        }
                    }
                }

                List list = new ArrayList();
                for (int z = 0; z < userForCity.size(); z++) {
                    String userInfoForCity = userForCity.get(z).toString();
                    DingdingRoleDTO dingdingRoleForCity = JSONObject.parseObject(userInfoForCity, DingdingRoleDTO.class);
                    list.add(dingdingRoleForCity.getName());
                }
                JSONArray jsonRole = JSONArray.parseArray(dingdingUserDTO.getRole_list());
                if (null == jsonRole) {
                    JSONArray userForCity1 = getUserForCity(req, newUserId);
                    for (int z = 0; z < userForCity1.size(); z++) {
                        String userInfoForCity = userForCity1.get(z).toString();
                        DingdingRoleDTO dingdingRoleForCity = JSONObject.parseObject(userInfoForCity, DingdingRoleDTO.class);
                        if (DingdingResEnum.SECRETARY.getMsg().equals(dingdingRoleForCity.getName())) {
                            String access_token = getStringForCityToken("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");

                            DingTalkClient client11 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/list");
                            OapiRoleListRequest req11 = new OapiRoleListRequest();
                            req11.setSize(20L);
                            req11.setOffset(0L);
                            OapiRoleListResponse rsp11 = client11.execute(req11, access_token);
                            DingdingResDTO dingdingResDTO11 = JSONObject.parseObject(rsp11.getBody(), DingdingResDTO.class);
                            String result = dingdingResDTO11.getResult();
                            JSONObject jsonObject1 = JSONObject.parseObject(result);
                            JSONArray jsonArray1 = JSONArray.parseArray(jsonObject1.getString("list"));
                            for (int a = 0; a < jsonArray1.size(); a++) {
                                DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArray1.get(a).toString(), DingdingRoleDTO.class);
                                if (DingdingResEnum.ROLEOFVILLAGEORGANIZATIONS.getMsg().equals(roleDTO.getName())) {
                                    JSONArray jsonArray11 = JSONArray.parseArray(roleDTO.getRoles());
                                    for (int i1 = 0; i1 < jsonArray11.size(); i1++) {
                                        DingdingRoleExtDTO dto = JSONObject.parseObject(jsonArray11.get(i1).toString(), DingdingRoleExtDTO.class);
                                        if (DingdingResEnum.SECRETARY.getMsg().equals(dto.getName())) {

                                            DingTalkClient client111 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/removerolesforemps");
                                            OapiRoleRemoverolesforempsRequest req111 = new OapiRoleRemoverolesforempsRequest();
                                            req111.setRoleIds(dto.getId());
                                            req111.setUserIds(newUserId);
                                            OapiRoleRemoverolesforempsResponse rsp1111 = client111.execute(req111, access_token);
                                            System.out.println(rsp1111.getBody() + "222");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (int j = 0; j < jsonRole.size(); j++) {
                    String role = jsonRole.get(j).toString();
                    DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(role, DingdingRoleDTO.class);
                    //如果没有书记那么根据userId.去查市里面是否有书记如果有则删除
                    if (DingdingResEnum.SECRETARY.getMsg().equals(dingdingRoleDTO.getName())) {
                        //如果市级没有包含书记则添加
                        if (!list.contains(dingdingRoleDTO.getName())) {
                            APIResultDTO<DingdingUserDTO> apiResultDTO1 = addUserByCity(newUserId, dingdingRoleDTO.getId());
                            if (true == apiResultDTO1.isFlag()) {
                                return apiResultDTO;
                            }

                        }
                    } else {
                        JSONArray userForCity1 = getUserForCity(req, newUserId);
                        for (int z = 0; z < userForCity1.size(); z++) {
                            String userInfoForCity = userForCity1.get(z).toString();
                            DingdingRoleDTO dingdingRoleForCity = JSONObject.parseObject(userInfoForCity, DingdingRoleDTO.class);
                            if (DingdingResEnum.SECRETARY.getMsg().equals(dingdingRoleForCity.getName())) {
                                String access_token = getStringForCityToken("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");

                                DingTalkClient client11 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/list");
                                OapiRoleListRequest req11 = new OapiRoleListRequest();
                                req11.setSize(20L);
                                req11.setOffset(0L);
                                OapiRoleListResponse rsp11 = client11.execute(req11, access_token);
                                DingdingResDTO dingdingResDTO11 = JSONObject.parseObject(rsp11.getBody(), DingdingResDTO.class);
                                String result = dingdingResDTO11.getResult();
                                JSONObject jsonObject1 = JSONObject.parseObject(result);
                                JSONArray jsonArray1 = JSONArray.parseArray(jsonObject1.getString("list"));
                                for (int a = 0; a < jsonArray1.size(); a++) {
                                    DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArray1.get(a).toString(), DingdingRoleDTO.class);
                                    if (DingdingResEnum.ROLEOFVILLAGEORGANIZATIONS.getMsg().equals(roleDTO.getName())) {
                                        JSONArray jsonArray11 = JSONArray.parseArray(roleDTO.getRoles());
                                        for (int i1 = 0; i1 < jsonArray11.size(); i1++) {
                                            DingdingRoleExtDTO dto = JSONObject.parseObject(jsonArray11.get(i1).toString(), DingdingRoleExtDTO.class);
                                            if (DingdingResEnum.SECRETARY.getMsg().equals(dto.getName())) {
                                                DingTalkClient client111 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/removerolesforemps");
                                                OapiRoleRemoverolesforempsRequest req111 = new OapiRoleRemoverolesforempsRequest();
                                                req111.setRoleIds(dto.getId());
                                                req111.setUserIds(newUserId);
                                                OapiRoleRemoverolesforempsResponse rsp1111 = client111.execute(req111, access_token);
                                                System.out.println(rsp1111.getBody() + "222");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LogUtil.error(e, "");
        }
        return null;
    }

    private String getStringForCityToken(String ding7dbf9b6aa3d3ac37a1320dcb25e91351, String suitel9vjbdnhkhiuogrh, String s) throws ApiException {
        DefaultDingTalkClient defaultDingTalkClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/service/get_corp_token");
        OapiServiceGetCorpTokenRequest tokenRequest = new OapiServiceGetCorpTokenRequest();
        tokenRequest.setAuthCorpid(ding7dbf9b6aa3d3ac37a1320dcb25e91351);
        OapiServiceGetCorpTokenResponse execute = defaultDingTalkClient.execute(tokenRequest, suitel9vjbdnhkhiuogrh, s, "suiteTicket");
        String body = execute.getBody();
        JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
        //市的token信息
        return jsonObject.getString("access_token");
    }

    @Override
    public APIResultDTO<DingdingUserDTO> addUserByCity(String userIds, String roleIds) {
        APIResultDTO<DingdingUserDTO> apiResultDTO = new APIResultDTO<DingdingUserDTO>();

        try {
            String access_token = getStringForCityToken("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");

            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/list");
            OapiRoleListRequest req = new OapiRoleListRequest();
            req.setSize(20L);
            req.setOffset(0L);
            OapiRoleListResponse rsp = client.execute(req, access_token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            String result = dingdingResDTO.getResult();
            JSONObject jsonObject1 = JSONObject.parseObject(result);
            JSONArray jsonArray1 = JSONArray.parseArray(jsonObject1.getString("list"));
            for (int i = 0; i < jsonArray1.size(); i++) {
                DingdingRoleDTO roleDTO = JSONObject.parseObject(jsonArray1.get(i).toString(), DingdingRoleDTO.class);
                if (DingdingResEnum.ROLEOFVILLAGEORGANIZATIONS.getMsg().equals(roleDTO.getName())) {
                    JSONArray jsonArray = JSONArray.parseArray(roleDTO.getRoles());
                    for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                        DingdingRoleExtDTO dto = JSONObject.parseObject(jsonArray.get(i1).toString(), DingdingRoleExtDTO.class);
                        if (DingdingResEnum.SECRETARY.getMsg().equals(dto.getName())) {
                            DingTalkClient client11 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/addrolesforemps");
                            OapiRoleAddrolesforempsRequest req11 = new OapiRoleAddrolesforempsRequest();
                            req11.setRoleIds(dto.getId());
                            req11.setUserIds(userIds);
                            OapiRoleAddrolesforempsResponse rsp11 = client11.execute(req11, access_token);
                            System.out.println(rsp11.getBody() + "111");
                            DingdingResDTO dingdingResDTO1 = JSONObject.parseObject(rsp11.getBody(), DingdingResDTO.class);
                            if (0 == dingdingResDTO1.getErrcode()) {
                                DingTalkClient client33 = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/scope/update");
                                OapiRoleScopeUpdateRequest req33 = new OapiRoleScopeUpdateRequest();
                                req33.setUserid(userIds);
                                req33.setRoleId(Long.valueOf(dto.getId()));
                                req33.setDeptIds("477217286");
                                OapiRoleScopeUpdateResponse rsp33 = client33.execute(req33, access_token);
                                System.out.println(rsp33.getBody());
                                apiResultDTO.setFlag(true);
                            }
                        }
                    }
                }
            }

        } catch (ApiException e) {
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    //获取市信息
    private JSONArray getUserForCity(OapiV2UserGetRequest req, String newUserId) throws ApiException {
        String access_token = getStringForCityToken("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");
        DingTalkClient dingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
        OapiV2UserGetRequest request = new OapiV2UserGetRequest();
        request.setUserid(newUserId);
        request.setLanguage("zh_CN");
        OapiV2UserGetResponse oapiV2UserGetResponse = dingTalkClient.execute(req, access_token);
        DingdingResDTO resDTO = JSONObject.parseObject(oapiV2UserGetResponse.getBody(), DingdingResDTO.class);
        //市的用户信息
        DingdingUserDTO userDTO = JSONObject.parseObject(resDTO.getResult(), DingdingUserDTO.class);
        JSONArray jsonRoleForCity = JSONArray.parseArray(userDTO.getRole_list());
        return jsonRoleForCity;
    }
}
