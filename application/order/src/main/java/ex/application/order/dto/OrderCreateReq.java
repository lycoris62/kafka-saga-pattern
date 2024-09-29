package ex.application.order.dto;

public record OrderCreateReq(
    String userId,
    Integer productId,
    Integer productQuantity,
    Integer payAmount
) {

}
