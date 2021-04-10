package com.yiku.dao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Function:公共方法名称提取
 * User: ldd
 * Date: 2018/2/8
 */
@AllArgsConstructor
public enum CommonMethodEnum {
    /**
     * 查询列表集合
     */
    QUERY_LIST("查询列表集合", "queryList"),
    /**
     * 查询列表集合数量
     */
    QUERY_LIST_COUNT("查询列表集合数量", "queryListCount"),
    /**
     * 批量插入集合操作
     */
    BATCH_INSERT_LIST("批量插入集合操作", "batchInsertList"),
    /**
     * 批量更新集合操作
     */
    BATCH_UPDATE_LIST("批量更新集合操作", "batchUpdateList"),
    /**
     * 批量删除集合操作
     */
    BATCH_DELETE_LIST("批量删除集合操作", "batchDeleteList"),;
    private @Getter
    @Setter
    String desc;
    private @Getter
    @Setter
    String method;
}
