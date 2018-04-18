package demo.creeper.dao.config;

import demo.dao.bo.DataSourceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

/**
 * @Author: Niko Zhao
 * @Date: Create in 03/27/18
 * @Email: nikoz@synnex.com
 */
@Configuration

public class JpaConfiguration {
     //@Bean
    public DataSource dataSource(DataSourceConfig dataSourceConfig){
        org.apache.tomcat.jdbc.pool.DataSource dataSource =new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(dataSourceConfig.getUrl());
        dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        return dataSource;
    }

   /* @Bean(name = "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment environment,
                                                                       @Qualifier("dataSource") DataSource dataSource) {
        return DataSourceConfigurationFactory.entityManagerFactory(environment, dataSource, DATASOURCE_NAME);
    }*/
}
