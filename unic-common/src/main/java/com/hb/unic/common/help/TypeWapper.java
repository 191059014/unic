package com.hb.unic.common.help;

/**
 * 类型包装
 *
 * @version v0.1, 2021/12/18 20:56, create by huangbiao.
 */
public class TypeWapper<T> {

    /**
     * 真正的对象
     */
    private T obj;

    /**
     * 构造方法
     * 
     * @param obj
     *            需要包装的对象
     */
    public TypeWapper(T obj) {
        this.obj = obj;
    }

    /**
     * 设置值
     * 
     * @param newObj
     *            新的值
     */
    public void set(T newObj) {
        this.obj = newObj;
    }

    /**
     * 获取值
     * 
     * @return 真正的对象
     */
    public T get() {
        return this.obj;
    }

}
