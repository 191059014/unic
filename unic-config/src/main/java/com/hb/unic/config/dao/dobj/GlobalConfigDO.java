package com.hb.unic.config.dao.dobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.model.impl.AbstractBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 全局配置表数据模型
 *
 * @version v0.1, 2021-09-21 15:08:19, create by Mr.Huang.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class GlobalConfigDO extends AbstractBaseDO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5003182963529863598L;

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 配置标识
     */
    private String configKey;

    /**
     * 配置内容
     */
    private String configValue;

    /**
     * 备注
     */
    private String remark;

}
