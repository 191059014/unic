package com.hb.unic.common.logichandle.loop;

import com.hb.unic.common.logichandle.ILogicHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Map集合循环处理器
 *
 * @version v0.1, 2021/12/18 21:49, create by huangbiao.
 */
public class MapLoopHandler<K, V> implements ILogicHandler {

    /**
     * 逻辑方法集合
     */
    private List<BiConsumer<K, V>> consumerList = new ArrayList<>();

    /**
     * 集合
     */
    private Map<K, V> map;

    /**
     * 构造方法
     *
     * @param map
     *            需要被遍历的集合
     */
    public MapLoopHandler(Map<K, V> map) {
        this.map = map;
    }

    /**
     * 添加逻辑处理方法
     * 
     * @param consumer
     *            逻辑处理方法
     * @return 处理器对象本身
     */
    public MapLoopHandler<K, V> addLogic(BiConsumer<K, V> consumer) {
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
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<K, V> entry : map.entrySet()) {
                for (BiConsumer<K, V> consumer : consumerList) {
                    consumer.accept(entry.getKey(), entry.getValue());
                }
            }
        }
        return true;
    }

}
