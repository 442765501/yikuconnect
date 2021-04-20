package com.yiku.common.dto.ueight;

import lombok.Data;

import java.util.List;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/19 15:58
 */
@Data
public class UeightReqDTO {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 标题
     */
    private String title;

    /**
     * 部门
     */
    private String deptname;

    /**
     * 费用列表
     */
    List<UeightReqExtDTO> ueightReqExtDTOList;

    /**
     * 费用明细
     */
    private String expensedetails;


    /**
     *创建时间
     */
    private String createtime;

    /**
     * 图片链接
     */
    private String url;

    /**
     * 0审核中 1已审核 2审核失败
     */
    private String status;

    /**
     *0存在 1删除
     */
    private Integer isdelete;

}
