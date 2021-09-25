package com.hb.unic.config.service;

import com.hb.unic.common.standard.Page;
import com.hb.unic.config.dao.dobj.ExceptionBoardDO;
import com.hb.unic.config.enums.ErrorProcessState;
import com.hb.unic.config.enums.ErrorType;

import java.util.List;
import java.util.Set;

/**
 * 异常看板表服务层接口
 *
 * @version v0.1, 2021-09-23 22:17:34, create by Mr.Huang.
 */
public interface IExceptionBoardService {

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    ExceptionBoardDO selectById(Long id);

    /**
     * 通过条件查询单条数据
     *
     * @param exceptionBoard
     *            查询条件
     * @return 实例对象
     */
    ExceptionBoardDO selectOne(ExceptionBoardDO exceptionBoard);

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param exceptionBoard
     *            查询条件
     * @return 对象列表
     */
    List<ExceptionBoardDO> selectList(ExceptionBoardDO exceptionBoard);

    /**
     * 分页查询数据
     *
     * @param exceptionBoard
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    Page<ExceptionBoardDO> selectPages(ExceptionBoardDO exceptionBoard, int pageNum, int pageSize);

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param exceptionBoard
     *            查询条件
     * @return 结果集
     */
    List<ExceptionBoardDO> selectByIdSet(Set<Long> idSet, ExceptionBoardDO exceptionBoard);

    /**
     * 新增
     *
     * @param errorType
     *            错误类型
     * @param processState
     *            处理状态
     * @param content
     *            内容
     * @param remark
     *            备注
     * @return 影响行行数
     */
    int insert(ErrorType errorType, ErrorProcessState processState, String content, String remark);

    /**
     * 通过主键修改
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    int updateById(ExceptionBoardDO exceptionBoard);

    /**
     * 通过主键删除数据
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    int deleteById(ExceptionBoardDO exceptionBoard);

}
