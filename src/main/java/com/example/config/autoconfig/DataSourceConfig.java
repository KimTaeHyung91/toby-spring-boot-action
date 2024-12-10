package com.example.config.autoconfig;

import com.example.config.ConditionalMyOnClass;
import com.example.config.EnableMyConfigurationProperties;
import com.example.config.MyAutoConfiguration;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Driver;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
// 아래 애노테이션은 @Transactional 이라는 애노테이션을 AOP 로 사용 가능하게 함.
@EnableTransactionManagement
public class DataSourceConfig {


  @Bean
  @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
  @ConditionalOnMissingBean
  DataSource hikariDataSource(MyDataSourceProperties properties) {
    HikariDataSource dataSource = new HikariDataSource();

    dataSource.setDriverClassName(properties.getDriverClassName());
    dataSource.setJdbcUrl(properties.getUrl());
    dataSource.setUsername(properties.getUserName());
    dataSource.setPassword(properties.getPassword());

    return dataSource;
  }

  @Bean
  @ConditionalOnMissingBean
  DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
    SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

    dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
    dataSource.setUrl(properties.getUrl());
    dataSource.setUsername(properties.getUserName());
    dataSource.setPassword(properties.getPassword());

    return dataSource;
  }

  @Bean
  // DataSource 의 빈이 딱 한개만 등록이 되었을때 해당 JdbcTemplate 을 사용
  @ConditionalOnSingleCandidate(DataSource.class)
  @ConditionalOnMissingBean
  JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  @ConditionalOnSingleCandidate(DataSource.class)
  @ConditionalOnMissingBean
  JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
    return new JdbcTransactionManager(dataSource);
  }
}
