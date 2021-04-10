package com.yiku.common.openAPI;

import chanjet.sign.SignatureManage;
import com.yiku.common.config.properties.TsumProperties;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jdiy.json.JsonObject;
import org.jdiy.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 *
 */

@Component
public class OpenAPI {
    public static final String OrgId = "";//云服务id

    public static String Access_Token_str = "";//持久化登录成功后获取的token

    @Resource
    TsumProperties tsumProperties;

//    public static void main(String[] args) throws Exception{
//        //登录
//        String sss = Login(USER, PWD, ZT);
//        System.out.println(sss);
//        //业务
//        String params = "{\"param\": { }" + "}";//��ѯ���
//        String eee = business(params, "inventory/Query");
//        System.out.println(eee);
//    }

    /**
     * @param username �˺�
     * @param password ����
     * @param accNum   ����
     * @return
     * @throws Exception
     */
    public String login(String username, String password, String accNum, String url, String appKey, String appSecret,String pemFile) throws Exception {
        OpenAPI api = new OpenAPI();
        SignatureManage manage = new SignatureManage();
//      PemFile = Fs.getResource("../../").getPath();
//      PemFile = PemFile + "config/cjet_pri.pkcs8";
        String authStr = manage.CreateAuthorizationHeader(appKey, appSecret, OrgId, pemFile, null);
        String accInfo = "{\"userName\":\"" + username + "\",\"password\":\"" + manage.getMD5(password) + "\",\"accNum\":\"" + accNum + "\"}";
        String token = api.getToken(authStr, accInfo, url);
        //从登录成功返回的字符串中解析出Token并持久化，以便后面业务请求调用
        Access_Token_str = getJson(token, "access_token");
        return token;
    }

    /**
     * @param params    json����
     * @param parameter �����ĸ��ӿڣ�inventory/Query��
     * @return
     * @throws Exception
     */
    public String business(String params, String parameter,String url,String appKey,String AppSecret,String pemFile) throws Exception {
        OpenAPI api = new OpenAPI();
        SignatureManage manage = new SignatureManage();
        String authStr1 = manage.CreateAuthorizationHeader(appKey,AppSecret, OrgId, pemFile, Access_Token_str);
        String retValue = api.getData(parameter, authStr1, params,url);
        return retValue;
    }

    /**
     * @param rstr     json�ַ���
     * @param rstrpara json��key
     * @return key��Ӧ��value
     * @throws Exception
     */
    //解析json
    public static String getJson(String rstr, String rstrpara) throws Exception {
        JsonObject jsonobj = (JsonObject) JsonUtil.parse(rstr);
        String jsonstr = "";
        if (jsonobj.get(rstrpara) != null) {
            jsonstr = jsonobj.get(rstrpara).toString();
        }
        return jsonstr;
    }

    //业务的请求
    public String getData(String methodName, String authstr, String paras,String url) throws Exception {
        String retValue = "";
        try {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(url + methodName);
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            method.addRequestHeader("Authorization", authstr);
            method.addParameter("_args", paras);
            client.executeMethod(method);

            retValue = method.getResponseBodyAsString();

            method.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    //登录的请求
    private String getToken(String authStr, String accInfo, String url) throws Exception {
        String jsonstr = "";
        try {
            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(url + "collaborationapp/GetRealNameTPlusToken?IsFree=1");
            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            method.addRequestHeader("Authorization", authStr);
            method.addParameter("_args", accInfo);
            client.executeMethod(method);
            jsonstr = method.getResponseBodyAsString();
            method.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonstr;
    }

}
