package com.hb.unic.base.model.impl;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb.unic.base.model.IBaseDO;
import com.hb.unic.common.util.DateUtils;

import java.util.Date;

/**
 * 数据模型超类
 *
 * @version v0.1, 2021-08-22 13:24:32, create by Mr.Huang.
 */
public abstract class AbstractBaseDO implements IBaseDO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_DEFAULT, timezone = DateUtils.TIME_ZONE_DEFAULT)
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateUtils.FORMAT_DEFAULT, timezone = DateUtils.TIME_ZONE_DEFAULT)
    private Date updateTime;

    /**
     * 记录有效状态
     */
    private Integer isValid;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Integer getIsValid() {
        return isValid;
    }

    @Override
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

}
