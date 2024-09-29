package ex.application.order.presentation;

import ex.application.order.application.OrderCommandService;
import ex.application.order.domain.Order;
import ex.application.order.dto.OrderCreateReq;
import ex.application.order.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderEndpoint {

    private final OrderCommandService orderCommandService;

    @PostMapping("/order")
    public ResponseEntity<Order> order(@RequestBody OrderCreateReq orderRequestDto) {

        Order order = orderCommandService.createOrder(orderRequestDto);

        return ResponseEntity.ok(order);
    }

    @KafkaListener(topics = "market.err.order", groupId = "order")
    public void errOrder(DeliveryMessage deliveryMessage) {
        log.info("ERROR RECEIVE !!!");
        orderCommandService.rollbackOrder(deliveryMessage);
    }
}
