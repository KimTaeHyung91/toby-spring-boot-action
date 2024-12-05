package com.example.tobySpringBootAction;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// Spring Container 가 빈 구성 정보를 가지고 있는 클래스 라고 인식
@Configuration
// 해당 어노테이션이 붙은 클래스가 포함 패키지를 시작으로 하위 패키지까지 @Component 가 붙은 클래스를 검색하여 빈으로 등록한다.
@ComponentScan
public class HelloBootApplication {

  @Bean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }

  @Bean
  public DispatcherServlet dispatcherServlet() {
    /**
     * 명시적으로 applicationContext 를 주입하지않아도 상위 인터페이스에서 setter 가 선언되어있기때문에 스프링이 자동으로 주입해줌.
     * @see {ApplicationContextAware}
     */
    return new DispatcherServlet();
  }

  public static void main(String[] args) {

    // Configuration 클래스를 인식하기 위해서는 아래 Context 클래스를 사용
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
      // TemplateMethod refresh 에서 상속받은 서브 클래스에서 부가적인 로직을 추가 하고 싶을때 아래 hook 메소드에 다가 위치시킨다.
      @Override
      protected void onRefresh() {
        super.onRefresh();

        // Servlet Container
        ServletWebServerFactory factory = this.getBean(ServletWebServerFactory.class);
        DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

        WebServer webServer = factory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet", dispatcherServlet)
                        .addMapping("/*");
        });
        webServer.start();
      }
    };
    // 해당 Configuration Class 를 등록하여 빈 정보, DI 주입 할 수있게 미리 Context 에 알림
    applicationContext.register(HelloBootApplication.class);
    applicationContext.refresh();
  }
}
