package com.hb.unic.rbac.service;

import com.hb.unic.common.standard.Page;
import com.hb.unic.rbac.dao.dobj.SysUserRoleDO;

import java.util.List;
import java.util.Set;

/**
 * 用户角色关系表服务层接口
 *
 * @version v0.1, 2021-09-04 12:48:47, create by Mr.Huang.
 */
public interface ISysUserRoleService {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysUserRoleDO selectById(Long id);

    /**
     * 通过条件查询单条数据
     *
     * @param sysUserRole
     *            查询条件
     * @return 实例对象
     */
    SysUserRoleDO selectOne(SysUserRoleDO sysUserRole);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysUserRole
     *            查询条件
     * @return 对象列表
     */
    List<SysUserRoleDO> selectList(SysUserRoleDO sysUserRole);

    /**
     * 分页查询数据
     *
     * @param sysUserRole
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    Page<SysUserRoleDO> selectPages(SysUserRoleDO sysUserRole, int pageNum, int pageSize);

    /**
     * 通过id集合查询
     *
     * @param roleIdSet
     *            id集合
     * @return 结果集
     */
    List<SysUserRoleDO> selectByRoleIdSet(Set<Long> roleIdSet);

    /**
     * 新增
     *
     * @param sysUserRole
     *            实例对象
     * @return 影响行数
     */
    int insert(SysUserRoleDO sysUserRole);

    /**
     * 通过主键修改
     *
     * @param sysUserRole
     *            实例对象
     * @return 影响行数
     */
    int updateById(SysUserRoleDO sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 删除用户下的所有角色
     * 
     * @param userId
     *            用户id
     * @return 影响行数
     */
    int deleteByUserId(Long userId);

    /**
     * 批量新增用户角色关系
     * 
     * @param list
     *            用户角色关系
     * @return 影响行数
     */
    int insertBatch(List<SysUserRoleDO> list);
}
