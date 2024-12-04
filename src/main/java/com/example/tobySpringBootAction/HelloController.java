package com.example.tobySpringBootAction;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring Container 안에서 요청(request)을 처리
// 마치 Web Component 처럼 동작(Servlet Container > Servlet Component)
// RestController -> Web Page 를 리턴X, JSON 으로 응답
// Servlet -> Java 진영에서 웹 요청을 처리하고 응답하는 컴포넌트
@RestController
public class HelloController {

  // Http Get Method 처리
  // 쿼리 스트링을 요청하면 스프링에서는 메소드 파라미터로 자동 인식
  // http://localhost:8080/hello?name=spring
  @GetMapping("/hello")
  public String getHello(String name) {
    return "Hello " + name;
  }
}
