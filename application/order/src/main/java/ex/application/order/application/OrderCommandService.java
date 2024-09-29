package ex.application.order.application;

import ex.application.order.domain.Order;
import ex.application.order.dto.OrderCreateReq;
import ex.application.order.implementation.OrderReader;
import ex.application.order.implementation.OrderSaver;
import ex.application.order.implementation.OrderSender;
import ex.application.order.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandService {

    private final OrderReader orderReader;
    private final OrderSaver orderSaver;
    private final OrderSender orderSender;

    public Order createOrder(OrderCreateReq orderCreateReq) {
        Order order = orderSaver.create(orderCreateReq.userId());
        orderSender.send("market.product", order.getOrderId(), orderCreateReq);
        return order;
    }

    public void rollbackOrder(DeliveryMessage message) {
        Order order = orderReader.read(message.orderId());
        order.cancel(message.errorType());
    }
}
