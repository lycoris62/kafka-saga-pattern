package ex.application.order.application;

import ex.application.order.domain.Order;
import ex.application.order.implementation.OrderReader;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final OrderReader orderReader;

    @Transactional(readOnly = true)
    public Order getOrderById(UUID orderId) {
        return orderReader.read(orderId);
    }
}
