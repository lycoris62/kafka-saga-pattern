package ex.application.product.message;

import java.util.UUID;

public record DeliveryMessage(
    UUID orderId,
    UUID paymentId,
    String userId,
    Integer productId,
    Integer productQuantity,
    Integer payAmount,
    String errorType
) {

}
