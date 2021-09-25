package com.hb.unic.config.dao.mapper;

import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.config.dao.dobj.ExceptionBoardDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 异常看板表数据库层接口
 *
 * @version v0.1, 2021-09-23 22:17:34, create by Mr.Huang.
 */
public interface IExceptionBoardMapper {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    ExceptionBoardDO selectById(@Param("id") Long id);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param exceptionBoard
     *            查询条件
     * @return 对象列表
     */
    List<ExceptionBoardDO> selectList(@Param("qc") ExceptionBoardDO exceptionBoard);

    /**
     * 条件查询总条数
     *
     * @param exceptionBoard
     *            查询条件
     * @return 总条数
     */
    Long selectCount(@Param("qc") ExceptionBoardDO exceptionBoard);

    /**
     * 分页条件查询
     *
     * @param exceptionBoard
     *            查询条件
     * @param pc
     *            分页条件
     * @return 对象列表
     */
    List<ExceptionBoardDO> selectPages(@Param("qc") ExceptionBoardDO exceptionBoard, @Param("pc") PageCondition pc);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param exceptionBoard
     *            查询条件
     * @return 结果集
     */
    List<ExceptionBoardDO> selectByIdSet(@Param("idSet") Set<Long> idSet, @Param("qc") ExceptionBoardDO exceptionBoard);

    /**
     * 新增
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    int insert(@Param("qc") ExceptionBoardDO exceptionBoard);

    /**
     * 通过主键修改
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    int updateById(@Param("qc") ExceptionBoardDO exceptionBoard);

    /**
     * 通过主键删除数据
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    int deleteById(@Param("qc") ExceptionBoardDO exceptionBoard);

}
