package com.hb.unic.common.standard;

import lombok.Data;

/**
 * 分页条件
 *
 * @version v0.1, 2021/9/4 21:26, create by huangbiao.
 */
@Data
public class PageCondition {

    /**
     * 开始下标
     */
    private Integer startIndex;

    /**
     * 每页条数
     */
    private Integer pageSize;

    /**
     * 创建分页查询条件对象
     *
     * @param pageNum
     *            当前第几页
     * @param pageSize
     *            每页条数
     * @return 分页条件
     */
    public static PageCondition create(Integer pageNum, Integer pageSize) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setPageSize(pageSize);
        pageCondition.setStartIndex((pageNum - 1) * pageSize);
        return pageCondition;
    }

}
