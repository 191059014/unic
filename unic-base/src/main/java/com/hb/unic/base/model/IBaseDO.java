package com.hb.unic.base.model;

import java.util.Date;

/**
 * 基础数据模型
 *
 * @version v0.1, 2021/9/12 16:52, create by huangbiao.
 */
public interface IBaseDO {

    /**
     * 获取主键
     */
    Long getId();

    /**
     * 设置主键
     */
    void setId(Long id);

    /**
     * 获取创建者
     */
    String getCreateBy();

    /**
     * 设置创建者
     */
    void setCreateBy(String createBy);

    /**
     * 获取创建时间
     */
    Date getCreateTime();

    /**
     * 设置创建时间
     */
    void setCreateTime(Date createTime);

    /**
     * 获取更新人
     */
    String getUpdateBy();

    /**
     * 设置更新人
     */
    void setUpdateBy(String updateBy);

    /**
     * 获取更新时间
     */
    Date getUpdateTime();

    /**
     * 设置更新时间
     */
    void setUpdateTime(Date updateTime);

    /**
     * 获取数据记录状态
     */
    Integer getIsValid();

    /**
     * 设置数据记录状态
     */
    void setIsValid(Integer isValid);

}
