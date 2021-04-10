package com.yiku.controller.dingding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiku.common.config.properties.DingdingProperties;
import com.yiku.common.util.DingCallbackCryptoUtil;
import com.yiku.controller.BaseController;
import com.yiku.service.dingding.DingdingService;
import com.yiku.service.dingding.DingdingTestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/29 13:30
 */
@RestController
@RequestMapping(value = "/dingding")
public class DingdingController extends BaseController {

    @Resource
    DingdingProperties dingdingProperties;


    @Resource
    DingdingTestService dingdingTestService;


    @RequestMapping(value = "/callback")
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json) {

        // 1. 从http请求中获取加解密参数
        String encryptMsg = json.getString("encrypt");
        try {
            // 2. 使用加解密类型
            DingCallbackCryptoUtil callbackCrypto = new DingCallbackCryptoUtil(dingdingProperties.getToken(), dingdingProperties.getEncodingAesKey(), dingdingProperties.getCustomKey());
            String decryptMsg = callbackCrypto.getDecryptMsg(signature, timestamp, nonce, encryptMsg);

            // 3. 反序列化回调事件json数据
            JSONObject eventJson = JSON.parseObject(decryptMsg);
            String eventType = eventJson.getString("EventType");

            // 4. 根据EventType分类处理
            if ("check_url".equals(eventType)) {
                //OA审核费用单回调. 根据processInstanceId调用接口.
            } else if ("bpms_instance_change".equals(eventType)) {
                dingdingTestService.getProcessinstance(eventJson.getString("processInstanceId"));
                //员工角色信息发生变更。
            } else if ("label_user_change".equals(eventType)) {
                dingdingTestService.realizationMail(eventJson.getString("UserIdList"));
            }
            //通讯录用户更改。
//            else if ("user_modify_org".equals(eventType)) {
//                dingdingService.getUserByUserId(eventJson.getString("UserId"));
//            }

            // 5. 返回success的加密数据
            Map<String, String> successMap = null;
            successMap = callbackCrypto.getEncryptedMap("success");
            return successMap;
        } catch (DingCallbackCryptoUtil.DingTalkEncryptException e) {
            e.printStackTrace();
        }
        return null;
    }
}
