package com.example.tobySpringBootAction;

import java.util.Objects;

/**
 * Spring Container 안에서 요청(request)을 처리
 * 마치 Web Component 처럼 동작(Servlet Container > Servlet Component)
 * RestController -> Web Page 를 리턴X, JSON 으로 응답
 * Servlet -> Java 진영에서 웹 요청을 처리하고 응답하는 컴포넌트
 */
public class HelloController {

  private final HelloService helloService;

  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  /**
   *
   * Http Get Method 처리
   * 쿼리 스트링을 요청하면 스프링에서는 메소드 파라미터로 자동 인식
   * http://localhost:8080/hello?name=spring
   */
  public String hello(String name) {
    return helloService.sayHello(Objects.requireNonNull(name));
  }
}
