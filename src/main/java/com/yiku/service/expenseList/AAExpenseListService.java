package com.yiku.service.expenseList;


import com.yiku.common.dto.expenseList.*;

import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/16 13:57
 */
public interface AAExpenseListService {

    /**
     * 列表
     */
    List<ExpenselistQueryDTO> getAAExpenseItem();

    /**
     * 根据code获取列表
     */
    List<ExpenselistQueryDTO> getAAExpenseItemByCode(String code);

    /**
     * 获取业务类型名称
     */
    List<BusinessType> queryBusinessTypeByName();

    /**
     * 获取票据类型
     */
    List<BillType> queryBillTypeQueryByIdEnum();

    /**
     * 获取往来单位
     */
    List<Partner> queryPartnerQueryDTO();

    /**
     * 获取T+存在的员工
     */
    List<Person> queryPerson();

    /**
     * 获取T+存在的部门
     */
    List<Department> queryDepartMent();

    /**
     * 获取结算方式
     */
    List<Settlestyle>querySettleStyle();

    /**
     * 获取账号名称
     */
    List<BankAccount>queryBankAccount();
    /**
     * 获取T+存在的币种
     */
    List<Currency> queryCurrency();

    /**
     * 根据code判断该用户是否为客户还是供应商
     */
    ARAPAccountDirection queryAccountDirectionByCode(String code);
}
