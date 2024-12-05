package com.example.tobySpringBootAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HelloBootApplication {

  public static void main(String[] args) {
    /**
     * Spring Container 의 객체를 사용하기 위해서는 아래와 같은 조건이 있음
     * POJO: 비즈니스 수행을 하는 일반 자바 객체 e.g) HelloController
     * Configure Metadata: Spring Container로 객체를 등록하기 위한 객체의 메타데이터 등록 e.g) registerBean
     */
    // ApplicationContext(=Spring Container)
    GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
      // TemplateMethod refresh 에서 상속받은 서브 클래스에서 부가적인 로직을 추가 하고 싶을때 아래 hook 메소드에 다가 위치시킨다.
      @Override
      protected void onRefresh() {
        super.onRefresh();

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
         *
         * DispatcherServlet: URL 맵핑정보를 해당 Servlet 이 웹 요청을 처리 할 컴포넌트를 자동으로 찾아 위임한다.
         */
        WebServer webServer = factory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet", new DispatcherServlet(this))
                        .addMapping("/*");
        });
        webServer.start();
      }
    };
    applicationContext.registerBean(HelloController.class);
    // Spring Container 에서 Bean 을 등록 할때 HelloController 에 선언되어있는 HelloService 인터페이스 구현체를 조회하여 알아서 DI 를 넣어야
    applicationContext.registerBean(SimpleHelloService.class);

    // Spring Container 초기화 -> 등록되어있는 메타데이터를 초기화
    // TemplateMethod Pattern
    applicationContext.refresh();
  }
}
