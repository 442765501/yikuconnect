package com.yiku.common.dto.Tsum;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/4/1 16:55
 */
@Data
public class TsumParameterReqDTO implements Serializable {

    private static final long serialVersionUID = 4088641604395547450L;

    /**
     * 凭证代码
     */
    private String voucherCode;

    /**
     * 商业代码
     */
    private String bizCode;

    /**
     * 行动
     */
    private String action;

    /**
     *意见
     */
    private String opinion;

    /**
     * 选择节点
     */
    private String selNextNodeId;

    /**
     *校验码
     */
    private String controlCode;
}
