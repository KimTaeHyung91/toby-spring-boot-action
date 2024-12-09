package com.example.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
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
  @Conditional(BooleanCondition.class)
  @interface BooleanConditional {

    // 엘리먼트가 단독으로 쓰이면 호출하는곳에서 생략 가능
    boolean value();
  }

  @Configuration
  @BooleanConditional(true)
  static class Config1 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  @Configuration
  @BooleanConditional(false)
  static class Config2 {

    @Bean
    MyBean myBean() {
      return new MyBean();
    }
  }

  static class MyBean {}

  static class BooleanCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      // BooleanConditional 에노테이션이 붙어있는 클래스를 탐색
      // 그 클래스에서 선언되어있는 해당 에노테이션의 엘리먼트 속성을 가져옴
      Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
      return (Boolean) annotationAttributes.get("value");
    }
  }
}
