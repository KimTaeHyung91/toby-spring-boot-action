## FrontController 에서 객체를 생성하는것돠 Spring Container에게 위암하여 처리하는것이 뭐가 다를까?

---

Spring Container 가 할수 있는 일들을 하는데 기본 골격을 만들었다는것에 의미가 큼.

### Spring Container 가 실행될 때 기본적으로 객체는 딱 한번만 만듦

새로운 객체를 계속 생성하는것이 아닌 이미 만들어진 객체를 재사용 -> 싱글톤 유지

### Controller, Service

Controller: 웹 요청을 처리, 처리 하기위한 별도의 객체를 호출
> 컨트롤러의 주요 역할의 유저의 요청을 검증하는것! 

Service: Controller 요청을 처리하고 결과를 내는 객체
