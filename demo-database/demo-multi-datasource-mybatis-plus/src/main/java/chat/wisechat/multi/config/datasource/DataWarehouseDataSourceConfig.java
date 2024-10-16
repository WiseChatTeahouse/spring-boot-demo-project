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
@MapperScan(value = "chat.wisechat.multi.mapper.datawarehouse", sqlSessionFactoryRef = "datawarehouseSqlSessionFactory")
@ConfigurationProperties(prefix = "wisechat.mybatis-plus.datasource.data-warehouse-datasource")
public class DataWarehouseDataSourceConfig {

    private HikariDataSource hikari;

    @Resource
    MybatisPlusBaseConfig mybatisPlusBaseConfig;

    @Bean(name = "datawarehouseDataSource")
    public DataSource datawarehouseDataSource() {
        return new HikariDataSource(hikari);
    }

    @Bean(name = "datawarehouseTransactionManager")
    public PlatformTransactionManager datawarehouseTransactionManager(@Qualifier("datawarehouseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "datawarehouseSqlSessionFactory")
    public MybatisSqlSessionFactoryBean datawarehouseSqlSessionFactory(@Qualifier("datawarehouseDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("chat.wisechat.multi.entity.datawarehouse");
        bean.setGlobalConfig(mybatisPlusBaseConfig.getGlobalConfig());
        bean.setPlugins(new Interceptor[]{
                mybatisPlusBaseConfig.mybatisPlusInterceptor()
        });
        // TODO: 还需要设置一下xml文件扫描路径
        return bean;
    }
}
