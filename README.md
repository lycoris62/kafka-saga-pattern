# kafka-saga-pattern

## 실행 방법

- 도커 실행
- 주문, 상품 스프링 실행
    - 자동으로 도커 컴포즈가 카프카 띄움
    - 결제 쪽은 귀찮아서 안 했는데 주문-상품 만으로 테스트 가능
    - http://localhost:8080 접속 시 카프카 UI 접속
- [order-api.http](http/order/order-api.http) 에서 호출 테스트 가능
    - productId != 1 || productQuantity > 1 일 때 에러 발생 - 보상 트랜잭션 실행

## 대강의 흐름

1. 주문 쪽으로 주문 요청이 들어옴
2. 주문 쪽에서 상품 쪽으로 상품 수량 감소 요청
3. 원래라면 결제 쪽으로 넘겨야 하는데 귀찮아서 구현 안 함
4. 상품 ID가 1이 아니거나, 상품 수량이 1 초과면 에러 발생하도록 구현함 
