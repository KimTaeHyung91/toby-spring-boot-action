package com.example.tobySpringBootAction;

import java.util.Objects;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Container 안에서 요청(request)을 처리
 * 마치 Web Component 처럼 동작(Servlet Container > Servlet Component)
 * RestController -> Web Page 를 리턴X, JSON 으로 응답
 * Servlet -> Java 진영에서 웹 요청을 처리하고 응답하는 컴포넌트
 *
 * DispatcherServlet 이 먼저 클래스 -> 메소드 순으로 맵핑 정보를 검색
 * Class -> RequestMapping
 * Method -> GetMapping, PostMapping 등
 * 위 어노테이션이 붙은 Bean 을 조회
 */
@RequestMapping
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
   *
   * 기본적으로 DispatcherServlet 의 응답은 view(html) 파일을 찾아서 응답 -> html 파일이 없으니 NotFound 에러 발생
   * 단순 String 으로 응답 하고 싶다면 ResponseBody 어노테이션을 붙여야됨.
   * 만약 클래스에 RestController 을 붙이면 하위 메소드는 모두 ResponseBody 가 붙어있다고 가정한다.
   */
  @GetMapping("/hello")
  @ResponseBody
  public String hello(String name) {
    return helloService.sayHello(Objects.requireNonNull(name));
  }
}
