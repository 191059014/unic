package com.hb.unic.common.ifelse;

import com.hb.unic.common.function.VoidFunction;
import lombok.Data;

/**
 * 逻辑元数据
 *
 * @version v0.1, 2022/1/2 17:29, create by huangbiao.
 */
@Data
public class LogicMeta {

    /**
     * 条件
     */
    private boolean condition;

    /**
     * 逻辑函数
     */
    private VoidFunction logicFunction;

    /**
     * 创建逻辑元数据
     * 
     * @param condition
     *            条件
     * @param logicFunction
     *            逻辑函数
     * @return 逻辑元数据对象
     */
    public static LogicMeta create(boolean condition, VoidFunction logicFunction) {
        LogicMeta logicMeta = new LogicMeta();
        logicMeta.setCondition(condition);
        logicMeta.setLogicFunction(logicFunction);
        return logicMeta;
    }

}
