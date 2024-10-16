package chat.wisechat.multi.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Data
@Configuration
@ConfigurationProperties(prefix = Constants.MYBATIS_PLUS)
public class MybatisPlusBaseConfig {

    @Resource
    private AutoFillHandler autoFillHandler;

    /**
     * Locations of MyBatis mapper files.
     *
     * @since 3.1.2 add default value
     */
    private String[] mapperLocations = new String[]{"classpath*:mapper/**/*.xml"};

    /**
     * 全局配置
     */
    private GlobalConfig globalConfig = GlobalConfigUtils.defaults();

    /**
     * 初始化全局默认配置
     */
    @PostConstruct
    private void postConstructor() {
        globalConfig.setMetaObjectHandler(autoFillHandler);
        //globalConfig.setBanner(false);
    }

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); // 乐观锁插件
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor()); // 如果配置多个插件, 切记分页最后添加
        return interceptor;
    }
}
