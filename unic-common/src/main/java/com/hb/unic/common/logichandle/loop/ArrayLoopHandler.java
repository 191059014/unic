package com.hb.unic.common.logichandle.loop;

import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 数组循环处理器
 *
 * @version v0.1, 2021/12/18 21:49, create by huangbiao.
 */
public class ArrayLoopHandler<V> implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<BiConsumer<Integer, V>> consumerList = new ArrayList<>();

    /**
     * 数组
     */
    private V[] objArr;

    /**
     * 构造方法
     * 
     * @param objArr
     *            需要被遍历的数组
     */
    public ArrayLoopHandler(V[] objArr) {
        this.objArr = objArr;
    }

    /**
     * 添加逻辑处理方法
     * 
     * @param consumer
     *            逻辑处理方法
     * @return 处理器对象本身
     */
    public ArrayLoopHandler<V> addLogic(BiConsumer<Integer, V> consumer) {
        this.consumerList.add(consumer);
        return this;
    }

    /**
     * 遍历数组，执行所有的逻辑方法
     *
     * @return 执行结果
     */
    @Override
    public boolean execute() {
        if (objArr != null && objArr.length > 0) {
            for (int i = 0; i < objArr.length; i++) {
                for (BiConsumer<Integer, V> consumer : consumerList) {
                    consumer.accept(i, objArr[i]);
                }
            }
        }
        return true;
    }

}
