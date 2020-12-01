package org.colin.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * @Description: easy9数据库
 * @ClassName: Db1Config
 * @Author: wujiangbo
 * @Date: 2020/7/16 0016 15:02
 * @Version: 1.1.0
 */
@Configuration
//下面要进行扫包，目的是标清楚为谁添加的数据源，这样对应的包里函数执行数据库操作的时候，就知道要执行的数据库账号/密码/表，以及事务处理之类的。
@MapperScan(basePackages = Db1Config.MAPPER_PACKAGE, sqlSessionFactoryRef = "db1SqlSessionFactory")
public class Db1Config {

    static final String MAPPER_PACKAGE = "org.colin.mapper";
    static final String MAPPER_XML_LOCATION = "classpath:mapper/*.xml";

    @Value("${system.isShowMybatisLog}")
    private String isShowMybatisLog;

    @Value("${spring.datasource.druid.db1.url}")
    private String url;

    @Value("${spring.datasource.druid.db1.username}")
    private String user;

    @Value("${spring.datasource.druid.db1.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "db1DataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "db1TransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1DataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(Db1Config.MAPPER_XML_LOCATION));
        org.apache.ibatis.session.Configuration ibatisConfiguration = new org.apache.ibatis.session.Configuration();
        ibatisConfiguration.setMapUnderscoreToCamelCase(true);//开启驼峰功能
        if("true".equals(isShowMybatisLog)){
            ibatisConfiguration.setLogImpl(StdOutImpl.class);
        }
        sessionFactory.setConfiguration(ibatisConfiguration);
        return sessionFactory.getObject();
    }
}
