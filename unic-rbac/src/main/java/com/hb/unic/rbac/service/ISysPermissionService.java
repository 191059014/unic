package com.hb.unic.rbac.service;

import com.hb.unic.common.standard.Page;
import com.hb.unic.rbac.dao.dobj.SysPermissionDO;

import java.util.List;
import java.util.Set;

/**
 * 权限信息表服务层接口
 *
 * @version v0.1, 2021-09-04 12:48:40, create by Mr.Huang.
 */
public interface ISysPermissionService {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysPermissionDO selectById(Long id);

    /**
     * 通过条件查询单条数据
     *
     * @param sysPermission
     *            查询条件
     * @return 实例对象
     */
    SysPermissionDO selectOne(SysPermissionDO sysPermission);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysPermission
     *            查询条件
     * @return 对象列表
     */
    List<SysPermissionDO> selectList(SysPermissionDO sysPermission);

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
    Page<SysPermissionDO> selectPages(SysPermissionDO sysPermission, int pageNum, int pageSize);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysPermission
     *            查询条件
     * @return 结果集
     */
    List<SysPermissionDO> selectByIdSet(Set<Long> idSet, SysPermissionDO sysPermission);

    /**
     * 新增
     *
     * @param sysPermission
     *            实例对象
     * @return 影响行数
     */
    int insert(SysPermissionDO sysPermission);

    /**
     * 通过主键修改
     *
     * @param sysPermission
     *            实例对象
     * @return 影响行数
     */
    int updateById(SysPermissionDO sysPermission);

    /**
     * 通过主键删除数据
     *
     * @param sysPermission
     *            主键
     * @return 影响行数
     */
    int deleteById(SysPermissionDO sysPermission);

    /**
     * 分页查询数据
     *
     * @param idSet
     *            id集合
     * @param sysPermission
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    Page<SysPermissionDO> selectPagesByIdSet(Set<Long> idSet, SysPermissionDO sysPermission, Integer pageNum,
        Integer pageSize);

}
