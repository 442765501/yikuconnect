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
import com.yiku.service.dingding.DingdingTestService;
import com.yiku.service.tsum.TsumService;
import org.springframework.stereotype.Service;

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
public class DingdingTestImpl implements DingdingTestService {

    @Resource
    DingdingProperties dingdingProperties;

    @Resource
    TsumService tsumService;


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
            //第三方的token后续要从数据库获取. 因为每个架构的corpId,key secret都不同
            DefaultDingTalkClient defaultDingTalkClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/service/get_corp_token");
            OapiServiceGetCorpTokenRequest tokenRequest = new OapiServiceGetCorpTokenRequest();
            tokenRequest.setAuthCorpid(corpId);
            OapiServiceGetCorpTokenResponse execute = defaultDingTalkClient.execute(tokenRequest, key, secret, "suiteTicket");
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
    public APIResultDTO<String> registerDingding() {
        //首先获取该组织架构的token
        try {
            APIResultDTO<String> tokenForThird = getTokenForThird("ding3b10878e36ef20304ac5d6980864d335", "suiteudmiazeigivw1y4t", "Uo6kN5ACevDGg_RgaYkvfDlgnlI-58YdFq8an2EXfPba8svHhFDh6r1-oBrxLek4");
            String token = tokenForThird.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/register_call_back");
            OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
            request.setUrl(dingdingProperties.getCallBackUrl());//回调地址
            request.setAesKey("1XdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NTb");   //随机生成可参考文档 这边我取固定值
            request.setToken("1992287b54c63b48a5cd9c1221827a7b");              //随机生成可参考文档 这边我取固定值
            //事件回调参数
            request.setCallBackTag(Arrays.asList("bpms_instance_change", "user_modify_org",
                    "user_add_org", "label_user_change", "label_conf_add", "label_conf_del",
                    "label_conf_modify", "label_user_change"));
            //回调接口
            OapiCallBackRegisterCallBackResponse response = client.execute(request, token);
            //解析回调参数
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(response.getBody(), DingdingResDTO.class);
            //判断两个都正确则为注册成功
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}" + dingdingResDTO.getErrmsg(), "钉钉接口注册成功");
            }
        } catch (ApiException e) {
            LogUtil.error(e, "钉钉接口注册失败");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public APIResultDTO<DingdingResDTO> deleteDingdingForRegister() {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getTokenForThird("ding3b10878e36ef20304ac5d6980864d335", "suiteudmiazeigivw1y4t", "Uo6kN5ACevDGg_RgaYkvfDlgnlI-58YdFq8an2EXfPba8svHhFDh6r1-oBrxLek4");//获取第三方token
            String tokenForThird = tokenResultDTO.getData();
            DingTalkClient deleteClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/delete_call_back");
            OapiCallBackDeleteCallBackRequest req = new OapiCallBackDeleteCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackDeleteCallBackResponse rsp = deleteClient.execute(req, tokenForThird);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            //判断两个都正确则为删除成功
            if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dingdingResDTO.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dingdingResDTO.getErrmsg())) {
                LogUtil.info("{}", "钉钉注册接口删除成功");
                apiResultDTO.setData(dingdingResDTO);
            }
        } catch (Exception e) {
            LogUtil.error(e, "钉钉注册接口删除失败");
            e.printStackTrace();
        }
        return apiResultDTO;

    }

    @Override
    public APIResultDTO<DingdingResDTO> queryDingdingForRegister() {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            APIResultDTO<String> tokenResultDTO = getTokenForThird("ding3b10878e36ef20304ac5d6980864d335", "suiteudmiazeigivw1y4t", "Uo6kN5ACevDGg_RgaYkvfDlgnlI-58YdFq8an2EXfPba8svHhFDh6r1-oBrxLek4");//获取第三方token
            String data = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/get_call_back");
            OapiCallBackGetCallBackRequest req = new OapiCallBackGetCallBackRequest();
            req.setHttpMethod("GET");
            OapiCallBackGetCallBackResponse rsp = client.execute(req, data);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            LogUtil.info("{}", "钉钉注册回调接口查询成功");
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            LogUtil.error(e, "钉钉注册回调接口查询失败");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    @Override
    public APIResultDTO<DingdingResDTO> getProcessinstance(String processInstanceId) {
        try {
            APIResultDTO<String> tokenResultDTO = getToken();//获取token
            String token = tokenResultDTO.getData();
            DingTalkClient client = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/topapi/processinstance/get");
            OapiProcessinstanceGetRequest req = new OapiProcessinstanceGetRequest();
            req.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse rsp = client.execute(req, token);
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
    public APIResultDTO<String> realizationMail(String userId) {
        //第一步获取村级的token 后期需要从数据库里取.
        //村的token
        APIResultDTO<String> tokenForThirdVillage = getTokenForThird("ding3b10878e36ef20304ac5d6980864d335", "suiteudmiazeigivw1y4t", "Uo6kN5ACevDGg_RgaYkvfDlgnlI-58YdFq8an2EXfPba8svHhFDh6r1-oBrxLek4");
        String tokenForVillage = tokenForThirdVillage.getData();
        //市的token
        APIResultDTO<String> tokenForThirdCity = getTokenForThird("ding7dbf9b6aa3d3ac37a1320dcb25e91351", "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa");
        String tokenForCity = tokenForThirdCity.getData();

        //获取村用户的信息
        APIResultDTO<List<DingdingUserDTO>> userForVillage = getUserByUserId(tokenForVillage, userId);
        List<DingdingUserDTO> userDTOListForVillage = userForVillage.getData();
        //获取市用户信息
        APIResultDTO<List<DingdingUserDTO>> userForCity = getUserByUserId(tokenForCity, userId);
        List<DingdingUserDTO> userDTOListForCity = userForCity.getData();


        //第一步循环是将村用户的角色id添加到list里面去
        for (DingdingUserDTO dingdingUserForVillage : userDTOListForVillage) {

            //--------------------集合封装-----------------
            //用来存储村里面的角色id
            List<String> roleIdForVillage = new ArrayList<String>();
            //用来存储市里面的角色id
            List<String> roleIdForCity = new ArrayList<String>();
            //--------------------------------------------

            //循环市判断村和市的用户是否为同一个如果是则进行进一步逻辑.
            for (DingdingUserDTO dingdingUserForCity : userDTOListForCity) {
                if (dingdingUserForVillage.getUserid().equals(dingdingUserForCity.getUserid())) {
                    String roleListForCity = dingdingUserForCity.getRole_list();
                    JSONArray jsonArrayForVillage = JSONArray.parseArray(dingdingUserForVillage.getRole_list());
                    //如果下面判断成立.说明该用户在市里一个角色都没有 则将该角色增加到市里面去.反之说明该用户是在市里存在其他角色.那么就判断新增的角色是否存在如果存在则增加
                    //为什么要判断为空.主要是如果该用户在市里面没有角色那么就会报空指针.所以要判断两边.后面也要先从前面判断开始
                    if (null != jsonArrayForVillage) {
                        if (null == roleListForCity || ("").equals(roleListForCity)) {
                            for (int z = 0; z < jsonArrayForVillage.size(); z++) {
                                DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(jsonArrayForVillage.get(z).toString(), DingdingRoleDTO.class);
                                String returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleDTO.getName());
                                APIResultDTO<DingdingResDTO> apiResultDTO = addUserByCity(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity);
                                if (0 == apiResultDTO.getData().getErrcode()) {
                                    //添加成功后给该角色赋予管理权限.  先获取到村的父级组织架构.在根据组织架构去市里面匹配到对应的id
                                    String deptIdList = dingdingUserForCity.getDept_id_list();
                                    JSONArray deptIdToArray = JSONArray.parseArray(deptIdList);
                                    for (int i = 0; i < deptIdToArray.size(); i++) {
                                        String deptId = deptIdToArray.get(i).toString();
                                        String organizationForVillage = getOrganization(tokenForVillage);
                                        String deptInfoByDeptId = getDeptInfoByDeptId(tokenForCity, deptId, organizationForVillage);

                                        if (StringUtil.isNotBlank(deptInfoByDeptId)) {
                                            APIResultDTO<DingdingResDTO> dtoapiResultDTO = updateRoleScope(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity, deptInfoByDeptId);
                                            if (0 == dtoapiResultDTO.getData().getErrcode()) {
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        } else {
                            //这里的目的是将用户在市里面的id存在集合里面去
                            JSONArray jsonArrayForCity = JSONArray.parseArray(roleListForCity);
                            for (int i = 0; i < jsonArrayForCity.size(); i++) {
                                DingdingRoleDTO dingdingRoleDTO = JSONObject.parseObject(jsonArrayForCity.get(i).toString(), DingdingRoleDTO.class);
                                roleIdForCity.add(dingdingRoleDTO.getName());

                            }
                            //这一步循环是判断村里面的角色id是否存在于市里面如果不存在则集合中添加用户id以便后续处理(这边只针对一个角色多个用户添加不支持一个用户添加多个角色)
                            String returnRoleIdForCity = null;
                            for (int i = 0; i < jsonArrayForVillage.size(); i++) {
                                DingdingRoleDTO dingdingRoleForVillage = JSONObject.parseObject(jsonArrayForVillage.get(i).toString(), DingdingRoleDTO.class);
                                roleIdForVillage.add(dingdingRoleForVillage.getName());
                                //这边是赋值.意思就是市里面是否包含该村的角色如果没有则赋值以便后续增加
                                if (!roleIdForCity.contains(dingdingRoleForVillage.getName())) {
                                    returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleForVillage.getName());
                                    if (StringUtil.isNotBlank(returnRoleIdForCity)) {
                                        break;
                                    }
                                    //如果村里面没有该角色则市里面有则删除该市对应的用户角色.
                                }
                            }
                            if (!StringUtil.isEmpty(returnRoleIdForCity)) {
                                APIResultDTO<DingdingResDTO> apiResultDTO = addUserByCity(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity);
                                if (0 == apiResultDTO.getData().getErrcode()) {
                                    //添加成功后给该角色赋予管理权限.  先获取到村的父级组织架构.在根据组织架构去市里面匹配到对应的id
                                    String deptIdList = dingdingUserForCity.getDept_id_list();
                                    JSONArray deptIdToArray = JSONArray.parseArray(deptIdList);
                                    for (int i = 0; i < deptIdToArray.size(); i++) {
                                        String deptId = deptIdToArray.get(i).toString();
                                        String organizationForVillage = getOrganization(tokenForVillage);
                                        String deptInfoByDeptId = getDeptInfoByDeptId(tokenForCity, deptId, organizationForVillage);

                                        if (StringUtil.isNotBlank(deptInfoByDeptId)) {
                                            APIResultDTO<DingdingResDTO> dtoapiResultDTO = updateRoleScope(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity, deptInfoByDeptId);
                                            if (0 == dtoapiResultDTO.getData().getErrcode()) {
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            } else {
                                if (roleIdForVillage.containsAll(roleIdForCity)) {
                                    break;
                                }
                                for (String listRoleForCity : roleIdForCity) {
                                    for (String listRoleForVillage : roleIdForVillage) {
                                        if (!listRoleForCity.equals(listRoleForVillage)) {
                                            returnRoleIdForCity = returnRoleIdForCity(tokenForCity, listRoleForCity);
                                            APIResultDTO<DingdingResDTO> apiResultDTO = removeUserForCity(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity);
                                            if (0 == apiResultDTO.getData().getErrcode()) {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        String returnRoleIdForCity = null;
                        JSONArray jsonArrayForCity = JSONArray.parseArray(roleListForCity);
                        for (int i = 0; i < jsonArrayForCity.size(); i++) {
                            DingdingRoleDTO dingdingRoleForCity = JSONObject.parseObject(jsonArrayForCity.get(i).toString(), DingdingRoleDTO.class);
                            returnRoleIdForCity = returnRoleIdForCity(tokenForCity, dingdingRoleForCity.getName());
                            APIResultDTO<DingdingResDTO> apiResultDTO = removeUserForCity(tokenForCity, dingdingUserForCity.getUserid(), returnRoleIdForCity);
                            if (0 == apiResultDTO.getData().getErrcode()) {
                                break;
                            }
                        }
                    }
                }

            }
        }
        return null;
    }

    //根据用户id查询用户信息
    public APIResultDTO<List<DingdingUserDTO>> getUserByUserId(String token, String userId) {
        //根据不同的token获取不同的组织架构
        APIResultDTO<List<DingdingUserDTO>> apiResultDTO = new APIResultDTO<List<DingdingUserDTO>>();
        List<DingdingUserDTO> dingdingUserDTOList = new ArrayList<DingdingUserDTO>();
        try {
            DingTalkClient dingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
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

    //添加用户信息
    public APIResultDTO<DingdingResDTO> addUserByCity(String token, String userIds, String roleIds) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/addrolesforemps");
            OapiRoleAddrolesforempsRequest req = new OapiRoleAddrolesforempsRequest();
            req.setRoleIds(roleIds);
            req.setUserIds(userIds);
            OapiRoleAddrolesforempsResponse rsp = client.execute(req, token);
            DingdingResDTO dingdingResDTO = JSONObject.parseObject(rsp.getBody(), DingdingResDTO.class);
            apiResultDTO.setData(dingdingResDTO);
        } catch (Exception e) {
            LogUtil.info(e, "增加员工角色异常");
            e.printStackTrace();
        }
        return apiResultDTO;
    }

    //删除用户信息
    public APIResultDTO<DingdingResDTO> removeUserForCity(String tokenForCity, String UserId, String roleId) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/removerolesforemps");
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

    //设置角色成员管理范围
    public APIResultDTO<DingdingResDTO> updateRoleScope(String token, String userId, String roleId, String deptId) {
        APIResultDTO<DingdingResDTO> apiResultDTO = new APIResultDTO<DingdingResDTO>();
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/scope/update");
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


    //获取企业名字
    public String getOrganization(String token) {
        String regionName = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/industry/organization/get");
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

    //获取到市里面的部门列表
    public String getDeptInfoByDeptId(String token, String deptId, String organizationForVillage) {
        String deptIdForCity = null;
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
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

    //返回角色id  //这边可以优化.
    public String returnRoleIdForCity(String token, String roleName) {

        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/role/list");
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
}
