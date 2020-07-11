package com.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Value("${demo2.database.driver}")
    private String driver2;

    @Value("${demo2.database.url}")
    private String url2;

    @Value("${demo2.database.username}")
    private String username2;

    @Value("${demo2.database.password}")
    private String password2;

    @Bean("dataSource2")
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver2);
        dataSource.setUrl(url2);
        dataSource.setUsername(username2);
        dataSource.setPassword(password2);
        return dataSource;
    }

    @Bean("namedParameterJdbcTemplate2")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate2(@Qualifier("dataSource2") DataSource dataSource2) {
        return new NamedParameterJdbcTemplate(dataSource2);
    }
}
