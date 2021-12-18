package com.hb.unic.common.logichandle.loop;

import com.hb.unic.common.function.VoidFunction;
import com.hb.unic.common.help.TypeWapper;
import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * while循环处理器
 *
 * @version v0.1, 2021/12/18 21:49, create by huangbiao.
 */
public class WhileLoopHandler implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<VoidFunction> consumerList = new ArrayList<>();

    /**
     * 条件集合
     */
    private List<TypeWapper<Boolean>> conditionList = new ArrayList<>();

    /**
     * 创建循环处理器，支持单个条件
     *
     * @param condition
     *            条件
     * @return 处理器对象本身
     */
    public static WhileLoopHandler create(TypeWapper<Boolean> condition) {
        WhileLoopHandler handler = new WhileLoopHandler();
        handler.conditionList.add(condition);
        return handler;
    }

    /**
     * 创建循环处理器，支持两个条件
     *
     * @param condition1
     *            条件1
     * @param condition2
     *            condition2
     * @return 处理器对象本身
     */
    public static WhileLoopHandler create(TypeWapper<Boolean> condition1, TypeWapper<Boolean> condition2) {
        WhileLoopHandler handler = new WhileLoopHandler();
        handler.conditionList.add(condition1);
        handler.conditionList.add(condition2);
        return handler;
    }

    /**
     * 创建循环处理器，支持多个条件
     *
     * @param conditionList
     *            条件集合
     * @return 处理器对象本身
     */
    public static WhileLoopHandler create(List<TypeWapper<Boolean>> conditionList) {
        WhileLoopHandler handler = new WhileLoopHandler();
        if (conditionList != null && !conditionList.isEmpty()) {
            handler.conditionList.addAll(conditionList);
        }
        return handler;
    }

    /**
     * 添加逻辑处理方法
     *
     * @param consumer
     *            逻辑处理方法
     * @return 处理器对象本身
     */
    public WhileLoopHandler addLogic(VoidFunction consumer) {
        this.consumerList.add(consumer);
        return this;
    }

    /**
     * 遍历，执行所有的逻辑方法
     *
     * @return 执行成功结果
     */
    @Override
    public boolean execute() {
        int loopTimes = 0;
        while (loopTimes++ < 1000) {
            boolean anyMatchFlase = conditionList.stream().anyMatch(wapper -> !wapper.get());
            if (anyMatchFlase) {
                break;
            }
            for (VoidFunction consumer : consumerList) {
                consumer.execute();
            }
        }
        return true;
    }

}
