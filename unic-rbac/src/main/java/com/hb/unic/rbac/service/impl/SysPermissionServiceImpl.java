package com.hb.unic.rbac.service.impl;

import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysPermissionDO;
import com.hb.unic.rbac.dao.mapper.ISysPermissionMapper;
import com.hb.unic.rbac.service.ISysPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 权限信息表服务层实现类
 *
 * @version v0.1, 2021-09-04 12:48:40, create by Mr.Huang.
 */
@Service
@Slf4j
public class SysPermissionServiceImpl implements ISysPermissionService {

    /**
     * 权限信息表操作数据库层
     */
    @Resource
    private ISysPermissionMapper sysPermissionMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public SysPermissionDO selectById(Long id) {
        return this.sysPermissionMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param sysPermission
     *            查询条件
     * @return 对象列表
     */
    @Override
    public SysPermissionDO selectOne(SysPermissionDO sysPermission) {
        List<SysPermissionDO> list = this.sysPermissionMapper.selectList(sysPermission);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysPermission
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<SysPermissionDO> selectList(SysPermissionDO sysPermission) {
        return this.sysPermissionMapper.selectList(sysPermission);
    }

    /**
     * 分页查询数据
     *
     * @param sysPermission
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    @Override
    public Page<SysPermissionDO> selectPages(SysPermissionDO sysPermission, int pageNum, int pageSize) {
        Long count = this.sysPermissionMapper.selectCount(sysPermission);
        List<SysPermissionDO> dataList =
            this.sysPermissionMapper.selectPages(sysPermission, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysPermission
     *            查询条件
     * @return 结果集
     */
    @Override
    public List<SysPermissionDO> selectByIdSet(Set<Long> idSet, SysPermissionDO sysPermission) {
        return this.sysPermissionMapper.selectByIdSet(idSet, sysPermission);
    }

    /**
     * 选择性新增
     *
     * @param sysPermission
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysPermissionDO sysPermission) {
        return this.sysPermissionMapper.insert(sysPermission);
    }

    /**
     * 通过主键修改
     *
     * @param sysPermission
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(SysPermissionDO sysPermission) {
        return this.sysPermissionMapper.updateById(sysPermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param sysPermission
     *            主键
     * @return 影响行数
     */
    @Override
    public int deleteById(SysPermissionDO sysPermission) {
        return this.sysPermissionMapper.deleteById(sysPermission);
    }

    @Override
    public Page<SysPermissionDO> selectPagesByIdSet(Set<Long> idSet, SysPermissionDO sysPermission, Integer pageNum,
        Integer pageSize) {
        Long count = this.sysPermissionMapper.selectCountByIdSet(idSet, sysPermission);
        List<SysPermissionDO> dataList =
            this.sysPermissionMapper.selectPagesByIdSet(idSet, sysPermission, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

}
