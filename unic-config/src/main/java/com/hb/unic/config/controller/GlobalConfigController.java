package com.hb.unic.config.controller;

import com.hb.unic.base.annotation.InOutLog;
import com.hb.unic.base.common.Result;
import com.hb.unic.base.common.ErrorCode;
import com.hb.unic.base.controller.BaseController;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.validator.Assert;
import com.hb.unic.common.validator.Check;
import com.hb.unic.config.dao.dobj.GlobalConfigDO;
import com.hb.unic.config.service.IGlobalConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局配置表控制层
 *
 * @version v0.1, 2021-09-21 15:08:19, create by Mr.Huang.
 */
@Slf4j
@RestController
@RequestMapping("/globalConfig")
public class GlobalConfigController extends BaseController {

    /**
     * 全局配置表服务层
     */
    @Resource
    private IGlobalConfigService globalConfigService;

    /**
     * 分页查询全局配置表
     *
     * @param globalConfig
     *            查询条件对象
     * @param pageNum
     *            当前第几页
     * @param pageSize
     *            每页条数
     * @return 分页结果
     */
    @PostMapping("/queryPages")
    public Result<Page<GlobalConfigDO>> queryPages(@RequestBody GlobalConfigDO globalConfig,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        Assert.ifTrueThrows(Check.incorrectPageParameter(pageNum, pageSize), ErrorCode.PAGE_PARAM_ERROR);
        return Result.success(globalConfigService.selectPages(globalConfig, pageNum, pageSize));
    }

    /**
     * 新增全局配置表
     *
     * @param config
     *            新增对象信息
     * @return 影响的行数
     */
    @InOutLog("新增全局配置表")
    @PostMapping("/save")
    public Result save(@RequestBody GlobalConfigDO config) {
        if (StringUtils.isAnyBlank(config.getSystemName(), config.getGroupName(), config.getConfigKey(),
            config.getConfigValue())) {
            return Result.fail(ErrorCode.PARAM_ILLEGAL);
        }
        GlobalConfigDO query = new GlobalConfigDO();
        query.setSystemName(config.getSystemName());
        query.setGroupName(config.getGroupName());
        query.setConfigKey(config.getConfigKey());
        List<GlobalConfigDO> existList = globalConfigService.selectList(query);
        if (!CollectionUtils.isEmpty(existList)) {
            return Result.fail(ErrorCode.RECORD_REPEAT);
        }
        return Result.success(globalConfigService.insert(config));
    }

    /**
     * 通过主键修改全局配置表
     *
     * @param config
     *            要修改的信息
     * @return 影响的行数
     */
    @InOutLog("通过主键修改全局配置表")
    @PostMapping("/updateById")
    public Result updateById(@RequestBody GlobalConfigDO config) {
        Assert.notNull(config.getId(), ErrorCode.PARAM_ILLEGAL);
        GlobalConfigDO old = globalConfigService.selectById(config.getId());
        GlobalConfigDO query = new GlobalConfigDO();
        if (StringUtils.isBlank(config.getSystemName())) {
            query.setSystemName(old.getSystemName());
        } else {
            query.setSystemName(config.getSystemName());
        }
        if (StringUtils.isBlank(config.getGroupName())) {
            query.setGroupName(old.getGroupName());
        } else {
            query.setGroupName(config.getGroupName());
        }
        if (StringUtils.isBlank(config.getConfigKey())) {
            query.setConfigKey(old.getConfigKey());
        } else {
            query.setConfigKey(config.getConfigKey());
        }
        List<GlobalConfigDO> existList = globalConfigService.selectList(query);
        if (!CollectionUtils.isEmpty(existList)) {
            List<GlobalConfigDO> repeatList =
                existList.stream().filter(cfg -> cfg.getId().compareTo(old.getId()) != 0).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(repeatList)) {
                return Result.fail(ErrorCode.RECORD_REPEAT);
            }
        }
        return Result.success(globalConfigService.updateById(config));
    }

    /**
     * 通过主键删除全局配置表
     *
     * @param id
     *            主键
     * @return 影响的行数
     */
    @InOutLog("通过主键删除全局配置表")
    @GetMapping("/deleteById")
    public Result deleteById(@RequestParam("id") Long id) {
        Assert.notNull(id, ErrorCode.PARAM_ILLEGAL);
        GlobalConfigDO globalConfig = new GlobalConfigDO();
        globalConfig.setId(id);
        return Result.success(globalConfigService.deleteById(globalConfig));
    }

}
