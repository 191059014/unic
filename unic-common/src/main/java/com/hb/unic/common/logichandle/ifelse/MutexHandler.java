package com.hb.unic.common.logichandle.ifelse;

import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/**
 * 互斥逻辑处理器
 *
 * @version v0.1, 2021/12/18 20:23, create by huangbiao.
 */
public class MutexHandler implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<BooleanSupplier> supplierList = new ArrayList<>();

    /**
     * 创建一个互斥逻辑处理器
     * 
     * @return 处理器对象
     */
    public static MutexHandler create() {
        return new MutexHandler();
    }

    /**
     * 添加逻辑处理分支
     * 
     * @param supplier
     *            处理方法
     * @return 处理器对象
     */
    public MutexHandler addLogic(BooleanSupplier supplier) {
        this.supplierList.add(supplier);
        return this;
    }

    /**
     * 执行最终的逻辑方法
     * 
     * @return true代表有一个逻辑方法执行成功
     */
    @Override
    public boolean execute() {
        for (BooleanSupplier supplier : supplierList) {
            boolean handleSuccess = supplier.getAsBoolean();
            if (handleSuccess) {
                return true;
            }
        }
        return false;
    }

}
