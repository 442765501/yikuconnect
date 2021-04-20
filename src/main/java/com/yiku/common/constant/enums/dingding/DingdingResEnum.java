package com.yiku.common.constant.enums.dingding;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/30 20:06
 */
@AllArgsConstructor
public enum DingdingResEnum {

    BUSINESS_TYPE("业务类型", "BUSINESS_TYPE"),

    BILL_TYPE("票据类型", "BILL_TYPE"),

    CURRENT_UNIT("往来单位", "CURRENT_UNIT"),

    TITLE_OF_ACCOUNT("账户名称", "TITLE_OF_ACCOUNT"),

    SETTLEMENT_METHOD("结算方式", "SETTLEMENT_METHOD"),

    INCOME_AMOUNT("收入金额", "INCOME_AMOUNT"),

    BILL_NO("票据单号", "BILL_NO"),

    DATE("日期", "DATE"),

    SUBJECT("科目", "SUBJECT"),

    COST("费用", "COST"),

    TAX_RATE("税率", "TAX_RATE"),

    AMOUNTOF_MONEY("金额", "AMOUNTOF_MONEY"),

    USERNAME("姓名","USERNAME"),

    EXPENSE_DETAILS("费用明细","EXPENSE_DETAILS"),

    PURPOSE("用途","PURPOSE"),

    DEPTNAME("所在部门","DEPTNAME"),

    PICTURE("图片","PICTURE"),

    TAX_AMOUNT("税额", "TAX_AMOUNT"),

    AMOUNT_INCLUDING_TAX("含税金额", "AMOUNT_INCLUDING_TAX"),

    SECRETARY("村书记","SECRETARY"),

    ROLEOFVILLAGEORGANIZATIONS("村级组织角色","ROLEOFVILLAGEORGANIZATIONS"),

    DETAILSOF_CASHSETTLEMENT("现结金额明细", "DETAILSOF_CASHSETTLEMENT"),

    DETAILSOF_INCOMESTATEMENT("收入单的明细", "DETAILSOF_INCOMESTATEMENT");


    private @Getter
    String msg;
    private @Getter
    String code;

}
