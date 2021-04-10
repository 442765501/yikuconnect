//package com.yiku.apiservic.connect;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.yiku.BaseTest;
//import com.yiku.common.config.properties.DingdingProperties;
//import com.yiku.common.config.properties.TsumProperties;
//import com.yiku.common.dto.dingding.DingdingRowValueResDTO;
//import com.yiku.common.dto.expenseList.*;
//import com.yiku.common.openAPI.OpenAPI;
//import com.yiku.common.util.DateUtil;
//import com.yiku.common.util.MD5Utils;
//import com.yiku.common.util.RedisUtil;
//import com.yiku.service.expenseList.AAExpenseListService;
//import com.yiku.service.tsum.TsumService;
//import org.jdiy.json.JsonArray;
//import org.jdiy.json.JsonObject;
//import org.jdiy.json.JsonUtil;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class MyselfConnectTest extends BaseTest {
//
//
//    @Resource
//    AAExpenseListService aaExpenseListService;
//
//    @Resource
//    TsumService tsumService;
//
//    @Resource
//    TsumProperties tsumProperties;
//
////    @Autowired
////    RedisTemplate redisTemplate;
////    @Autowired
////    RedisUtil redisUtil;
//
//    @Resource
//    DingdingProperties dingdingProperties;
//
//    public static String Access_Token_str = "";//持久化登录成功后获取的token
//
//
//    @Test
//    public void getConnectId() {
//        String jsonStr = "";
//
//
////
//
////        try {
////            openAPI api = new openAPI();
////            jsonStr = api.Login(user, pwd, account);
////            jsonStr = api.getData("expenseVoucher/CreateExpenseVoucher", JSONObject.toJSONString(dto));
////            System.out.println(jsonStr);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//    }
//
////    @Test
////    public void getToken() throws Exception {
////        String jsonstr = "";
////        String path = "";
////        PostMethod method = null;
////        try {
////            SignatureManage manage = new SignatureManage();
////            path = Fs.getResource("../../").getPath();
////            path = path + "config/cjet_pri.pkcs8";
////            Security.addProvider(new BouncyCastleProvider());
////            String authStr = manage.CreateAuthorizationHeader(tsumProperties.getAppkey(), tsumProperties.getAppSecret(), "", path, null);
////
////            String accInfo = "{\"userName\":\"" + tsumProperties.getUserName() + "\",\"password\":\"" + manage.getMD5(tsumProperties.getPassWord()) + "\",\"accNum\":\"" + tsumProperties.getAccount() + "\"}";
////            HttpClient client = new HttpClient();
////            method = new PostMethod(tsumProperties.getUrl() + "collaborationapp/GetRealNameTPlusToken?IsFree=1");
////            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
////            method.addRequestHeader("Authorization", authStr);
////            method.addParameter("_args", accInfo);
////
////            client.executeMethod(method);
////            jsonstr = method.getResponseBodyAsString();
////            String access_token = getJson(jsonstr, "access_token");
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            method.releaseConnection();
////        }
////    }
//
////    @Test
////    public void login() {
////        String path = "";
////        String jsonstr = "";
////        PostMethod method = null;
////        try {
////            String token = getToken();
////            SignatureManage manage = new SignatureManage();
////            path = Fs.getResource("../../").getPath();
////            path = path + "config/cjet_pri.pkcs8";
////            Security.addProvider(new BouncyCastleProvider());
////            String authStr = manage.CreateAuthorizationHeader(appKey, appSecret, "", path, token);
////            String accInfo = "{\"userName\":\"" + user + "\",\"password\":\"" + manage.getMD5(pwd) + "\",\"accNum\":\"" + account + "\"}";
////            HttpClient client = new HttpClient();
////            method = new PostMethod(URL + "expenseVoucher/CreateExpenseVoucher");
////            method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
////            method.addRequestHeader("Authorization", authStr);
////            method.addParameter("_args", accInfo);
////            client.executeMethod(method);
////            jsonstr = method.getResponseBodyAsString();
////            System.out.println(jsonstr);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//
//    //解析json
//    public static String getJson(String rstr, String rstrpara) throws Exception {
//        JsonObject jsonobj = (JsonObject) JsonUtil.parse(rstr);
//        String jsonstr = "";
//        if (jsonobj.get(rstrpara) != null) {
//            jsonstr = jsonobj.get(rstrpara).toString();
//        }
//        return jsonstr;
//    }
//
//    @Test
//    public void test() {
//        OpenAPI openAPI = new OpenAPI();
//        ExpenseListDTO dto = new ExpenseListDTO();
//        ExpenselistQueryDTO dto1 = new ExpenselistQueryDTO();
//
//        dto1.setVoucherDate(DateUtil.formateDate(new Date(), DateUtil.DateMode_1));
//        dto1.setExternalCode(String.valueOf(System.currentTimeMillis()));
//
//        BusinessType businessType = new BusinessType();
//        businessType.setCode("37");
//        dto1.setBusinessType(businessType);
//
//        BillType billType = new BillType();
//        billType.setCode("01");
//        dto1.setBillType(billType);
//
//        Partner partner = new Partner();
//        partner.setCode("0010001");
//        dto1.setPartner(partner);
//
//        dto1.setMemo("OpenAPI收入单测试1");
//
//        List<MultiSettles> multiSettles = new ArrayList<MultiSettles>();
//        MultiSettles multiSettle = new MultiSettles();
//
//        Settlestyle settlestyle = new Settlestyle();
//        settlestyle.setCode("1");
//
//        BankAccount bankaccount = new BankAccount();
//        bankaccount.setName("现金");
//
//        multiSettle.setSettlestyle(settlestyle);
//        multiSettle.setBankaccount(bankaccount);
//        multiSettle.setOrigAmount(Double.valueOf(105));
//
//        multiSettles.add(multiSettle);
//
//        List<Details> details = new ArrayList<Details>();
//        Details detail = new Details();
//
//        Expenseitem expenseitem = new Expenseitem();
//        expenseitem.setCode("102");
//
//        detail.setExpenseitem(expenseitem);
//        detail.setTaxRate(Double.valueOf(0.05));
//        detail.setOrigAmount(Double.valueOf(100));
//        detail.setOrigTax(Double.valueOf(5));
//        detail.setOrigTaxAmount(Double.valueOf(105));
//
//        details.add(detail);
//
//        dto1.setMultiSettles(multiSettles);
//        dto1.setDetails(details);
//
//        dto.setDto(dto1);
//        try {
//            String login = openAPI.login(tsumProperties.getUserName(),
//                    tsumProperties.getPassWord(), tsumProperties.getAccount(),
//                    tsumProperties.getUrl(), tsumProperties.getAppkey(),
//                    tsumProperties.getAppSecret(), tsumProperties.getPemFile());
//
//            String eee = openAPI.business(JSON.toJSONString(dto), "expenseVoucher/CreateExpenseVoucher",
//                    tsumProperties.getUrl(),
//                    tsumProperties.getAppkey(),
//                    tsumProperties.getAppSecret(),
//                    tsumProperties.getPemFile());
//
//            System.out.println(eee);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getProperties() {
//    }
//
//}
//
//
