package com.hb.unic.rbac.controller;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.base.common.ErrorCode;
import com.hb.unic.base.common.Result;
import com.hb.unic.base.model.impl.AbstractBaseDO;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.util.ExcelUtils;
import com.hb.unic.common.validator.Assert;
import com.hb.unic.common.validator.Check;
import com.hb.unic.rbac.RbacContext;
import com.hb.unic.rbac.common.enums.RbacResultCode;
import com.hb.unic.rbac.common.enums.ResourceType;
import com.hb.unic.rbac.common.util.RbacUtils;
import com.hb.unic.rbac.dao.dobj.SysPermissionDO;
import com.hb.unic.rbac.dao.dobj.SysRolePermissionDO;
import com.hb.unic.rbac.dao.dobj.SysUserDO;
import com.hb.unic.rbac.dao.dobj.SysUserRoleDO;
import com.hb.unic.rbac.model.dto.ElementuiMenu;
import com.hb.unic.rbac.model.vo.request.UpdatePasswordRequest;
import com.hb.unic.rbac.service.ISysPermissionService;
import com.hb.unic.rbac.service.ISysRolePermissionService;
import com.hb.unic.rbac.service.ISysUserRoleService;
import com.hb.unic.rbac.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 用户信息表控制层
 *
 * @version v0.1, 2021-09-04 12:48:45, create by Mr.Huang.
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
public class SysUserController {

    /**
     * 用户信息表服务层
     */
    @Resource
    private ISysUserService sysUserService;

    /**
     * 用户角色信息表服务层
     */
    @Resource
    private ISysUserRoleService sysUserRoleService;

    /**
     * 权限信息表服务层
     */
    @Resource
    private ISysPermissionService sysPermissionService;

    /**
     * 角色权限关系表服务层
     */
    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 分页查询用户信息表
     *
     * @param sysUser
     *            查询条件对象
     * @param pageNum
     *            当前第几页
     * @param pageSize
     *            每页条数
     * @return 分页结果
     */
    @PreAuthorize("hasAuthority('user_manage')")
    @PostMapping("/queryPages")
    public Result<Page<SysUserDO>> queryPages(@RequestBody SysUserDO sysUser, @RequestParam("pageNum") Integer pageNum,
        @RequestParam("pageSize") Integer pageSize) {
        Assert.ifTrueThrows(Check.incorrectPageParameter(pageNum, pageSize), ErrorCode.PAGE_PARAM_ERROR);
        return Result.success(sysUserService.selectPages(sysUser, pageNum, pageSize));
    }

    /**
     * 新增用户信息表
     *
     * @param sysUser
     *            新增对象信息
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('user_manage_add')")
    @PostMapping("/save")
    @InOutLog("新增用户")
    public Result save(@RequestBody SysUserDO sysUser) {
        Assert.hasText(sysUser.getUserName(), ErrorCode.PARAM_ILLEGAL);
        Assert.hasText(sysUser.getPassword(), ErrorCode.PARAM_ILLEGAL);
        String encodePassword = new BCryptPasswordEncoder().encode(sysUser.getPassword());
        sysUser.setPassword(encodePassword);
        return Result.success(sysUserService.insert(sysUser));
    }

    /**
     * 通过主键修改用户信息表
     *
     * @param sysUser
     *            要修改的信息
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('user_manage_update')")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody SysUserDO sysUser) {
        Assert.notNull(sysUser.getId(), ErrorCode.PARAM_ILLEGAL);
        int updateRows = sysUserService.updateById(sysUser);
        if (updateRows != 1) {
            return Result.fail(ErrorCode.FAIL);
        }
        RbacContext.clear();
        return Result.success(updateRows);
    }

    /**
     * 通过主键删除用户信息表
     *
     * @param id
     *            主键
     * @return 影响的行数
     */
    @PreAuthorize("hasAuthority('user_manage_delete')")
    @GetMapping("/deleteById")
    public Result deleteById(@RequestParam("id") Long id) {
        Assert.notNull(id, ErrorCode.PARAM_ILLEGAL);
        SysUserDO sysUser = new SysUserDO();
        sysUser.setId(id);
        return Result.success(sysUserService.deleteById(sysUser));
    }

    /**
     * 更新用户的角色
     * 
     * @param roleIdSet
     *            角色id
     * @param userId
     *            用户id
     * @return 结果
     */
    @PreAuthorize("hasAuthority('user_manage_update')")
    @PostMapping("updateUserRole")
    @InOutLog("更新用户的角色")
    public Result updateUserRole(@RequestBody Set<Long> roleIdSet, @RequestParam("userId") Long userId) {
        Assert.notNull(userId, ErrorCode.PARAM_ILLEGAL);
        Assert.notEmpty(roleIdSet, ErrorCode.PARAM_ILLEGAL);
        // 删除用户的所有角色
        sysUserRoleService.deleteByUserId(userId);
        // 批量新增用户角色关系
        List<SysUserRoleDO> list = new ArrayList<>();
        for (Long roleId : roleIdSet) {
            SysUserRoleDO userRole = new SysUserRoleDO();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            list.add(userRole);
        }
        return Result.success(sysUserRoleService.insertBatch(list));
    }

    /**
     * 查询用户下的菜单列表
     *
     * @return 分页结果
     */
    @GetMapping("/getPrivateMenuDatas")
    @InOutLog("获取用户下的菜单")
    public Result<List<ElementuiMenu>> getPrivateMenuDatas() {
        List<SysPermissionDO> permissionList = null;
        if (RbacUtils.isSuperAdmin(RbacContext.getCurrentUserId())) {
            // 超级管理员
            permissionList = sysPermissionService.selectList(new SysPermissionDO());
        } else {
            Long currentUserId = RbacContext.getCurrentUserId();
            SysUserRoleDO userRoleQuery = new SysUserRoleDO();
            userRoleQuery.setUserId(currentUserId);
            // 查询用户的角色
            List<SysUserRoleDO> userRoleList = sysUserRoleService.selectList(userRoleQuery);
            if (!CollectionUtils.isEmpty(userRoleList)) {
                Set<Long> roleIdSet = userRoleList.stream().map(SysUserRoleDO::getRoleId).collect(Collectors.toSet());
                // 查询角色拥有的权限
                List<SysRolePermissionDO> rolePermissionList = sysRolePermissionService.selectByRoleIdSet(roleIdSet);
                if (!CollectionUtils.isEmpty(rolePermissionList)) {
                    Set<Long> permissionIdSet = rolePermissionList.stream().map(SysRolePermissionDO::getPermissionId)
                        .collect(Collectors.toSet());
                    permissionList = sysPermissionService.selectByIdSet(permissionIdSet, new SysPermissionDO());
                }
            }
        }
        if (CollectionUtils.isEmpty(permissionList)) {
            return Result.success();
        }
        Predicate<SysPermissionDO> predicate = p -> !ResourceType.BUTTON.getValue().equals(p.getResourceType());
        permissionList = permissionList.stream().filter(predicate).collect(Collectors.toList());
        permissionList.sort(Comparator.comparing(AbstractBaseDO::getCreateTime));
        // 将菜单按层级组装
        List<SysPermissionDO> topList =
            permissionList.stream().filter(access -> access.getParentId() == null).collect(Collectors.toList());
        List<ElementuiMenu> menuList = findChildrenMenuCycle(permissionList, topList);
        return Result.success(menuList);
    }

    /**
     * 递归查找菜单
     *
     * @param allList
     *            所有权限
     * @param childList
     *            当前权限信息
     * @return 菜单列表
     */
    private List<ElementuiMenu> findChildrenMenuCycle(List<SysPermissionDO> allList, List<SysPermissionDO> childList) {
        List<ElementuiMenu> menuList = new ArrayList<>();
        childList.forEach(access -> {
            ElementuiMenu menu =
                ElementuiMenu.builder().index(access.getId().toString()).name(access.getPermissionName())
                    .icon(access.getIcon()).url(access.getUrl()).parentIndex(access.getParentId()).build();
            List<SysPermissionDO> cList =
                allList.stream().filter(acc -> access.getId().equals(acc.getParentId())).collect(Collectors.toList());
            menu.setChildren(findChildrenMenuCycle(allList, cList));
            menuList.add(menu);
        });
        return menuList.size() > 0 ? menuList : null;
    }

    /**
     * 获取当前用户信息
     * 
     * @return 结果
     */
    @InOutLog("获取当前用户信息")
    @GetMapping("/getCurrentUser")
    public Result<SysUserDO> getCurrentUser() {
        return Result.success(RbacContext.getCurrentUser());
    }

    /**
     * 更新密码
     * 
     * @return 结果
     */
    @InOutLog("更新密码")
    @PostMapping("/updateCurrentUserPassword")
    public Result<Integer> updateCurrentUserPassword(@RequestBody UpdatePasswordRequest request) {
        SysUserDO currentUser = RbacContext.getCurrentUser();
        Assert.notNull(request.getOldPassword(), ErrorCode.PARAM_ILLEGAL);
        Assert.notNull(request.getNewPassword(), ErrorCode.PARAM_ILLEGAL);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            return Result.fail(RbacResultCode.OLD_PASSWORD_ERROR);
        }
        String newPasswordEncode = passwordEncoder.encode(request.getNewPassword());
        SysUserDO update = new SysUserDO();
        update.setId(currentUser.getId());
        update.setPassword(newPasswordEncode);

        RbacContext.clear();
        int updateRows = sysUserService.updateById(update);
        if (updateRows != 1) {
            return Result.fail(ErrorCode.FAIL);
        }
        RbacContext.clear();
        return Result.success(updateRows);
    }

    /**
     * 上传
     *
     * @param file
     *            文件
     * @return 结果
     */
    @PostMapping("/importUsers")
    public Result importUsers(@RequestParam("file") MultipartFile file) {
        List<Map<String, String>> list = ExcelUtils.parse(file);
        return Result.success();
    }

}
