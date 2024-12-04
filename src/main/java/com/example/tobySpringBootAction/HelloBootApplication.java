package com.example.tobySpringBootAction;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HelloBootApplication {

  public static void main(String[] args) {
    // Servlet Container
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
    factory.setPort(3000);

    /**
     * 추상화 시켜놓음
     * 여러 컨테이너를 자유롭게 사용하기 위해 추상화 -> tomcat 이외의 jetty, netty
     * 인터페이스가 행위에 초점을 맞춘것도 있지만, 아래 처럼 특정 구현 사항을 의존하지 않게 명사로도 선언될 수 있음을 인지하자
     *
     * 웹상에서 스프링 MVC 를 사용을 하는데 이를 Java 의 메소드 또는 파라미터가 요청/응답 사이클에서 어떻게 맵핑해서 사용하는지 주목해서 사용할 필요가 있음
     * 왜? 웹 API -> Http 를 통해서 API 를 제공하니깐
     */
    WebServer webServer = factory.getWebServer(servletContext -> {
      servletContext.addServlet("frontController", new HttpServlet() {
                      // 요청을 처리하는 서비스 객체
                      @Override
                      protected void service(HttpServletRequest req, HttpServletResponse resp)
                          throws ServletException, IOException {
                        // 요청 URL 을 읽을 수 있는 메소드
                        String requestURI = req.getRequestURI();
                        // 쿼리스트링으로 넘어오는 값 처리
                        String name = req.getParameter("name");

                        if (requestURI.equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                          // 웹 응답의 3가지 요소 -> 상태, Content-Type, Body(응답)
                          resp.setStatus(HttpStatus.OK.value());
                          resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                          resp.getWriter().println("Hello, " + name);
                        } else if (requestURI.equals("/user")) {
                          //
                        } else {
                          resp.setStatus(HttpStatus.NOT_FOUND.value());
                        }
                      }
                    })
                    // url 맵핑
                    // '/*' -> frontController: 공통된 사항(보안, 인증, 다국어 처리 응답 형식, 로깅..등)를 요청 핸들러 앞단에서 처리하는 Controller
                    .addMapping("/*");
    });
    webServer.start();
  }
}
