package com.hb.unic.common.ifelse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * 多重逻辑处理器
 *
 * @version v0.1, 2021/12/18 20:23, create by huangbiao.
 */
public class MultiLogicHandler {

    /**
     * 方法集合
     */
    private List<BooleanSupplier> supplierList = new ArrayList<>();

    /**
     * 创建一个多重逻辑处理器
     * 
     * @return 处理器对象
     */
    public static MultiLogicHandler create() {
        return new MultiLogicHandler();
    }

    /**
     * 添加逻辑处理分支
     * 
     * @param supplier
     *            处理方法
     * @return 处理器对象
     */
    public MultiLogicHandler addLogic(BooleanSupplier supplier) {
        this.supplierList.add(supplier);
        return this;
    }

    /**
     * 执行满足条件的所有逻辑方法
     * 
     * @return true代表最少有一个逻辑方法执行成功
     */
    public boolean execute() {
        boolean doAtLeastOneLogic = false;
        for (BooleanSupplier supplier : supplierList) {
            boolean handleSuccess = supplier.getAsBoolean();
            if (handleSuccess) {
                doAtLeastOneLogic = true;
            }
        }
        return doAtLeastOneLogic;
    }

}
