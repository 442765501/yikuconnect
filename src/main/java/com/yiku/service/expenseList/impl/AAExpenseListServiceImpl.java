package com.yiku.service.expenseList.impl;

import com.yiku.common.dto.expenseList.*;
import com.yiku.dao.entity.aabankAccount.AABankAccountBean;
import com.yiku.dao.entity.aabusiType.AABusiTypeBean;
import com.yiku.dao.entity.aacurrency.AACurrencyBean;
import com.yiku.dao.entity.aadepartment.AADepartmentBean;
import com.yiku.dao.entity.aaexentity.AAExpenseItemBean;
import com.yiku.dao.entity.aapartner.AAPartnerBean;
import com.yiku.dao.entity.aaperson.AAPersonBean;
import com.yiku.dao.entity.aasettleStyle.AASettleStyleBean;
import com.yiku.dao.entity.eapEnumItem.EapEnumItemBean;
import com.yiku.dao.framework.inf.YiKuDAO;
import com.yiku.service.expenseList.AAExpenseListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/16 14:13
 */

@Service
public class AAExpenseListServiceImpl implements AAExpenseListService {

    private YiKuDAO<AAExpenseItemBean, Integer> aaExpenseItemBeanDAO;

    private YiKuDAO<AABusiTypeBean, Integer> aaBusiTypeBeanDAO;

    private YiKuDAO<EapEnumItemBean, Integer> eapEnumItemBeanDAO;

    private YiKuDAO<AAPartnerBean, Integer> aaPartnerBeanDAO;

    private YiKuDAO<AAPersonBean, Integer> aaPersonBeanDAO;

    private YiKuDAO<AADepartmentBean, Integer> aaDepartmentBeanDAO;

    private YiKuDAO<AACurrencyBean, Integer> aaCurrencyBeanDAO;

    private YiKuDAO<AASettleStyleBean, Integer> aaSettleStyleDAO;

    private YiKuDAO<AABankAccountBean, Integer> aaBankAccountBeanDAO;

//    @Resource
////    private RedisService redisService;


    @Resource
    public void setAaBankAccountBeanDAO(YiKuDAO<AABankAccountBean, Integer> aaBankAccountBeanDAO) {
        this.aaBankAccountBeanDAO = aaBankAccountBeanDAO;
        this.aaBankAccountBeanDAO.setPerfix(AABankAccountBean.class.getName());
    }


    @Resource
    public void setAaSettleStyleDAO(YiKuDAO<AASettleStyleBean, Integer> aaSettleStyleDAO) {
        this.aaSettleStyleDAO = aaSettleStyleDAO;
        this.aaSettleStyleDAO.setPerfix(AASettleStyleBean.class.getName());
    }


    @Resource
    public void setAaCurrencyBeanDAO(YiKuDAO<AACurrencyBean, Integer> aaCurrencyBeanDAO) {
        this.aaCurrencyBeanDAO = aaCurrencyBeanDAO;
        this.aaCurrencyBeanDAO.setPerfix(AACurrencyBean.class.getName());
    }


    @Resource
    public void setAaDepartmentBeanDAO(YiKuDAO<AADepartmentBean, Integer> aaDepartmentBeanDAO) {
        this.aaDepartmentBeanDAO = aaDepartmentBeanDAO;
        this.aaDepartmentBeanDAO.setPerfix(AADepartmentBean.class.getName());
    }

    @Resource
    public void setAaPersonBeanDAO(YiKuDAO<AAPersonBean, Integer> aaPersonBeanDAO) {
        this.aaPersonBeanDAO = aaPersonBeanDAO;
        this.aaPersonBeanDAO.setPerfix(AAPersonBean.class.getName());
    }

    @Resource
    public void setAaPartnerBeanDAO(YiKuDAO<AAPartnerBean, Integer> aaPartnerBeanDAO) {
        this.aaPartnerBeanDAO = aaPartnerBeanDAO;
        this.aaPartnerBeanDAO.setPerfix(AAPartnerBean.class.getName());
    }

    @Resource
    public void setEapEnumItemBeanDAO(YiKuDAO<EapEnumItemBean, Integer> eapEnumItemBeanDAO) {
        this.eapEnumItemBeanDAO = eapEnumItemBeanDAO;
        this.eapEnumItemBeanDAO.setPerfix(EapEnumItemBean.class.getName());
    }

    @Resource
    public void setAaBusiTypeBeanDAO(YiKuDAO<AABusiTypeBean, Integer> aaBusiTypeBeanDAO) {
        this.aaBusiTypeBeanDAO = aaBusiTypeBeanDAO;
        this.aaBusiTypeBeanDAO.setPerfix(AABusiTypeBean.class.getName());
    }

    @Resource
    public void setCsexpensevoucherBean(YiKuDAO<AAExpenseItemBean, Integer> aaExpenseItemBeanDAO) {
        this.aaExpenseItemBeanDAO = aaExpenseItemBeanDAO;
        this.aaExpenseItemBeanDAO.setPerfix(AAExpenseItemBean.class.getName());
    }

    @Override
    public List<ExpenselistQueryDTO> getAAExpenseItem() {
        List<ExpenselistQueryDTO> expenselistQueryDTOS = this.aaExpenseItemBeanDAO.executeListMethod("null", "getAAExpenseItem", ExpenselistQueryDTO.class);
//        boolean expense = redisService.addStringToJedis("expense", JSONObject.toJSONString(expenselistQueryDTOS));
//        System.out.println(expense);
        return expenselistQueryDTOS;

    }

    @Override
    public List<ExpenselistQueryDTO> getAAExpenseItemByCode(String code) {
        List<ExpenselistQueryDTO> getEpenseItemSonByCode = this.aaExpenseItemBeanDAO.executeListMethod(code, "getEpenseItemSonByCode", ExpenselistQueryDTO.class);
        return getEpenseItemSonByCode;
    }

    @Override
    public List<BusinessType> queryBusinessTypeByName() {

        List<BusinessType> businessTypes = this.aaBusiTypeBeanDAO.executeListMethod("", "queryBusinessTypeByName", BusinessType.class);

        return businessTypes;
    }

    @Override
    public List<BillType> queryBillTypeQueryByIdEnum() {

        List<BillType> getBillTypeByIdEnum = this.eapEnumItemBeanDAO.executeListMethod("", "getBillTypeByIdEnum", BillType.class);

        return getBillTypeByIdEnum;
    }

    @Override
    public List<Partner> queryPartnerQueryDTO() {
        List<Partner> getPartnerByName = this.aaPartnerBeanDAO.executeListMethod("", "getPartner", Partner.class);
        return getPartnerByName;
    }

    @Override
    public ARAPAccountDirection queryAccountDirectionByCode(String code) {
        //根据code获取id
        Partner getPartnerByName = this.aaPartnerBeanDAO.executeSelectOneMethod(code, "getPartnerByCode", Partner.class);
        //根据id获取name
        EapEnumItemBean eapEnumItemBean = eapEnumItemBeanDAO.selectByPrimaryKey(getPartnerByName.getCustomId());
        ARAPAccountDirection arapAccountDirection = new ARAPAccountDirection();
        arapAccountDirection.setCode(String.valueOf(eapEnumItemBean.getId()));
        arapAccountDirection.setName(eapEnumItemBean.getName());
        return arapAccountDirection;
    }

    @Override
    public List<Person> queryPerson() {
        List<Person> getPersonItem = this.aaPersonBeanDAO.executeListMethod("", "getPersonItem", Person.class);
        return getPersonItem;
    }

    @Override
    public List<Department> queryDepartMent() {
        List<Department> getDepartmentItem = this.aaDepartmentBeanDAO.executeListMethod("", "getDepartmentItem", Department.class);
        return getDepartmentItem;
    }

    @Override
    public List<Settlestyle> querySettleStyle() {
        List<Settlestyle> getPersonItem = this.aaSettleStyleDAO.executeListMethod("", "getSettleStyleItem", Settlestyle.class);
        return getPersonItem;
    }

    @Override
    public List<BankAccount> queryBankAccount() {
        List<BankAccount> queryBankAccount = this.aaBankAccountBeanDAO.executeListMethod("", "queryBankAccount", BankAccount.class);
        return queryBankAccount;
    }

    @Override
    public List<Currency> queryCurrency() {
        List<Currency> queryCurrency = this.aaCurrencyBeanDAO.executeListMethod("", "queryCurrency", Currency.class);
        return queryCurrency;
    }


}

