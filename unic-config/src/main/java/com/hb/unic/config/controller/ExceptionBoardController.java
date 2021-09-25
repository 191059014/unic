package com.hb.unic.config.controller;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ErrorCode;
import com.hb.unic.common.standard.Dropdown;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.validator.Assert;
import com.hb.unic.common.validator.Check;
import com.hb.unic.config.dao.dobj.ExceptionBoardDO;
import com.hb.unic.config.enums.ErrorProcessState;
import com.hb.unic.config.enums.ErrorType;
import com.hb.unic.config.service.IExceptionBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 异常看板表控制层
 *
 * @version v0.1, 2021-09-23 22:17:34, create by Mr.Huang.
 */
@Slf4j
@RestController
@RequestMapping("/exceptionBoard")
public class ExceptionBoardController {

    /**
     * 异常看板表服务层
     */
    @Resource
    private IExceptionBoardService exceptionBoardService;

    /**
     * 分页查询异常看板表
     *
     * @param exceptionBoard
     *            查询条件对象
     * @param pageNum
     *            当前第几页
     * @param pageSize
     *            每页条数
     * @return 分页结果
     */
    @PostMapping("/queryPages")
    public Result<Page<ExceptionBoardDO>> queryPages(@RequestBody ExceptionBoardDO exceptionBoard,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Assert.ifTrueThrows(Check.incorrectPageParameter(pageNum, pageSize), ErrorCode.PAGE_PARAM_ERROR);
        return Result.success(exceptionBoardService.selectPages(exceptionBoard, pageNum, pageSize));
    }

    /**
     * 通过主键修改异常看板表
     *
     * @param exceptionBoard
     *            要修改的信息
     * @return 影响的行数
     */
    @InOutLog("通过主键修改异常看板表")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody ExceptionBoardDO exceptionBoard) {
        Assert.notNull(exceptionBoard.getId(), ErrorCode.PARAM_ILLEGAL);
        return Result.success(exceptionBoardService.updateById(exceptionBoard));
    }

    /**
     * 获取异常系统名称下拉框
     *
     * @return 结果
     */
    @GetMapping("/dropdown/systemName")
    public Result<List<Dropdown>> getSystemNames() {
        List<Dropdown> list = new ArrayList<>();
        Arrays.stream(ErrorType.values())
            .forEach(obj -> list.add(new Dropdown(obj.getSystemNameDesc(), obj.getSystemName())));
        return Result.success(list);
    }

    /**
     * 通过系统名称获取异常业务类型下拉框
     *
     * @return 结果
     */
    @GetMapping("/dropdown/bizTypeBySystemName")
    public Result<List<Dropdown>> getBizTypesBySystemName(@RequestParam("systemName") String systemName) {
        List<Dropdown> list = new ArrayList<>();
        List<ErrorType> errorTypeList = ErrorType.getBySystemName(systemName);
        if (!CollectionUtils.isEmpty(errorTypeList)) {
            errorTypeList.forEach(err -> list.add(new Dropdown(err.getBizTypeDesc(), err.getBizType())));
        }
        return Result.success(list);
    }

    /**
     * 获取异常业务类型下拉框
     *
     * @return 结果
     */
    @GetMapping("/dropdown/bizTypes")
    public Result<List<Dropdown>> getBizTypes() {
        List<Dropdown> list = new ArrayList<>();
        Arrays.stream(ErrorType.values())
            .forEach(obj -> list.add(new Dropdown(obj.getBizTypeDesc(), obj.getBizType())));
        return Result.success(list);
    }

    /**
     * 获取异常处理状态下拉框
     *
     * @return 结果
     */
    @GetMapping("/dropdown/processState")
    public Result<List<Dropdown>> getErrorProcessStates() {
        List<Dropdown> list = new ArrayList<>();
        Arrays.stream(ErrorProcessState.values()).forEach(obj -> list.add(new Dropdown(obj.getName(), obj.getValue())));
        return Result.success(list);
    }

}
