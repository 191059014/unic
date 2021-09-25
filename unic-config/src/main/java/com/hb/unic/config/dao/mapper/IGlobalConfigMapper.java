package com.hb.unic.config.dao.mapper;

import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.config.dao.dobj.GlobalConfigDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 全局配置表数据库层接口
 *
 * @version v0.1, 2021-09-21 15:08:19, create by Mr.Huang.
 */
public interface IGlobalConfigMapper {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    GlobalConfigDO selectById(@Param("id") Long id);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param globalConfig
     *            查询条件
     * @return 对象列表
     */
    List<GlobalConfigDO> selectList(@Param("qc") GlobalConfigDO globalConfig);

    /**
     * 条件查询总条数
     *
     * @param globalConfig
     *            查询条件
     * @return 总条数
     */
    Long selectCount(@Param("qc") GlobalConfigDO globalConfig);

    /**
     * 分页条件查询
     *
     * @param globalConfig
     *            查询条件
     * @param pc
     *            分页条件
     * @return 对象列表
     */
    List<GlobalConfigDO> selectPages(@Param("qc") GlobalConfigDO globalConfig, @Param("pc") PageCondition pc);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param globalConfig
     *            查询条件
     * @return 结果集
     */
    List<GlobalConfigDO> selectByIdSet(@Param("idSet") Set<Long> idSet, @Param("qc") GlobalConfigDO globalConfig);

    /**
     * 新增
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    int insert(@Param("qc") GlobalConfigDO globalConfig);

    /**
     * 通过主键修改
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    int updateById(@Param("qc") GlobalConfigDO globalConfig);

    /**
     * 通过主键删除数据
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    int deleteById(@Param("qc") GlobalConfigDO globalConfig);

}
