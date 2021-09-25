package com.hb.unic.common.validator;

/**
 * 常见校验
 *
 * @version v0.1, 2021/8/22 19:28, create by huangbiao.
 */
public class Check {

    /**
     * 校验分页参数
     * 
     * @param pageNum
     *            当前页
     * @param pageSize
     *            每页条数
     * @return true为校验通过
     */
    public static boolean incorrectPageParameter(Integer pageNum, Integer pageSize) {
        return pageNum == null || pageNum < 1 || pageSize == null || pageSize < 1;
    }

}
