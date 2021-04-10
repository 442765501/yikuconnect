package com.yiku.common.dto.expenseList;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ldd
 * @version 1.0
 * @date 2021/3/27 11:13
 */
@Data
public class ExpenseListDTO implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;


    ExpenselistQueryDTO dto;
}
