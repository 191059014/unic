package com.hb.unic.rbac.dao.mapper;

import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysUserRoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户角色关系表数据库层接口
 *
 * @version v0.1, 2021-09-04 12:48:47, create by Mr.Huang.
 */
public interface ISysUserRoleMapper {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysUserRoleDO selectById(Long id);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysUserRole
     *            查询条件
     * @return 对象列表
     */
    List<SysUserRoleDO> selectList(@Param("qc") SysUserRoleDO sysUserRole);

    /**
     * 条件查询总条数
     *
     * @param sysUserRole
     *            查询条件
     * @return 总条数
     */
    Long selectCount(@Param("qc") SysUserRoleDO sysUserRole);

    /**
     * 查询指定行数据
     *
     * @param sysUserRole
     *            查询条件
     * @param pc
     *            分页条件
     * @return 对象列表
     */
    List<SysUserRoleDO> selectPages(@Param("qc") SysUserRoleDO sysUserRole, @Param("pc") PageCondition pc);

    /**
     * 通过id集合查询
     *
     * @param roleIdSet
     *            id集合
     * @return 结果集
     */
    List<SysUserRoleDO> selectByRoleIdSet(@Param("roleIdSet") Set<Long> roleIdSet);

    /**
     * 新增
     *
     * @param sysUserRole
     *            实例对象
     * @return 影响行数
     */
    int insert(@Param("qc") SysUserRoleDO sysUserRole);

    /**
     * 通过主键修改
     *
     * @param sysUserRole
     *            实例对象
     * @return 影响行数
     */
    int updateById(@Param("qc") SysUserRoleDO sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param id
     *            主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 删除用户下的所有角色
     *
     * @param userId
     *            用户id
     * @return 影响行数
     */
    int deleteByUserId(@Param("userId") Long userId);

    /**
     * 批量新增用户角色关系
     *
     * @param list
     *            用户角色关系
     * @return 影响行数
     */
    int insertBatch(@Param("list") List<SysUserRoleDO> list);
}
