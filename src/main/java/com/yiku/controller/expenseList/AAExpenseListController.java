package com.yiku.controller.expenseList;

import com.yiku.common.constant.enums.response.CommonMessageEnum;
import com.yiku.common.dto.expenseList.*;
import com.yiku.common.util.LogUtil;
import com.yiku.common.util.StringUtil;
import com.yiku.controller.BaseController;
import com.yiku.service.expenseList.AAExpenseListService;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/17 9:14
 */
@RestController
@RequestMapping(value = "/yiku/expenseList")
public class AAExpenseListController extends BaseController {

    @Resource
    private AAExpenseListService aaExpenseListService;

    @RequestMapping(value = "/queryAppExpenseItemList")
    public void queryAAExpenseItemList(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<ExpenselistQueryDTO> aaExpenseItem = aaExpenseListService.getAAExpenseItem();
            this.sendJson(aaExpenseItem, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询列表发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryAppExpenseItemListByCode")
    public void queryAAExpenseItemListByCode(HttpServletRequest request, HttpServletResponse response) {

        try {
            String[] codes = request.getParameterValues("code");
            List<ExpenselistQueryDTO> aaExpenseItem = null;
            if ("[]".equals(codes[0]) || StringUtils.isBlank(codes[0])) {
                this.sendJsonError(CommonMessageEnum.PARAM_LOST.getMsg(), response);
            } else {
                String number = StringUtil.replaceNumber(codes[0]);
                aaExpenseItem = aaExpenseListService.getAAExpenseItemByCode(number);
            }
            this.sendJson(aaExpenseItem, response);
        } catch (Exception e) {
            LogUtil.error(e, "根据code查询列表发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryBusinessType")
    public void queryBusinessType(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BusinessType> businessType = aaExpenseListService.queryBusinessTypeByName();
            if (!CollectionUtils.isEmpty(businessType)) {
                this.sendJson(businessType, response);
            } else {
                this.sendJsonError(CommonMessageEnum.PARAM_LOST.getMsg(), CommonMessageEnum.PARAM_LOST.getCode(), response);
            }
        } catch (Exception e) {
            LogUtil.error(e, "查询业务类型名称发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryBillType")
    public void queryBillType(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BillType> billTypeQuery = aaExpenseListService.queryBillTypeQueryByIdEnum();
            if (!CollectionUtils.isEmpty(billTypeQuery)) {
                this.sendJson(billTypeQuery, response);
            } else {
                this.sendJsonError(CommonMessageEnum.PARAM_LOST.getMsg(), CommonMessageEnum.PARAM_LOST.getCode(), response);
            }
        } catch (Exception e) {
            LogUtil.error(e, "查询票据类型发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryPartner")
    public void queryPartner(HttpServletRequest request, HttpServletResponse response) {
        try {
//            String partnerName = request.getParameter("partnerName");
            List<Partner> partners = aaExpenseListService.queryPartnerQueryDTO();
            this.sendJson(partners, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询往来单位发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryPerson")
    public void queryPerson(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Person> person = aaExpenseListService.queryPerson();
            this.sendJson(person, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询T+存在的员工发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryDepartMent")
    public void queryDepartMent(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Department> departments = aaExpenseListService.queryDepartMent();
            this.sendJson(departments, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询T+存在的部门发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryCurrency")
    public void queryCurrency(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Currency> currencies = aaExpenseListService.queryCurrency();
            this.sendJson(currencies, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询T+存在的币种发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/querySettleStyle")
    public void querySettleStyle(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Settlestyle> settlestyles = aaExpenseListService.querySettleStyle();
            this.sendJson(settlestyles, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询T+存在的结算方式发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryBankAccount")
    public void queryBankAccount(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<BankAccount> bankAccounts = aaExpenseListService.queryBankAccount();
            this.sendJson(bankAccounts, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询T+存在的结算方式发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }

    @RequestMapping(value = "/queryAccountDirectionByCode")
    public void queryAccountDirectionByCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = request.getParameter("code");
            ARAPAccountDirection arapAccountDirection = aaExpenseListService.queryAccountDirectionByCode(code);
            this.sendJson(arapAccountDirection, response);
        } catch (Exception e) {
            LogUtil.error(e, "查询根据code判断该用户是否为客户还是供应商发生异常");
            this.sendJsonError(e.toString(), CommonMessageEnum.FAIL.getCode(), response);
        }
    }
}
