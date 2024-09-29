package ex.application.order.infrastructure;

import ex.application.order.domain.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private static final Map<UUID, Order> orderStore = new HashMap<>();

    public Order save(Order order) {
        orderStore.put(order.getOrderId(), order);
        return order;
    }

    public Optional<Order> findById(UUID orderId) {
        return Optional.ofNullable(orderStore.get(orderId));
    }
}
