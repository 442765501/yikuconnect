package com.yiku.apiservic.connect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.response.OapiCallBackDeleteCallBackResponse;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.taobao.api.ApiException;
import com.yiku.BaseTest;
import com.yiku.common.config.properties.DingdingProperties;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.dto.dingding.DingdingResDTO;
import com.yiku.common.util.LogUtil;
import org.jdiy.json.JsonObject;
import org.jdiy.json.JsonUtil;
import org.junit.Test;
import org.springframework.util.CollectionUtils;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.*;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/27 15:11
 */
public class DingdingTest extends BaseTest {

    public static String TOKEN = "";

    public static final String ACCESSKEY = "ding860nlbhwqeo8oapr";

    public static final String ACCESSSECRET = "-ayNNyNyGkKbdTXD8N8NObEqFTiKcXQixpP85DUoCDyEmuQ_22EuCFeen4GmYVhA";

    @Resource
    DingdingProperties dingdingProperties;

    @Test
    public void getToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/service/get_corp_token");
        OapiServiceGetCorpTokenRequest req = new OapiServiceGetCorpTokenRequest();
        req.setAuthCorpid("dingbbc42a35888ac2b9f5bf40eda33b7ba0");
        String body = null;
        try {
            OapiServiceGetCorpTokenResponse execute = client.execute(req, "suiteeezj31r6zxcbma5v", "KkukYRubONjwTit7cnhMx_M-5uSu3tLA7wdD-QipfSW8fVRjW70c1MDRpoHp7446", "suiteTicket");
            body = execute.getBody();
            JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
            TOKEN = jsonObject.getString("access_token");
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void register() {
        try {
            getToken();
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/register_call_back");
            OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
            request.setUrl("http://125.113.133.121:2020/dingding/callback");
            request.setAesKey("1XdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NTb");
            request.setToken("1992287b54c63b48a5cd9c1221827a7b");
            request.setCallBackTag(Arrays.asList("bpms_instance_change"));
            OapiCallBackRegisterCallBackResponse response = client.execute(request, TOKEN);
            System.out.println(response.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    //解析json
    @Test
    public void getJson() throws Exception {
//        DefaultDingTalkClient defaultDingTalkClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/service/get_corp_token");
//        OapiServiceGetCorpTokenRequest tokenRequest = new OapiServiceGetCorpTokenRequest();
//        tokenRequest.setAuthCorpid("ding7dbf9b6aa3d3ac37a1320dcb25e91351");
//        OapiServiceGetCorpTokenResponse execute = defaultDingTalkClient.execute(tokenRequest, "suitel9vjbdnhkhiuogrh", "zMf0CBsTztDWH2X3VBxyojdvpkCKwIjr3cIEFQbvu472lPEon3FRgWx-BzR3oKqa", "suiteTicket");
//        String body = execute.getBody();
//        JSONObject jsonObject = (JSONObject) JSONObject.parse(body);
//        //市的token信息
//        String access_token = jsonObject.getString("access_token");
//
//        DingTalkClient deleteClient = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/delete_call_back");
//        OapiCallBackDeleteCallBackRequest req33 = new OapiCallBackDeleteCallBackRequest();
//        req33.setHttpMethod("GET");
//        OapiCallBackDeleteCallBackResponse rsp44 = deleteClient.execute(req33, access_token);
//
//        DingTalkClient client11 = new DefaultDingTalkClient(dingdingProperties.getUrl() + "/call_back/register_call_back");
//        OapiCallBackRegisterCallBackRequest request = new OapiCallBackRegisterCallBackRequest();
//        request.setUrl("http://125.113.133.121:2020/connectApi_Web_exploded/dingding/callback");
//        request.setAesKey("1XdlcnR5dWlvcGFzZGZnaGprbHp4Y3Zibm0xMjM0NTb");   //随机生成可参考文档 这边我取固定值
//        request.setToken("1992287b54c63b48a5cd9c1221827a7b");              //随机生成可参考文档 这边我取固定值
//        request.setCallBackTag(Arrays.asList("bpms_instance_change", "user_modify_org", "user_add_org", "label_user_change", "label_conf_add", "label_conf_del", "label_conf_modify"));
//        //回调接口
//        OapiCallBackRegisterCallBackResponse response = client11.execute(request, access_token);
//        //解析回调参数
//        DingdingResDTO dto = JSONObject.parseObject(response.getBody(), DingdingResDTO.class);
//
//        //判断两个都正确则为注册成功
//        if (FinalDatas.DEFALUT_ZERO == Integer.valueOf(dto.getErrcode()) && FinalDatas.SUCCESS_LOWER.equalsIgnoreCase(dto.getErrmsg())) {
//            LogUtil.info("{}", "钉钉接口注册成功");
//        }
//    }
    }

    @Test
    public void test(){


    }
}
