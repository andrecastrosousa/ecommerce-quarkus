package mindswap.academy.order.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.model.OrderItem;

@ApplicationScoped
public class OrderItemConverter {

    @Inject
    ObjectMapper objectMapper;

    public OrderItemDto forDto(OrderItem orderItem){
        return objectMapper.convertValue(orderItem, OrderItemDto.class);
    }

    public OrderItem fromOrderItemCreateDto(OrderItemCreateDto orderItemCreateDto){
        return OrderItem.builder()
                .withOrder(orderItemCreateDto.getOrder())
                .withItem(orderItemCreateDto.getItem())
                .withQuantity(orderItemCreateDto.getQuantity())
                .build();
    }

}
