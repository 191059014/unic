package com.hb.unic.rbac.service;

import com.hb.unic.common.standard.Page;
import com.hb.unic.rbac.dao.dobj.SysUserDO;

import java.util.List;
import java.util.Set;

/**
 * 用户信息表服务层接口
 *
 * @version v0.1, 2021-09-04 12:48:45, create by Mr.Huang.
 */
public interface ISysUserService {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysUserDO selectById(Long id);

    /**
     * 通过条件查询单条数据
     *
     * @param sysUser
     *            查询条件
     * @return 实例对象
     */
    SysUserDO selectOne(SysUserDO sysUser);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysUser
     *            查询条件
     * @return 对象列表
     */
    List<SysUserDO> selectList(SysUserDO sysUser);

    /**
     * 分页查询数据
     *
     * @param sysUser
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    Page<SysUserDO> selectPages(SysUserDO sysUser, int pageNum, int pageSize);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysUser
     *            查询条件
     * @return 结果集
     */
    List<SysUserDO> selectByIdSet(Set<Long> idSet, SysUserDO sysUser);

    /**
     * 新增
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    int insert(SysUserDO sysUser);

    /**
     * 通过主键修改
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    int updateById(SysUserDO sysUser);

    /**
     * 通过主键删除数据
     *
     * @param sysUser
     *            主键
     * @return 影响行数
     */
    int deleteById(SysUserDO sysUser);

    /**
     * 通过用户名或者手机号查询用户信息
     *
     * @param usernameOrMobile
     *            用户名或者手机号
     * @return 用户信息
     */
    SysUserDO findByUsernameOrMobile(String usernameOrMobile);

    /**
     * 通过用户id查询用户下的权限
     * 
     * @param id
     *            用户id
     * @return 权限列表
     */
    Set<String> findPermissions(Long id);
}
