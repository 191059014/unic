package com.hb.unic.rbac.service.impl;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysRolePermissionDO;
import com.hb.unic.rbac.dao.mapper.ISysRolePermissionMapper;
import com.hb.unic.rbac.service.ISysRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 角色权限关系表服务层实现类
 *
 * @version v0.1, 2021-09-04 12:48:44, create by Mr.Huang.
 */
@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRolePermissionServiceImpl.class);

    /**
     * 角色权限关系表操作数据库层
     */
    @Resource
    private ISysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public SysRolePermissionDO selectById(Long id) {
        return this.sysRolePermissionMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param sysRolePermission
     *            查询条件
     * @return 对象列表
     */
    @Override
    public SysRolePermissionDO selectOne(SysRolePermissionDO sysRolePermission) {
        List<SysRolePermissionDO> list = this.sysRolePermissionMapper.selectList(sysRolePermission);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysRolePermission
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<SysRolePermissionDO> selectList(SysRolePermissionDO sysRolePermission) {
        return this.sysRolePermissionMapper.selectList(sysRolePermission);
    }

    /**
     * 分页查询数据
     *
     * @param sysRolePermission
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    @Override
    public Page<SysRolePermissionDO> selectPages(SysRolePermissionDO sysRolePermission, int pageNum, int pageSize) {
        Long count = this.sysRolePermissionMapper.selectCount(sysRolePermission);
        List<SysRolePermissionDO> dataList =
            this.sysRolePermissionMapper.selectPages(sysRolePermission, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param roleIdSet
     *            id集合
     * @return 结果集
     */
    @Override
    public List<SysRolePermissionDO> selectByRoleIdSet(Set<Long> roleIdSet) {
        return this.sysRolePermissionMapper.selectByRoleIdSet(roleIdSet);
    }

    /**
     * 选择性新增
     *
     * @param sysRolePermission
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysRolePermissionDO sysRolePermission) {
        return this.sysRolePermissionMapper.insert(sysRolePermission);
    }

    /**
     * 通过主键修改
     *
     * @param sysRolePermission
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(SysRolePermissionDO sysRolePermission) {
        return this.sysRolePermissionMapper.updateById(sysRolePermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 影响行数
     */
    @Override
    public int deleteById(Long id) {
        return this.sysRolePermissionMapper.deleteById(id);
    }

    @InOutLog("删除角色下的所有权限")
    @Override
    public int deleteByRoleId(Long roleId) {
        return sysRolePermissionMapper.deleteByRoleId(roleId);
    }

    @InOutLog(value = "批量插入角色权限关系", printInLog = false)
    @Override
    public int insertBatch(List<SysRolePermissionDO> list) {
        return sysRolePermissionMapper.insertBatch(list);
    }

}
