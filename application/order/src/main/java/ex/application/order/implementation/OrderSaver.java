package ex.application.order.implementation;

import ex.application.order.domain.Order;
import ex.application.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderSaver {

    private final OrderRepository orderRepository;

    @Transactional
    public Order create(String userId) {
        Order order = Order.create(userId);
        return orderRepository.save(order);
    }
}
