package com.yiku.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yiku.common.constant.FinalDatas;
import com.yiku.common.util.DateEditor;
import com.yiku.common.util.DateUtil;
import com.yiku.common.util.LogUtil;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {

    private String dateFormat = DateUtil.DateMode_4;

    @InitBinder
    private void initBinder(HttpServletRequest request,
                            ServletRequestDataBinder binder) throws Exception{
        binder.registerCustomEditor(Date.class, new DateEditor());
    }


    /**
     * 将Java对象转换为JSON对象并响应给浏览器端
     *
     * @param obj
     * @param response
     */
    protected void sendJson(Object obj, HttpServletResponse response) {
        try {
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding(FinalDatas.UTF_8);
            response.getWriter().print(JSON.toJSONStringWithDateFormat(obj,this.dateFormat, SerializerFeature.DisableCircularReferenceDetect));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            LogUtil.error(e,"","输入json异常");
        }
    }

    /**
     * 将Str对象响应给浏览器端
     *
     * @param str
     * @param response
     */
    protected void sendRes(String str, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.print(new ByteArrayInputStream(str.getBytes(Charset.forName(FinalDatas.UTF_8))));
            out.flush();
            out.close();
        } catch (IOException e) {
            LogUtil.error(e,"","页面打印异常");
        }
    }
    /**
     * 将Str对象响应给浏览器端
     *
     * @param str
     * @param response
     */
    protected void sendStringRes(String str, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            out.print(str);
            out.flush();
            out.close();
        } catch (IOException e) {
            LogUtil.error(e,"","页面打印异常");
        }
    }

    /**
     * 发送一个完成的JSON响应<br>
     *
     * @param response
     */
    protected void sendJsonSuccess(HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, true);
        sendJson(map,response);
    }

    /**
     * 发送一个完成的JSON响应,同时带有结果对象
     *
     * @param result
     * @param response
     */
    protected void sendJsonSuccess(Object result, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.RESULT_LOWER, result);
        sendJson(map,response);
    }

    protected void sendJsonSuccess(String info,String code,Object result, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, true);
        map.put(FinalDatas.INFO_LOWER, info);
        map.put(FinalDatas.CODE, code);
        map.put(FinalDatas.RESULT_LOWER, result);
        sendJson(map,response);
    }

    protected void sendJsonSuccessPage(Object result, List<?> list,Integer count,String info ,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, true);
        map.put(FinalDatas.RESULT_LOWER, result);
        map.put(FinalDatas.LIST_LOWER, list);
        map.put(FinalDatas.COUNT_LOWER, count);
        map.put(FinalDatas.INFO_LOWER, info);
        sendJson(map,response);
    }

    /**
     * 发送一个失败的JSON响应,同时带有错误信息
     *
     * @param info
     * @param response
     */
    protected void sendJsonError(String info, String errCode,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, false);
        map.put(FinalDatas.ERR_CODE_LOWER, errCode);
        map.put(FinalDatas.INFO_LOWER, info);
        sendJson(map,response);
    }

    protected void sendJsonError(String info,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, false);
        map.put(FinalDatas.INFO_LOWER, info);
        sendJson(map,response);
    }



    /**
     * 返回一个失败的错误json，同时带有错误信息
     * @param info
     * @param errCode
     * @return
     */
    protected Map<String, Object> sendJsonError(String info, String errCode){
        Map<String, Object> map = new HashMap<>();
        map.put(FinalDatas.SUCCESS_LOWER, false);
        map.put(FinalDatas.ERR_CODE_LOWER, errCode);
        map.put(FinalDatas.INFO_LOWER, info);
        return map;
    }

}
