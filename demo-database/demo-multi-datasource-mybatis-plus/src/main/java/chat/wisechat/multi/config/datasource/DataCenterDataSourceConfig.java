package chat.wisechat.multi.config.datasource;

import chat.wisechat.multi.config.MybatisPlusBaseConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Data
@Configuration
@MapperScan(value = "chat.wisechat.multi.mapper.datacenter", sqlSessionFactoryRef = "datacenterSqlSessionFactory")
@ConfigurationProperties(prefix = "wisechat.mybatis-plus.datasource.data-center-datasource")
public class DataCenterDataSourceConfig {

    private HikariDataSource hikari;

    @Resource
    MybatisPlusBaseConfig mybatisPlusBaseConfig;

    @Bean(name = "datacenterDataSource")
    public DataSource datacenterDataSource() {
        return new HikariDataSource(hikari);
    }

    @Bean(name = "datacenterTransactionManager")
    public PlatformTransactionManager datacenterTransactionManager(@Qualifier("datacenterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datacenterSqlSessionFactory")
    public MybatisSqlSessionFactoryBean datacenterSqlSessionFactory(@Qualifier("datacenterDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("chat.wisechat.multi.entity.datacenter");
        bean.setGlobalConfig(mybatisPlusBaseConfig.getGlobalConfig());
        bean.setPlugins(new Interceptor[]{
                mybatisPlusBaseConfig.mybatisPlusInterceptor()
        });
        // TODO: 还需要设置一下xml文件扫描路径
        return bean;
    }
}
