package com.yiku.common.constant.enums.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project Name: kc-risk
 * Package Name: cn.kingcar.risk.biz.common.constant.enums.response
 * Function: 公共响应消息枚举
 * user: San
 * Date:2018/2/5
 */
@AllArgsConstructor
public enum CommonMessageEnum {

    /**
     * 响应消息枚举列表
     */
    SUCCESS("操作成功","SUCCESS"),
    FAIL("操作失败","FAIL"),
    PARAM_LOST("参数缺失","PARAM_LOST"),
    ILLEGAL_PARAM("非法参数","ILLEGAL_PARAM"),
    NO_PERMISION("您没有权限执行此操作","NO_PERMISION"),
    DATA_IS_EMPTY("数据为空","DATA_IS_EMPTY"),
    PAGE_IS_EMPTY("分页数据为空","PAGE_IS_EMPTY"),
    PRI_ID_IS_EMPT("主键ID为空","PAGE_IS_EMPTY"),
    USER_IS_NULL("用户不存在","USER_IS_NULL"),
    USER_IS_EXIST("用户已存在","USER_IS_EXIST"),
    FILE_FORMAT_ERROR("仅支持.jpg .jpeg .png格式","FILE_FORMAT_ERROR"),
    FILE_EMPTY("文件缺失","FILE_EMPTY"),
    FILE_FORMAT("文件内容不匹配","FILE_FORMAT"),
    PASSWORD_ERROR("密码错误","PASSWORD_ERROR"),
    USER_NOT_ALLOW_LOGIN("当前用户禁止登录,请联系管理员", "USER_NOT_ALLOW_LOGIN"),
    USER_NOT_LOGIN("用户未登陆", "USER_NOT_LOGIN"),
    USER_LOCK_ALLOW_LOGIN("当前用户已被锁定,请联系管理员", "USER_LOCK_ALLOW_LOGIN"),
    RECORD_EMPTY("无录音文件","RECORD_EMPTY"),
    LOGIN_SUCCESS("登陆成功","LOGIN_SUCCESS"),
    GET_MENU_LIST_FAIL("获取菜单列表失败","GET_MENU_LIST_FAIL"),
    INSERT_FAILURE("增加失败","INSERT_FAILURE"),
    UPDATE_FAILURE("更改失败","UPDATE_FAILURE"),
    RECORD_IS_NULL("数据不存在","RECORD_IS_NULL"),
    PARAMETER_ERROR("参数列表字段有空值","PARAMETER_ERROR"),
    ID_IS_NULL("主键为空","ID_IS_NULL"),
    UNKNOWN_ERROR("未知错误","UNKNOWN_ERROR"),
    NO_PERMISSION("您没有权限,请勿操作！","NO_PERMISSION"),
    MONEY_IS_NULL("金额不能为空！","MONEY_IS_NULL"),
    SELECT_FAILURE("查询失败","SELECT_FAILURE"),
    DELETE_FAILURE("删除失败","DELETE_FAILURE"),
    THE_REGION_NONE_PRESERVER("该区域无保全人员，无法进行车辆入库","THE_REGION_NONE_PRESERVER"),
    /*****************缓存相关*********************/
    CACHE_SAVE_ERROR("缓存保存错误","CACHE_SAVE_ERROR"),
    CACHE_DELETE_ERROR("缓存删除错误","CACHE_DELETE_ERROR"),

    NOTIFY_MANUAL_SEND_FAIL("指定批次结果消息发送失败","NOTIFY_MANUAL_SEND_FAIL"),

    /*****************DUBBO服务调用*********************/
    DUBBO_EXCEPTION("DUBBO服务调用异常","DUBBO_EXCEPTION"),
    DUBBO_FLAG_FALSE("DUBBO服务调用失败","DUBBO_FLAG_FALSE"),

    SYS_PARAM_VALUE_GET_VALUE("系统参数取值失败","SYS_PARAM_VALUE_GET_VALUE")
    ;
    private @Getter String msg;
    private @Getter String code;

}
