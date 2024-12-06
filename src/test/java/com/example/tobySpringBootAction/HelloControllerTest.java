package com.example.tobySpringBootAction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

  /**
   * HelloController 경우 HelloService 가 의존관계로 주입되어있는데
   * 이를 테스트 하기 위해서는 HelloService 객체도 만들어줘야된다.
   * 하지만 HelloController 자체만을 테스트를 위해서는 HelloService 객체까지 신경쓰는거 부담이 큼(HelloService 내부에서 에러라도 뱉는 경우)
   * 그래서 HelloController 를 의존관계로부터 고립을 시켜야된다.
   * -> 직접 HelloService 의 Stub 객체를 넣어줘서 해결
   */
  @Test
  void helloController() {
    // given
    HelloController helloController = new HelloController(name -> name);

    // when
    String test = helloController.hello("Test");

    // then
    Assertions.assertThat(test).isEqualTo("Test");
  }

  /**
   * 단위 테스트: 환경(Spring, DB..)에 영향을 받지않고 고립된 상태에서 테스트를 진행 -> 보통 하나의 클래스 단위로 진행
   */
  @Test
  void failHelloController() {

    // given
    HelloController helloController = new HelloController(name -> name);

    // when
    // then
    Assertions.assertThatThrownBy(() -> {
      helloController.hello(null);
    }).isInstanceOf(IllegalArgumentException.class);

    Assertions.assertThatThrownBy(() -> {
      helloController.hello("");
    }).isInstanceOf(IllegalArgumentException.class);
  }
}
