package com.bussinessIt.api.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class GeneralConfiguration {

    @Bean
    public DataSource datasource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.username("airoman");
        dataSourceBuilder.password("password");
        dataSourceBuilder.url("jdbc:h2:mem:crudbussinessit");
        return dataSourceBuilder.build();
    }
}
