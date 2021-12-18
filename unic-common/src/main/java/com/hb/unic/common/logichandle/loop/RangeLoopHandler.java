package com.hb.unic.common.logichandle.loop;

import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 数字循环处理器
 *
 * @version v0.1, 2021/12/18 21:49, create by huangbiao.
 */
public class RangeLoopHandler implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<Consumer<Integer>> consumerList = new ArrayList<>();

    /**
     * 开始数字
     */
    private int start;

    /**
     * 结束数字
     */
    private int end;

    /**
     * 创建数字循环处理器
     * 
     * @param start
     *            开始数字
     * @param end
     *            结束数字
     * @return 处理器对象本身
     */
    public static RangeLoopHandler create(int start, int end) {
        RangeLoopHandler handler = new RangeLoopHandler();
        handler.start = start;
        handler.end = end;
        return handler;
    }

    /**
     * 添加逻辑处理方法
     * 
     * @param consumer
     *            逻辑处理方法
     * @return 处理器对象本身
     */
    public RangeLoopHandler addLogic(Consumer<Integer> consumer) {
        this.consumerList.add(consumer);
        return this;
    }

    /**
     * 遍历数字，执行所有的逻辑方法
     *
     * @return 执行结果
     */
    @Override
    public boolean execute() {
        for (int i = start; i <= end; i++) {
            for (Consumer<Integer> consumer : consumerList) {
                consumer.accept(i);
            }
        }
        return true;
    }

}
