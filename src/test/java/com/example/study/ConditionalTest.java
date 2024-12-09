package com.example.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ConditionalTest {

  @Test
  void conditional() {
    // true
    // 테스트 전용 애플리케이션 컨텍스트를 확인하는 용도
    ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner();
    applicationContextRunner.withUserConfiguration(Config1.class)
                            .run(context -> {
                              assertThat(context).hasSingleBean(MyBean.class);
                              assertThat(context).hasSingleBean(Config1.class);
                            });

    // false
    new ApplicationContextRunner().withUserConfiguration(Config2.class)
                                  .run(context -> {
                                    assertThat(context).doesNotHaveBean(MyBean.class);
                                    assertThat(context).doesNotHaveBean(Config2.class);
                                  });
  }

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(TrueCondition.class)
  @interface TrueConditional {}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @Conditional(FalseCondition.class)
  @interface FalseConditional {}

  @Configuration
  @TrueConditional
  static class Config1 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  @Configuration
  @FalseConditional
  static class Config2 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  static class MyBean {}

  static class TrueCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      return true;
    }
  }

  static class FalseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      return false;
    }
  }
}
