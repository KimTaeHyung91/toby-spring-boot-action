package com.example.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 학습 테스트
 * <p>
 * 내가 만든 로직을 검증하는것이 아닌 이미 만들어진 코드, 라이브러리를 검증하고 싶을때 사용
 */
public class ConfigurationTest {

  @Test
  void configuration() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
    ac.register(MyConfig.class);
    ac.refresh();

    Bean1 bean1 = ac.getBean(Bean1.class);
    Bean2 bean2 = ac.getBean(Bean2.class);

    Assertions.assertThat(bean1.common).isSameAs(bean2.common);
  }

  @Test
  void proxyCommonMethod() {
    MyConfigProxy myConfigProxy = new MyConfigProxy();

    Bean1 bean1 = myConfigProxy.bean1();
    Bean2 bean2 = myConfigProxy.bean2();

    Assertions.assertThat(bean1.common).isSameAs(bean2.common);
  }

  static class MyConfigProxy extends MyConfig {

    private Common common;

    @Override
    Common common() {
      if (this.common == null) {
        this.common = super.common();
      }

      return this.common;
    }
  }

  @Configuration
  static class MyConfig {

    @Bean
    Common common() {
      return new Common();
    }

    /**
     * bean1, bean2 의 주입되는 common 은 두번 생성됨.
     */
    @Bean
    Bean1 bean1() {
      return new Bean1(common());
    }

    @Bean
    Bean2 bean2() {
      return new Bean2(common());
    }
  }

  static class Bean1 {

    private final Common common;

    public Bean1(Common common) {
      this.common = common;
    }
  }

  static class Bean2 {

    private final Common common;

    public Bean2(Common common) {
      this.common = common;
    }
  }

  static class Common {

  }

  // Bean1 <-- Common (Common 빈의 의존)

  // Bean2 <-- Common (Common 빈의 의존)
}
