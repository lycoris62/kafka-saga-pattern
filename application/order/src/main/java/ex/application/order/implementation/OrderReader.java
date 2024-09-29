package ex.application.order.implementation;

import ex.application.order.domain.Order;
import ex.application.order.infrastructure.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderReader {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Order read(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }
}
