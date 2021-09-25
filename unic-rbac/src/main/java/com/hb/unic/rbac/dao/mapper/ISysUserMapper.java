package com.hb.unic.rbac.dao.mapper;

import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.rbac.dao.dobj.SysUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户信息表数据库层接口
 *
 * @version v0.1, 2021-09-04 12:48:45, create by Mr.Huang.
 */
public interface ISysUserMapper {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    SysUserDO selectById(Long id);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param sysUser
     *            查询条件
     * @return 对象列表
     */
    List<SysUserDO> selectList(@Param("qc") SysUserDO sysUser);

    /**
     * 条件查询总条数
     *
     * @param sysUser
     *            查询条件
     * @return 总条数
     */
    Long selectCount(@Param("qc") SysUserDO sysUser);

    /**
     * 查询指定行数据
     *
     * @param sysUser
     *            查询条件
     * @param pc
     *            分页条件
     * @return 对象列表
     */
    List<SysUserDO> selectPages(@Param("qc") SysUserDO sysUser, @Param("pc") PageCondition pc);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param sysUser
     *            查询条件
     * @return 结果集
     */
    List<SysUserDO> selectByIdSet(@Param("idSet") Set<Long> idSet, @Param("qc") SysUserDO sysUser);

    /**
     * 新增
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    int insert(@Param("qc") SysUserDO sysUser);

    /**
     * 通过主键修改
     *
     * @param sysUser
     *            实例对象
     * @return 影响行数
     */
    int updateById(@Param("qc") SysUserDO sysUser);

    /**
     * 通过主键删除数据
     *
     * @param sysUser
     *            主键
     * @return 影响行数
     */
    int deleteById(@Param("qc") SysUserDO sysUser);

    /**
     * 通过用户名或者手机号查询用户信息
     *
     * @param usernameOrMobile
     *            用户名或者手机号
     * @return 用户信息
     */
    SysUserDO findByUsernameOrMobile(@Param("usernameOrMobile") String usernameOrMobile);
}
