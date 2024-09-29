package ex.application.order.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import ex.application.order.dto.OrderCreateReq;
import ex.application.order.message.DeliveryMessage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSender {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, DeliveryMessage> kafkaTemplate;

    public void send(String topic, UUID orderId, OrderCreateReq request) {
        DeliveryMessage message = new DeliveryMessage(
            orderId,
            null,
            request.userId(),
            request.productId(),
            request.productQuantity(),
            request.payAmount(),
            null);

        kafkaTemplate.send(topic, orderId.toString(), message);

//        try {
//            String json = objectMapper.writeValueAsString(message);
//            kafkaTemplate.send(topic, orderId.toString(), json);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
    }
}
