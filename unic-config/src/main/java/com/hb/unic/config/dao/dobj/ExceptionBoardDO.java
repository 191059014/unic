package com.hb.unic.config.dao.dobj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.model.impl.AbstractBaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 异常看板表数据模型
 *
 * @version v0.1, 2021-09-23 22:17:34, create by Mr.Huang.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ExceptionBoardDO extends AbstractBaseDO {

    /**
     * 系统名称
     */
    private String systemName;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 处理状态
     */
    private String processState;

    /**
     * 配置内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 链路追踪ID
     */
    private String traceId;

}
