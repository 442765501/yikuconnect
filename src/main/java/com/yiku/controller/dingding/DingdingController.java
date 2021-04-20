package com.yiku.controller.dingding;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yiku.common.config.properties.DingdingProperties;
import com.yiku.common.dto.APIResultDTO;
import com.yiku.common.dto.dingding.DingdingCallBackDTO;
import com.yiku.common.util.DingCallbackCryptoUtil;
import com.yiku.common.util.LogUtil;
import com.yiku.common.util.StringUtil;
import com.yiku.controller.BaseController;
import com.yiku.service.dingding.DingdingService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

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
    DingdingService dingdingService;


    @RequestMapping(value = "/callback")
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json,
                                        WebRequest request) {

        // 1. 从http请求中获取加解密参数
        String encryptMsg = json.getString("encrypt");
        String customkey = request.getParameter("customkey");
        if (StringUtil.isBlank(customkey)) {
            customkey = "";
        }
        try {
            // 2. 使用加解密类型
            DingCallbackCryptoUtil callbackCrypto = new DingCallbackCryptoUtil(dingdingProperties.getToken(), dingdingProperties.getEncodingAesKey(), customkey);
            String decryptMsg = callbackCrypto.getDecryptMsg(signature, timestamp, nonce, encryptMsg);

            // 3. 反序列化回调事件json数据

            DingdingCallBackDTO dingdingCallBackDTO = JSONObject.parseObject(decryptMsg, DingdingCallBackDTO.class);


            // 4. 根据EventType分类处理
            if ("check_url".equals(dingdingCallBackDTO.getEventType())) {
                //OA审核费用单回调. 根据processInstanceId调用接口.
            } else if ("bpms_instance_change".equals(dingdingCallBackDTO.getEventType())) {
                dingdingService.getProcessinstance(dingdingCallBackDTO.getProcessInstanceId(),customkey,dingdingCallBackDTO.getType());
                //员工角色信息发生变更。
            } else if ("label_user_change".equals(dingdingCallBackDTO.getEventType())) {
                if ("add".equals(dingdingCallBackDTO.getAction())) {
                    APIResultDTO<String> apiResultDTO = dingdingService.addUserByRoleId(dingdingCallBackDTO.getEventType(), customkey);
                    if (apiResultDTO.isFlag()) {
                        LogUtil.info("{}", "角色添加成功");
                    }
                } else if ("remove".equals(dingdingCallBackDTO.getAction())) {
                    APIResultDTO<String> apiResultDTO = dingdingService.removeUserByRoleId(dingdingCallBackDTO.getEventType(), customkey, dingdingCallBackDTO.getLabelIdList());
                    if (apiResultDTO.isFlag()) {
                        LogUtil.info("{}", "角色删除成功");
                    }
                }
            }
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
