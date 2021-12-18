package com.hb.unic.common.ifelse;

import com.hb.unic.common.function.VoidFunction;

/**
 * 处理if else
 *
 * @version v0.1, 2021/12/18 20:16, create by huangbiao.
 */
public class IfElse {

    /**
     * 条件
     */
    private boolean condition;

    /**
     * 条件为真时执行的方法
     */
    private VoidFunction ifFunction;

    /**
     * 条件为假时执行的方法
     */
    private VoidFunction elseFunction;

    /**
     * 创建逻辑对象
     * 
     * @param condition
     *            条件
     * @return 逻辑对象
     */
    public static IfElse create(boolean condition) {
        IfElse ifElse = new IfElse();
        ifElse.condition = condition;
        return ifElse;
    }

    /**
     * 创建逻辑对象
     * 
     * @param ifFunction
     *            条件为真时执行的方法
     * @return 逻辑对象
     */
    public IfElse ifLogic(VoidFunction ifFunction) {
        this.ifFunction = ifFunction;
        return this;
    }

    /**
     * 创建逻辑对象
     * 
     * @param elseFunction
     *            条件为假时执行的方法
     * @return 逻辑对象
     */
    public IfElse elseLogic(VoidFunction elseFunction) {
        this.elseFunction = elseFunction;
        return this;
    }

    /**
     * 执行最终的逻辑方法
     */
    public void execute() {
        if (condition) {
            this.ifFunction.execute();
        } else {
            if (elseFunction != null) {
                elseFunction.execute();
            }
        }
    }

}
