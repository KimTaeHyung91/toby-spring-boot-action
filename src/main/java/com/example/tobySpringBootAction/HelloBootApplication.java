package com.example.tobySpringBootAction;

import com.example.config.MySpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MySpringBootApplication
public class HelloBootApplication {

  /**
   * ApplicationRunner 인터페이스는 스프링 부트가 초기화 작업을 모두 마치고 이후에 실행이 될 수 있게 해줌.
   * <p>
   * 또한 application.properties 보다 우선적으로 로딩되는것은 os, cloud 환경변수
   * <p>
   * os, cloud 환경 변수 보다 우선적인것은 VM Option "-D{Key}={Value}" 가 더 우선적으로 로딩
   * <p>
   * 개발하는 시점에서는 application.properties 에 작성을 해두고 이를 오버라이드 해서 상황에 맞게 쓰는것이 일반적인 패턴
   */
  @Bean
  ApplicationRunner applicationRunner(Environment env) {
    return args -> {
      String property = env.getProperty("my.name");
      System.out.println("property = " + property);
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(HelloBootApplication.class, args);
  }
}
