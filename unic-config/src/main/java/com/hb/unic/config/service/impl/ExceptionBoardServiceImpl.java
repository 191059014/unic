package com.hb.unic.config.service.impl;

import com.hb.unic.base.constant.BaseConsts;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.config.dao.dobj.ExceptionBoardDO;
import com.hb.unic.config.dao.mapper.IExceptionBoardMapper;
import com.hb.unic.config.enums.ErrorProcessState;
import com.hb.unic.config.enums.ErrorType;
import com.hb.unic.config.service.IExceptionBoardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 异常看板表服务层实现类
 *
 * @version v0.1, 2021-09-23 22:17:34, create by Mr.Huang.
 */
@Slf4j
@Service
public class ExceptionBoardServiceImpl implements IExceptionBoardService {

    /**
     * 异常看板表操作数据库层
     */
    @Resource
    private IExceptionBoardMapper exceptionBoardMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public ExceptionBoardDO selectById(Long id) {
        return this.exceptionBoardMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param exceptionBoard
     *            查询条件
     * @return 对象列表
     */
    @Override
    public ExceptionBoardDO selectOne(ExceptionBoardDO exceptionBoard) {
        List<ExceptionBoardDO> list = this.exceptionBoardMapper.selectList(exceptionBoard);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param exceptionBoard
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<ExceptionBoardDO> selectList(ExceptionBoardDO exceptionBoard) {
        return this.exceptionBoardMapper.selectList(exceptionBoard);
    }

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
    @Override
    public Page<ExceptionBoardDO> selectPages(ExceptionBoardDO exceptionBoard, int pageNum, int pageSize) {
        Long count = this.exceptionBoardMapper.selectCount(exceptionBoard);
        List<ExceptionBoardDO> dataList =
            this.exceptionBoardMapper.selectPages(exceptionBoard, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param exceptionBoard
     *            查询条件
     * @return 结果集
     */
    @Override
    public List<ExceptionBoardDO> selectByIdSet(Set<Long> idSet, ExceptionBoardDO exceptionBoard) {
        return this.exceptionBoardMapper.selectByIdSet(idSet, exceptionBoard);
    }

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
    @Override
    public int insert(ErrorType errorType, ErrorProcessState processState, String content, String remark) {
        ExceptionBoardDO exceptionBoard = new ExceptionBoardDO();
        exceptionBoard.setSystemName(errorType.getSystemName());
        exceptionBoard.setBizType(errorType.getBizType());
        exceptionBoard.setProcessState(processState.getValue());
        exceptionBoard.setContent(content);
        exceptionBoard.setRemark(remark);
        exceptionBoard.setTraceId(MDC.get(BaseConsts.TRACE_ID));
        return this.exceptionBoardMapper.insert(exceptionBoard);
    }

    /**
     * 通过主键修改
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(ExceptionBoardDO exceptionBoard) {
        return this.exceptionBoardMapper.updateById(exceptionBoard);
    }

    /**
     * 通过主键删除数据
     *
     * @param exceptionBoard
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int deleteById(ExceptionBoardDO exceptionBoard) {
        return this.exceptionBoardMapper.deleteById(exceptionBoard);
    }

}
