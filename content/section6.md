## DI 를 이용한 Decorator, Proxy 패턴

### 직접 의존 하는 경우

의존 방향이 위에서 아래로 향함
```mermaid
classDiagram
    direction TB
    class HelloController
    class SimpleHelloService
    
    HelloController ..> SimpleHelloService
```

### HelloService Interface 에 의존 하는 경우
의존성이 역전이 됨. <br>
이렇게 되면 HelloService 의 구현체를 자유롭게 교체 가능해짐. -> Controller 입장에서는 바뀐지 모름.  
```mermaid
classDiagram
    direction LR
    class HelloController
    class HelloService
    <<interface>> HelloService
    
    HelloController ..> HelloService
```

```mermaid
classDiagram
    direction BT
    class HelloService
    <<interface>> HelloService
    
    class SimpleHelloService
    class ComplexHelloService
    SimpleHelloService ..|> HelloService
    ComplexHelloService ..|> HelloService
```

### 만약 SimpleHelloService 의 비즈니스 로직을 수정하지않고 추가적인 로직을 구현을 하고 싶다면? Decorator 패턴
```mermaid
classDiagram
    direction LR
    class HelloController
    class HelloDecorator
    class SimpleHelloService
    
    HelloController ..> HelloDecorator
    HelloDecorator..> SimpleHelloService
```

