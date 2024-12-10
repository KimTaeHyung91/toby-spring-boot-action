package com.example.tobySpringBootAction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * 해당 element 는 기본적으로 resource/application.properties 에 있는 port 값을 적용함.
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

  @Test
  void helloApi() {
    // given
    // http localhost:8080/hello?name=Spring
    // 위 엔드포인트 호출을 코드로 작성
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    // when
    ResponseEntity<String> res = testRestTemplate.getForEntity(
        "http://localhost:9090/app/hello?name={name}",
        String.class,
        "Spring");

    // then
    // status code 200
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    // header(content-type) text/plain
    assertThat(res.getHeaders()
                  .getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
    // body Hello, Spring
    assertThat(res.getBody()).isEqualTo("*Hello, Spring*");

  }

  @Test
  void failHelloApi() {
    // given
    // http localhost:8080/hello?name=Spring
    // 위 엔드포인트 호출을 코드로 작성
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    // when
    ResponseEntity<String> res = testRestTemplate.getForEntity(
        "http://localhost:9090/hello?name={name}",
        String.class,
        "");

    // then
    assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
