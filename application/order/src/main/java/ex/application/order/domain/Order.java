package ex.application.order.domain;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private UUID orderId;
    private String userId;
    private String orderStatus;
    private String errorType;

    public static Order create(String userId) {
        Order order = new Order();

        order.orderId = UUID.randomUUID();
        order.userId = userId;
        order.orderStatus = "ORDERED";

        return order;
    }

    public void cancel(String errorType) {
        this.orderStatus = "CANCELLED";
        this.errorType = errorType;
    }
}