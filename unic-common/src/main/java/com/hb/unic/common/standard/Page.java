package com.hb.unic.common.standard;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * 分页
 *
 * @version v0.1, 2021/1/27 13:36, create by huangbiao.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Page<T> {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据集
     */
    private List<T> rows;

    /**
     * 创建Page对象
     *
     * @param total
     *            总条数
     * @param rows
     *            数据集
     * @param <T>
     *            泛型
     * @return 分页结果
     */
    public static <T> Page<T> create(Long total, List<T> rows) {
        Page<T> page = new Page<>();
        page.setTotal(total);
        page.setRows(rows);
        return page;
    }

}
