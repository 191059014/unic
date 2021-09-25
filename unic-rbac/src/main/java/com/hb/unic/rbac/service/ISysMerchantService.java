package com.hb.unic.rbac.service;

import java.util.List;
import java.util.Set;

import com.hb.unic.common.standard.Page;
import com.hb.unic.rbac.dao.dobj.SysMerchantDO;

/**
 * 商户表服务层接口
 *
 * @version v0.1, 2021-09-04 12:48:33, create by Mr.Huang.
 */
public interface ISysMerchantService {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysMerchantDO selectById(Long id);

    /**
     * 通过条件查询单条数据
     *
     * @param sysMerchant
     *            查询条件
     * @return 实例对象
     */
    SysMerchantDO selectOne(SysMerchantDO sysMerchant);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysMerchant
     *            查询条件
     * @return 对象列表
     */
    List<SysMerchantDO> selectList(SysMerchantDO sysMerchant);

    /**
     * 分页查询数据
     *
     * @param sysMerchant
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    Page<SysMerchantDO> selectPages(SysMerchantDO sysMerchant, int pageNum, int pageSize);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysMerchant
     *            查询条件
     * @return 结果集
     */
    List<SysMerchantDO> selectByIdSet(Set<Long> idSet, SysMerchantDO sysMerchant);


    /**
     * 新增
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    int insert(SysMerchantDO sysMerchant);

    /**
     * 通过主键修改
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    int updateById(SysMerchantDO sysMerchant);

    /**
     * 通过主键删除数据
     *
     * @param sysMerchant
     *            主键
     * @return 影响行数
     */
    int deleteById(SysMerchantDO sysMerchant);

}
