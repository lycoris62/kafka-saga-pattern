package ex.application.product.application;

import ex.application.product.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductCommandService {

    private final KafkaTemplate<String, DeliveryMessage> kafkaTemplate;

    public void reduceProductAmount(DeliveryMessage message) {

        Integer productId = message.productId();
        Integer productQuantity = message.productQuantity();

        if (productId != 1 || productQuantity > 1) {
            this.rollbackProduct(message);
            return;
        }

        kafkaTemplate.send("market.payment", message);
    }

    public void rollbackProduct(DeliveryMessage message) {
        log.info("PRODUCT ROLLBACK!!!");

        if (!StringUtils.hasText(message.errorType())) {
            message = new DeliveryMessage(
                message.orderId(),
                message.paymentId(),
                message.userId(),
                message.productId(),
                message.productQuantity(),
                message.payAmount(),
                "PRODUCT ERROR"
            );
        }

        kafkaTemplate.send("market.err.order", message.orderId().toString(), message);
    }
}
