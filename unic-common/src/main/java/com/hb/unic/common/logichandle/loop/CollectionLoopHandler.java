package com.hb.unic.common.logichandle.loop;

import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 集合循环处理器
 *
 * @version v0.1, 2021/12/18 21:49, create by huangbiao.
 */
public class CollectionLoopHandler<V> implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<BiConsumer<Integer, V>> consumerList = new ArrayList<>();

    /**
     * 集合
     */
    private Collection<V> objs;

    /**
     * 构造方法
     *
     * @param objs
     *            需要被遍历的集合
     */
    public CollectionLoopHandler(Collection<V> objs) {
        this.objs = objs;
    }

    /**
     * 添加逻辑处理方法
     * 
     * @param consumer
     *            逻辑处理方法
     * @return 处理器对象本身
     */
    public CollectionLoopHandler<V> addLogic(BiConsumer<Integer, V> consumer) {
        this.consumerList.add(consumer);
        return this;
    }

    /**
     * 遍历集合，执行所有的逻辑方法
     *
     * @return 执行结果
     */
    @Override
    public boolean execute() {
        if (objs != null && !objs.isEmpty()) {
            Iterator<V> iterator = objs.iterator();
            for (int i = 0; i < objs.size(); i++) {
                V next = iterator.next();
                for (BiConsumer<Integer, V> consumer : consumerList) {
                    consumer.accept(i, next);
                }
            }
        }
        return true;
    }

}
