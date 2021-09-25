package com.hb.unic.rbac.service.impl;

import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.mapper.ISysUserMapper;
import com.hb.unic.rbac.dao.dobj.SysPermissionDO;
import com.hb.unic.rbac.dao.dobj.SysRolePermissionDO;
import com.hb.unic.rbac.dao.dobj.SysUserDO;
import com.hb.unic.rbac.dao.dobj.SysUserRoleDO;
import com.hb.unic.rbac.service.ISysPermissionService;
import com.hb.unic.rbac.service.ISysRolePermissionService;
import com.hb.unic.rbac.service.ISysRoleService;
import com.hb.unic.rbac.service.ISysUserRoleService;
import com.hb.unic.rbac.service.ISysUserService;
import com.hb.unic.rbac.common.util.RbacUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户信息表服务层实现类
 *
 * @version v0.1, 2021-09-04 12:48:45, create by Mr.Huang.
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 用户信息表操作数据库层
     */
    @Resource
    private ISysUserMapper sysUserMapper;

    /**
     * 用户角色关系表操作服务层
     */
    @Resource
    private ISysUserRoleService sysUserRoleService;

    /**
     * 角色表操作服务层
     */
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 角色权限关系表操作服务层
     */
    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 权限表操作服务层
     */
    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public SysUserDO selectById(Long id) {
        return this.sysUserMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param sysUser
     *            查询条件
     * @return 对象列表
     */
    @Override
    public SysUserDO selectOne(SysUserDO sysUser) {
        List<SysUserDO> list = this.sysUserMapper.selectList(sysUser);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysUser
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<SysUserDO> selectList(SysUserDO sysUser) {
        return this.sysUserMapper.selectList(sysUser);
    }

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
    @Override
    public Page<SysUserDO> selectPages(SysUserDO sysUser, int pageNum, int pageSize) {
        Long count = this.sysUserMapper.selectCount(sysUser);
        List<SysUserDO> dataList = this.sysUserMapper.selectPages(sysUser, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysUser
     *            查询条件
     * @return 结果集
     */
    @Override
    public List<SysUserDO> selectByIdSet(Set<Long> idSet, SysUserDO sysUser) {
        return this.sysUserMapper.selectByIdSet(idSet, sysUser);
    }

    /**
     * 选择性新增
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysUserDO sysUser) {
        return this.sysUserMapper.insert(sysUser);
    }

    /**
     * 通过主键修改
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(SysUserDO sysUser) {
        return this.sysUserMapper.updateById(sysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param sysUser
     *            主键
     * @return 影响行数
     */
    @Override
    public int deleteById(SysUserDO sysUser) {
        return this.sysUserMapper.deleteById(sysUser);
    }

    @Override
    public SysUserDO findByUsernameOrMobile(String usernameOrMobile) {
        return this.sysUserMapper.findByUsernameOrMobile(usernameOrMobile);
    }

    @Override
    public Set<String> findPermissions(Long id) {
        if (RbacUtils.isSuperAdmin(id)) {
            // 超级管理员
            List<SysPermissionDO> permissionList = sysPermissionService.selectList(new SysPermissionDO());
            return permissionList.stream().map(SysPermissionDO::getPermissionValue).collect(Collectors.toSet());
        }
        SysUserRoleDO userRoleQuery = new SysUserRoleDO();
        userRoleQuery.setUserId(id);
        List<SysUserRoleDO> userRoleList = sysUserRoleService.selectList(userRoleQuery);
        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        Set<Long> roleIdSet = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toSet());
        List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.selectByRoleIdSet(roleIdSet);
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return null;
        }
        Set<Long> permissionIdSet =
            rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());
        List<SysPermissionDO> permissionList =
            sysPermissionService.selectByIdSet(permissionIdSet, new SysPermissionDO());
        if (CollectionUtils.isEmpty(permissionList)) {
            return null;
        }
        return permissionList.stream().map(SysPermissionDO::getPermissionValue).collect(Collectors.toSet());
    }

}
