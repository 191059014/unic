package com.hb.unic.config.service.impl;

import com.alibaba.fastjson.JSON;
import com.hb.unic.base.constant.BaseConsts;
import com.hb.unic.cache.Redis;
import com.hb.unic.common.standard.Page;
import com.hb.unic.common.standard.PageCondition;
import com.hb.unic.config.dao.dobj.GlobalConfigDO;
import com.hb.unic.config.dao.mapper.IGlobalConfigMapper;
import com.hb.unic.config.service.IGlobalConfigService;
import com.hb.unic.config.util.ConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 全局配置表服务层实现类
 *
 * @version v0.1, 2021-09-21 15:08:19, create by Mr.Huang.
 */
@Slf4j
@Service
public class GlobalConfigServiceImpl implements IGlobalConfigService {

    /**
     * 全局配置表操作数据库层
     */
    @Resource
    private IGlobalConfigMapper globalConfigMapper;

    /**
     * 通过主键查询单条数据
     *
     * @param id
     *            主键
     * @return 实例对象
     */
    @Override
    public GlobalConfigDO selectById(Long id) {
        return this.globalConfigMapper.selectById(id);
    }

    /**
     * 通过条件查询单条数据
     *
     * @param globalConfig
     *            查询条件
     * @return 对象列表
     */
    @Override
    public GlobalConfigDO selectOne(GlobalConfigDO globalConfig) {
        List<GlobalConfigDO> list = this.globalConfigMapper.selectList(globalConfig);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 通过实体作为筛选条件查询集合
     *
     * @param globalConfig
     *            查询条件
     * @return 对象列表
     */
    @Override
    public List<GlobalConfigDO> selectList(GlobalConfigDO globalConfig) {
        return this.globalConfigMapper.selectList(globalConfig);
    }

    /**
     * 分页查询数据
     *
     * @param globalConfig
     *            查询条件
     * @param pageNum
     *            当前页数
     * @param pageSize
     *            每页查询条数
     * @return 对象列表
     */
    @Override
    public Page<GlobalConfigDO> selectPages(GlobalConfigDO globalConfig, int pageNum, int pageSize) {
        Long count = this.globalConfigMapper.selectCount(globalConfig);
        List<GlobalConfigDO> dataList =
            this.globalConfigMapper.selectPages(globalConfig, PageCondition.create(pageNum, pageSize));
        return Page.create(count, dataList);
    }

    /**
     * 通过id集合查询
     *
     * @param idSet
     *            id集合
     * @param globalConfig
     *            查询条件
     * @return 结果集
     */
    @Override
    public List<GlobalConfigDO> selectByIdSet(Set<Long> idSet, GlobalConfigDO globalConfig) {
        return this.globalConfigMapper.selectByIdSet(idSet, globalConfig);
    }

    /**
     * 选择性新增
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int insert(GlobalConfigDO globalConfig) {
        return this.globalConfigMapper.insert(globalConfig);
    }

    /**
     * 通过主键修改
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int updateById(GlobalConfigDO globalConfig) {
        return this.globalConfigMapper.updateById(globalConfig);
    }

    /**
     * 通过主键删除数据
     *
     * @param globalConfig
     *            实例对象
     * @return 影响行数
     */
    @Override
    public int deleteById(GlobalConfigDO globalConfig) {
        return this.globalConfigMapper.deleteById(globalConfig);
    }

    /**
     * 从缓存里获取配置
     *
     * @param systemName
     *            系统名称
     * @param groupName
     *            分组名称
     * @return 值
     */
    @Override
    public List<GlobalConfigDO> getFromCache(String systemName, String groupName) {
        if (StringUtils.isAnyBlank(systemName, groupName)) {
            return null;
        }
        String cacheKey = ConfigUtils.getGlobalConfigCacheKey(systemName, groupName, null);
        String cache = Redis.strOps().opsForValue().get(cacheKey);
        log.info("get config from redis, key={}, value={}", cacheKey, cache);
        if (StringUtils.isNotBlank(cache)) {
            return JSON.parseArray(cache, GlobalConfigDO.class);
        }
        GlobalConfigDO query = new GlobalConfigDO();
        query.setSystemName(systemName);
        query.setGroupName(groupName);
        List<GlobalConfigDO> configFromDb = selectList(query);
        log.info("get config from db, key={}, value={}", cacheKey, configFromDb == null ? 0 : configFromDb.size());
        if (configFromDb != null) {
            Redis.strOps().opsForValue().set(cacheKey, JSON.toJSONString(configFromDb), BaseConsts.MINUTE_10_S,
                TimeUnit.SECONDS);
        }
        return configFromDb;
    }

    /**
     * 从缓存里获取配置
     * 
     * @param systemName
     *            系统名称
     * @param groupName
     *            分组名称
     * @param configKey
     *            键
     * @return 值
     */
    @Override
    public GlobalConfigDO getFromCache(String systemName, String groupName, String configKey) {
        if (StringUtils.isAnyBlank(systemName, groupName, configKey)) {
            return null;
        }
        String cacheKey = ConfigUtils.getGlobalConfigCacheKey(systemName, groupName, configKey);
        String cache = Redis.strOps().opsForValue().get(cacheKey);
        log.info("Get config from redis, key: {}, value: {}", cacheKey, cache);
        if (StringUtils.isNotBlank(cache)) {
            return JSON.parseObject(cache, GlobalConfigDO.class);
        }
        GlobalConfigDO query = new GlobalConfigDO();
        query.setSystemName(systemName);
        query.setGroupName(groupName);
        query.setConfigKey(configKey);
        GlobalConfigDO configFromDb = selectOne(query);
        log.info("Get config from db, key: {}, value: {}", cacheKey, configFromDb);
        if (configFromDb != null) {
            Redis.strOps().opsForValue().set(cacheKey, JSON.toJSONString(configFromDb), BaseConsts.MINUTE_10_S,
                TimeUnit.SECONDS);
        }
        return configFromDb;
    }

}
