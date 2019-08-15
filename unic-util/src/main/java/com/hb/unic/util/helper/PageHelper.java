package com.hb.unic.util.helper;

/**
 * ========== 分页工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.PageHelper.java, v1.0
 * @date 2019年06月23日 00时38分
 */
public class PageHelper {

    /**
     * ########## 计算起始记录行数 ##########
     *
     * @param pageNum  当前第几页
     * @param pageSize 每页多少条
     * @return 起始记录行数
     */
    public static Integer getStartRow(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 0 : pageNum;
        pageSize = pageSize == null ? 0 : pageSize;
        return (pageNum - 1) * pageSize;
    }

}
