package demo.dao;

import demo.dao.bo.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author: Niko Zhao
 * @Date: Create in 17:21 12/18/17
 * @Email: nikoz@synnex.com
 */
//@Configuration
public class jpaConfiguration {
   // @Bean
    public DataSource dataSource(DataSourceConfig dataSourceConfig){
        org.apache.tomcat.jdbc.pool.DataSource dataSource =new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(dataSourceConfig.getUrl());
        dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        return dataSource;
    }

}
