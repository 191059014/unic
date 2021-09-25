package com.hb.unic.rbac.dao.mapper;

import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysMerchantDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 商户表数据库层接口
 *
 * @version v0.1, 2021-09-04 12:48:33, create by Mr.Huang.
 */
public interface ISysMerchantMapper {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysMerchantDO selectById(Long id);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysMerchant
     *            查询条件
     * @return 对象列表
     */
    List<SysMerchantDO> selectList(@Param("qc") SysMerchantDO sysMerchant);

    /**
     * 条件查询总条数
     *
     * @param sysMerchant
     *            查询条件
     * @return 总条数
     */
    Long selectCount(@Param("qc") SysMerchantDO sysMerchant);

    /**
     * 查询指定行数据
     *
     * @param sysMerchant
     *            查询条件
     * @param pc
     *            分页条件
     * @return 对象列表
     */
    List<SysMerchantDO> selectPages(@Param("qc") SysMerchantDO sysMerchant, @Param("pc") PageCondition pc);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysMerchant
     *            查询条件
     * @return 结果集
     */
    List<SysMerchantDO> selectByIdSet(@Param("idSet") Set<Long> idSet, @Param("qc") SysMerchantDO sysMerchant);

    /**
     * 新增
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    int insert(@Param("qc") SysMerchantDO sysMerchant);

    /**
     * 通过主键修改
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    int updateById(@Param("qc") SysMerchantDO sysMerchant);

    /**
     * 通过主键删除数据
     *
     * @param sysMerchant
     *            主键
     * @return 影响行数
     */
    int deleteById(@Param("qc") SysMerchantDO sysMerchant);

}
