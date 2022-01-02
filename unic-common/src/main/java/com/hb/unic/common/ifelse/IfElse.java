package com.hb.unic.common.ifelse;

import com.hb.unic.common.function.VoidFunction;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 处理if else
 *
 * @version v0.1, 2021/12/18 20:16, create by huangbiao.
 */
public class IfElse {

    /**
     * 所有逻辑元数据集合
     */
    private final List<LogicMeta> logicMetas = new LinkedList<>();

    /**
     * 创建逻辑对象
     *
     * @return 逻辑对象
     */
    public static IfElse create() {
        return new IfElse();
    }

    /**
     * if
     *
     * @param condition
     *            条件
     * @param ifFunction
     *            条件为真时执行的方法
     * @return 逻辑对象
     */
    public IfElse ifLogic(boolean condition, VoidFunction ifFunction) {
        this.logicMetas.add(LogicMeta.create(condition, ifFunction));
        return this;
    }

    /**
     * else if
     *
     * @param condition
     *            条件
     * @param elseIfFunction
     *            条件为真时执行的方法
     * @return 逻辑对象
     */
    public IfElse elseIfLogic(boolean condition, VoidFunction elseIfFunction) {
        this.logicMetas.add(LogicMeta.create(condition, elseIfFunction));
        return this;
    }

    /**
     * else
     * 
     * @param elseFunction
     *            条件为假时执行的方法
     * @return 逻辑对象
     */
    public IfElse elseLogic(VoidFunction elseFunction) {
        this.logicMetas.add(LogicMeta.create(true, elseFunction));
        return this;
    }

    /**
     * 执行最终的逻辑方法
     */
    public boolean execute() {
        for (LogicMeta logicMeta : logicMetas) {
            if (logicMeta.isCondition()) {
                VoidFunction logicFunction = logicMeta.getLogicFunction();
                if (Objects.nonNull(logicFunction)) {
                    logicFunction.execute();
                    return true;
                }
            }
        }
        return false;
    }

}
