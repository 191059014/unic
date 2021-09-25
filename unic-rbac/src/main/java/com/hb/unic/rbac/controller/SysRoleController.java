package com.hb.unic.rbac.controller;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ResultCode;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.validator.Assert;
import com.hb.unic.common.validator.Check;
import com.hb.unic.rbac.RbacContext;
import com.hb.unic.rbac.common.util.RbacUtils;
import com.hb.unic.rbac.dao.dobj.SysPermissionDO;
import com.hb.unic.rbac.dao.dobj.SysRoleDO;
import com.hb.unic.rbac.dao.dobj.SysRolePermissionDO;
import com.hb.unic.rbac.dao.dobj.SysUserRoleDO;
import com.hb.unic.rbac.model.dto.ElementuiTree;
import com.hb.unic.rbac.service.ISysPermissionService;
import com.hb.unic.rbac.service.ISysRolePermissionService;
import com.hb.unic.rbac.service.ISysRoleService;
import com.hb.unic.rbac.service.ISysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色信息表控制层
 *
 * @version v0.1, 2021-09-04 12:48:42, create by Mr.Huang.
 */
@RestController
@RequestMapping("/sysRole")
@Slf4j
public class SysRoleController {

    /**
     * 角色信息表服务层
     */
    @Resource
    private ISysRoleService sysRoleService;

    /**
     * 用户角色关系表服务层
     */
    @Resource
    private ISysUserRoleService sysUserRoleService;

    /**
     * 角色权限关系表服务层
     */
    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 权限信息表服务层
     */
    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * 分页查询角色信息表
     *
     * @param sysRole
     *            查询条件对象
     * @param pageNum
     *            当前第几页
     * @param pageSize
     *            每页条数
     * @return 分页结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @PostMapping("/queryPages")
    public Result<Page<SysRoleDO>> queryPages(@RequestBody SysRoleDO sysRole, @RequestParam("pageNum") Integer pageNum,
        @RequestParam("pageSize") Integer pageSize) {
        Assert.ifTrueThrows(Check.incorrectPageParameter(pageNum, pageSize), ResultCode.PAGE_PARAM_ERROR);
        return Result.success(sysRoleService.selectPages(sysRole, pageNum, pageSize));
    }

    /**
     * 新增角色信息表
     *
     * @param sysRole
     *            新增对象信息
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('role_manage_add')")
    @PostMapping("/save")
    public Result save(@RequestBody SysRoleDO sysRole) {
        Assert.hasText(sysRole.getRoleName(), ResultCode.PARAM_ILLEGAL);
        return Result.success(sysRoleService.insert(sysRole));
    }

    /**
     * 通过主键修改角色信息表
     *
     * @param sysRole
     *            要修改的信息
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('role_manage_update')")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody SysRoleDO sysRole) {
        Assert.notNull(sysRole.getId(), ResultCode.PARAM_ILLEGAL);
        return Result.success(sysRoleService.updateById(sysRole));
    }

    /**
     * 通过主键删除角色信息表
     *
     * @param id
     *            主键
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('role_manage_delete')")
    @GetMapping("/deleteById")
    public Result deleteById(@RequestParam("id") Long id) {
        Assert.notNull(id, ResultCode.PARAM_ILLEGAL);
        SysRoleDO sysRole = new SysRoleDO();
        sysRole.setId(id);
        return Result.success(sysRoleService.deleteById(sysRole));
    }

    /**
     * 获取当前用户对应商户下的所有角色
     *
     * @return 结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @GetMapping("/getRolesUnderMerchant")
    @InOutLog("获取当前用户对应商户下的所有角色")
    public Result<List<SysRoleDO>> getRolesUnderMerchant() {
        return Result.success(sysRoleService.selectList(new SysRoleDO()));
    }

    /**
     * 获取指定用户拥有的角色
     *
     * @return 结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @GetMapping("/getRolesUnderUser")
    @InOutLog("获取指定用户拥有的角色")
    public Result<Set<Long>> getRolesUnderUser(@RequestParam("userId") Long userId) {
        Assert.notNull(userId, ResultCode.PARAM_ILLEGAL);
        SysUserRoleDO userRoleQuery = new SysUserRoleDO();
        userRoleQuery.setUserId(userId);
        List<SysUserRoleDO> userRoleList = sysUserRoleService.selectList(userRoleQuery);
        Set<Long> roleIdSet = null;
        if (!CollectionUtils.isEmpty(userRoleList)) {
            roleIdSet = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toSet());
        }
        return Result.success(roleIdSet);
    }

    /**
     * 更新角色对应的权限
     * 
     * @param permissionIdSet
     *            权限集合
     * @param roleId
     *            角色id
     * @return 结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @PostMapping("/updateRolePermission")
    @InOutLog("更新角色对应的权限")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateRolePermission(@RequestBody Set<Long> permissionIdSet, @RequestParam("roleId") Long roleId) {
        Assert.notNull(roleId, ResultCode.PARAM_ILLEGAL);
        Assert.notEmpty(permissionIdSet, ResultCode.PARAM_ILLEGAL);
        // 删除角色下所有的权限
        sysRolePermissionService.deleteByRoleId(roleId);
        // 新增权限
        List<SysRolePermissionDO> list = new ArrayList<>();
        for (Long permissionId : permissionIdSet) {
            SysRolePermissionDO sysRolePermission = new SysRolePermissionDO();
            sysRolePermission.setRoleId(roleId);
            sysRolePermission.setPermissionId(permissionId);
            list.add(sysRolePermission);
        }
        return Result.success(sysRolePermissionService.insertBatch(list));
    }

    /**
     * 获取指定角色的所有权限
     *
     * @return 结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @InOutLog("获取指定角色的所有权限")
    @GetMapping("/getPermissionsUnderRole")
    public Result<Set<Long>> getPermissionsUnderRole(@RequestParam("roleId") Long roleId) {
        Assert.notNull(roleId, ResultCode.PARAM_ILLEGAL);
        SysRolePermissionDO rolePermissionQuery = new SysRolePermissionDO();
        rolePermissionQuery.setRoleId(roleId);
        List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.selectList(rolePermissionQuery);
        if (CollectionUtils.isEmpty(rolePermissionList)) {
            return Result.success();
        }
        Set<Long> permissionIdSet =
            rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId).collect(Collectors.toSet());
        // 只返回叶子节点，防止父节点选中，导致子节点全部选中的问题
        List<SysPermissionDO> permissionList =
            sysPermissionService.selectByIdSet(permissionIdSet, new SysPermissionDO());
        Set<Long> parentPermissionIdSet =
            permissionList.stream().map(SysPermissionDO::getParentId).collect(Collectors.toSet());
        permissionIdSet.removeAll(parentPermissionIdSet);
        return Result.success(permissionIdSet);
    }

    /**
     * 获取当前用户对应商户下的所有角色的所有权限
     *
     * @return 结果
     */
    @PreAuthorize("hasAuthority('role_manage')")
    @GetMapping("/getPermissionTreeUnderMerchant")
    @InOutLog("获取当前用户对应商户下的所有角色的所有权限")
    public Result<List<ElementuiTree>> getPermissionTreeUnderMerchant(@RequestParam("tenantId") Long tenantId) {
        List<SysPermissionDO> permissionList = null;
        if (RbacUtils.isSuperAdmin(RbacContext.getCurrentUserId())) {
            // 如果当前用户是超级管理员，则返回所有的权限，防止给刚新增的角色赋权的时候，没有权限可选
            permissionList = sysPermissionService.selectList(new SysPermissionDO());
        } else {
            // 查询当前商户下的所有角色对应的权限
            permissionList = sysRoleService.getPermissionListUnderTenantRole(tenantId);
        }
        if (CollectionUtils.isEmpty(permissionList)) {
            return Result.success();
        }
        List<SysPermissionDO> topList =
            permissionList.stream().filter(access -> access.getParentId() == null).collect(Collectors.toList());
        List<ElementuiTree> treeDataList = findTreeCycle(permissionList, topList);
        return Result.success(treeDataList);
    }

    /**
     * 递归获取权限树
     *
     * @return 权限树
     */
    private List<ElementuiTree> findTreeCycle(List<SysPermissionDO> allList, List<SysPermissionDO> childList) {
        List<ElementuiTree> treeDataList = new ArrayList<>();
        for (SysPermissionDO access : childList) {
            ElementuiTree treeData =
                ElementuiTree.builder().id(access.getId()).label(access.getPermissionName()).build();
            List<SysPermissionDO> cList =
                allList.stream().filter(acc -> access.getId().equals(acc.getParentId())).collect(Collectors.toList());
            treeData.setChildren(findTreeCycle(allList, cList));
            treeDataList.add(treeData);
        }
        return treeDataList.size() > 0 ? treeDataList : null;
    }

}
