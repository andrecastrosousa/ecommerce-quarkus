package mindswap.academy.order.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.model.OrderItem;

@ApplicationScoped
public class OrderItemConverter {

    @Inject
    ObjectMapper objectMapper;
    @Inject
    OrderConverter orderConverter;
    @Inject
    ItemConverter itemConverter;
///////
    public OrderItemDto forDto(OrderItem orderItem){
//        return objectMapper.convertValue(orderItem, OrderItemDto.class);
        return new OrderItemDto(orderItem.getId(),
                orderConverter.toDto(orderItem.getOrder()),
                itemConverter.toDto(orderItem.getItem()),
                orderItem.getQuantity());
    }

    public OrderItem fromOrderItemCreateDto(OrderItemCreateDto orderItemCreateDto){
        return OrderItem.builder()
                .withOrder(orderItemCreateDto.getOrder())
                .withItem(orderItemCreateDto.getItem())
                .withQuantity(orderItemCreateDto.getQuantity())
                .build();
    }

}

