package com.example.tobySpringBootAction;

import javax.annotation.PostConstruct;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HelloBootApplication {

  @Bean
  ApplicationRunner runner(ConditionEvaluationReport report) {
    return args -> {
      System.out.println(report.getConditionAndOutcomesBySource().entrySet().stream()
                               .filter(co -> co.getValue().isFullMatch())
                               .filter(co -> !co.getKey().contains("Jmx"))
                               .map(co -> {
                                 System.out.println("key: " + co.getKey());
                                 co.getValue().forEach(c -> {
                                   System.out.println("\t Outcome: " + c.getOutcome());
                                 });
                                 System.out.println();
                                 return co;
                               }).count());
    };
  }

  private final JdbcTemplate jdbcTemplate;

  public HelloBootApplication(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  /**
   * Spring LifeCycle 에 따라서 모든 빈이 초기화 된 후 실행되는 코드를 삽입
   */
  @PostConstruct
  void init() {
    jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloBootApplication.class, args);
  }
}
