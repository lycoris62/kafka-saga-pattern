package ex.application.order.presentation;

import ex.application.order.application.OrderCreateService;
import ex.application.order.domain.Order;
import ex.application.order.dto.OrderCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderEndpoint {

    private final OrderCreateService orderCreateService;

    @PostMapping("/order")
    public ResponseEntity<Order> order(@RequestBody OrderCreateReq orderRequestDto) {

        Order order = orderCreateService.createOrder(orderRequestDto);

        return ResponseEntity.ok(order);
    }
}
