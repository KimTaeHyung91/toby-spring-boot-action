## Bean Object 의 역할과 구분

### Spring 에서 관리하는 Bean 종류와 구분

Bean: Spring Container 에서 관리하는 컴포넌트

<div>

#### [애플리케이션 빈]

```mermaid
block-beta
    columns 2
    HelleController
    DataSource
    SimpleHelloService
    JpaEntityManagerFactory
    JdbcTransactionManager
```

#### [컨테이너 인프라스트럭처 빈]

```mermaid
block-beta
    columns 2
    ApplicationContext/BeanFactory
    Environment
    BeanPostProcessor
    BeanFactoryPostProcessor
    DefaultAdvisorAutoProxyCreator
```

애플리케이션 빈: 개발자가 어떤 빈을 사용하겠다고 구성정보를 명시적으로 제공한것

컨테이너 인프라스트럭처 빈: Spring Container 자신, 또는 Container 기능을 구동시키기위한 빈

---

여기서 애플리케이션 빈을 2가지로 나뉠수 있음

#### [애플리케이션 로직 빈]

```mermaid
block-beta
    columns 1
    HelleController
    SimpleHelloService
```

#### [애플리케이션 인프라스트럭처 빈]

```mermaid
block-beta
    columns 1
    DataSource
    JpaEntityManagerFactory
    JdbcTransactionManager
```

애플리케이션 로직 빈: 애플리케이션의 기능, 비즈니스 로직, 도메인 로직을 담는 빈

애플리케이션 인프라스트럭처 빈: 대부분 기술과 관련, 직접 작성X, 이미 만들어져 있고 구성정보 등록 후 가져다 쓰기만 하면 됨.

### TomcatServletWebServerFactory, DispatcherServlet 은 어디에 속할 수 있는가?

애플리케이션을 구동 시키기 위한 기능이므로 애플리케이션 인프라스트럭처 빈으로 취급할 수 있음.

그러면 SpringBoot 개발자들이 분류하는 방식으로 분리하면?

#### [사용자 구성정보(ComponentScan)]

```mermaid
block-beta
    columns 1
    HelloController
    HelloDecorator
    SimpleHelloService
```

#### [자동 구성정보(AutoConfiguration)]

```mermaid
block-beta
    columns 1
    TomcatServletWebServerFactory
    DispatcherServlet
```

자동구성정보를 만들려면 아래처럼 애플리케이션 인프라스트럭처 빈들을 담는 Configuration 클래스를 각각 생성

```mermaid
---
title: "@Configuration"
---

classDiagram
    class TomcatServletWebServerFactory
```

#### [@Configuration]

```mermaid
---
title: "@Configuration"
---

classDiagram
    class DispatcherServlet
```

#### AuthConfiguration 이란

미리 만들어둔 Configuration 클래스(구성 정보)를 SpringBoot 가 어떤게 필요한지를 판단하고 자동으로 선택해서 사용이 목적

#### ImportSelector & DeferredImportSelector

하드 코딩으로 @Import 를 하는것이 아닌 동적으로 구성 정보 클래스를 로딩하게 만들어주는 Interface
DeferredImportSelector 인터페이스의 실행 시점은 @Configuration 클래스를 모두 등록 된 후 실행
동적으로 DB 정보를 불러온다던가 외부 환경 정보(외부 API) 를 불러오든 동적으로 호출 가능

#### com.example.config.MyAutoConfiguration.imports 으로 동적 구성정보 가능

해당 파일에 Import 를 해야되는 Configuration 파일을 작성해주면 SpringBoot 로딩 시 자동으로 구성정보를 로딩 가능

#### 애플리케이션 인프라스트럭처 빈 구조

```mermaid
classDiagram
    direction BT
    class MySpringBootApplication
    class EnableMyAutoConfiguration
    class MyAutoConfigImportSelector
    <<annotation>> MySpringBootApplication
    <<annotation>> EnableMyAutoConfiguration
    MySpringBootApplication ..> EnableMyAutoConfiguration: meta
    EnableMyAutoConfiguration ..> MyAutoConfigImportSelector: import
```

```mermaid
classDiagram
    direction RL
    class DispatcherServletConfig
    class TomcatWebServerConfig
    class MyAutoConfigImportSelector

    DispatcherServletConfig ..> MyAutoConfigImportSelector: load
    TomcatWebServerConfig ..> MyAutoConfigImportSelector: load
```

```mermaid
classDiagram
    direction BT
    class DispatcherServletConfig
    class TomcatWebServerConfig
    class MyAutoConfiguration
    <<annotation>> MyAutoConfiguration
    DispatcherServletConfig ..> MyAutoConfiguration
    TomcatWebServerConfig ..> MyAutoConfiguration
```

#### @Configuration(proxyBeanMethods=false) default 는 true 인데 false 면 뭐가 다른거지?

default 가 true 인 경우 아래처럼 일반 자바코드를 Configuration 안에서 Bean 으로 등록되어질떄 직접 Common, Bean1, Bean2 를 생성해서
등록하는것이 아니고
앞에 Proxy Object 를 앞에 두고 그게 Bean 으로 등록

![img.png](img.png)

즉 proxyBeanMethod 를 false 로 지정하면 Spring 이 빈을 등록할때 Proxy Object 를 만들지않고 자바코드 동작 방식 그대로 사용할 수 있게끔 처리가
된다

**[default 사용 경우]**

에러 X
![img_1.png](img_1.png)

**[false 로 적용한 경우]**

common 객체가 싱글톤으로 유지X
![img_2.png](img_2.png)

proxyBeanMethod = false 로 사용하는 경우가 늘어났음

왜냐하면 내가 빈 팩토리 메소드를 만들어서 등록 할때 의존 관계가 있는 객체를 가져오지않는 방식으로 코드를 작성했다면

매번 시간이 오래걸리는 Proxy 객체를 만들 필요가 없음
</div>



