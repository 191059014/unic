package com.hb.unic.rbac.service.impl;

import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysMerchantDO;
import com.hb.unic.rbac.dao.mapper.ISysMerchantMapper;
import com.hb.unic.rbac.service.ISysMerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 商户表服务层实现类
 *
 * @version v0.1, 2021-09-04 12:48:33, create by Mr.Huang.
 */
@Service
public class SysMerchantServiceImpl implements ISysMerchantService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SysMerchantServiceImpl.class);

    /**
     * 商户表操作数据库层
     */
    @Resource
    private ISysMerchantMapper sysMerchantMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public SysMerchantDO selectById(Long id) {
        return this.sysMerchantMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param sysMerchant
     *            查询条件
     * @return 对象列表
     */
    @Override
    public SysMerchantDO selectOne(SysMerchantDO sysMerchant) {
        List<SysMerchantDO> list = this.sysMerchantMapper.selectList(sysMerchant);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysMerchant
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<SysMerchantDO> selectList(SysMerchantDO sysMerchant) {
        return this.sysMerchantMapper.selectList(sysMerchant);
    }

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
    @Override
    public Page<SysMerchantDO> selectPages(SysMerchantDO sysMerchant, int pageNum, int pageSize) {
        Long count = this.sysMerchantMapper.selectCount(sysMerchant);
        List<SysMerchantDO> dataList =
            this.sysMerchantMapper.selectPages(sysMerchant, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysMerchant
     *            查询条件
     * @return 结果集
     */
    @Override
    public List<SysMerchantDO> selectByIdSet(Set<Long> idSet, SysMerchantDO sysMerchant) {
        return this.sysMerchantMapper.selectByIdSet(idSet, sysMerchant);
    }

    /**
     * 选择性新增
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysMerchantDO sysMerchant) {
        return this.sysMerchantMapper.insert(sysMerchant);
    }

    /**
     * 通过主键修改
     *
     * @param sysMerchant
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(SysMerchantDO sysMerchant) {
        return this.sysMerchantMapper.updateById(sysMerchant);
    }

    /**
     * 通过主键删除数据
     *
     * @param sysMerchant
     *            主键
     * @return 影响行数
     */
    @Override
    public int deleteById(SysMerchantDO sysMerchant) {
        return this.sysMerchantMapper.deleteById(sysMerchant);
    }

}
