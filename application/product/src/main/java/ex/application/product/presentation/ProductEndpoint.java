package ex.application.product.presentation;

import ex.application.product.message.DeliveryMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEndpoint {

    @KafkaListener(topics = "market.product", groupId = "product")
    public void receiveMessage(DeliveryMessage message) {
        log.info("PRODUCT RECEIVE:{}", message);
    }
}
