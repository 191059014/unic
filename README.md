# unic基础平台
## unic-util 工具类模块
包含了一系列实用的工具类，例如CloneUtils，BigDecimalUtils，CompressUtils，DateUtils，
EncryptUtils，EnumUtils，GroovyUtils，OkHttpUtils，RandomUtils，ReflectUtils，
StringUtils，YamlUtils，JsonFileParseHelper，PageHelper，SimpleIdHelper，SimpleVerifyHelper等。
## unic-base 基础模块
- spring的工具类SpringUtils，可以获取所有bean实例
- 标准全局异常StandardRuntimeException
- 公共响应模型ResponseData，以及生成ResponseData的工具类ResponseUtils
- 基于spring注解@Async的异步调用接口IServiceExecutorService
- 基于javaconfig，扫描路径为“classpath*:config/service-*-config.yml”全局配置类GlobalProperties
- 基于xml，扫描路径为“classpath*:config/service-*-config.yml”下的所有配置信息
## unic-logger 日志模块
- 统一封装日志工厂com.hb.unic.logger.LoggerFactory
- 统一封装日志类com.hb.unic.logger.Logger
- 针对异常堆栈信息日志工具类com.hb.unic.logger.util.LogUtils
- traceId工具类com.hb.unic.logger.util.TraceIdUtils
- traceId过滤器com.hb.unic.logger.filter.TraceIdFilter
## unic-cache 缓存模块
- 统一缓存操作接口com.hb.unic.cache.ICacheService
- 基于redis的操作工具类com.hb.unic.cache.service.impl.RedisCacheServiceImpl

