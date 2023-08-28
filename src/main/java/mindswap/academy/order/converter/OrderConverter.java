package mindswap.academy.order.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderDto;
import mindswap.academy.order.dto.OrderUpdateDto;
import mindswap.academy.order.model.Order;

@ApplicationScoped
public class OrderConverter {

    @Inject
    ObjectMapper objectMapper;

    public OrderDto toDto(Order order){
        return new OrderDto(order.getId(),
                order.getTotal(),
                order.getOrderDatetime(),
                order.getOrderItems(),
                order.getShipping(),
                order.getPaymentMethod());
    }

    public OrderDto toDto(OrderUpdateDto orderUpdateDto){
        return objectMapper.convertValue(orderUpdateDto, mindswap.academy.order.dto.OrderDto.class);
    }

    public Order fromOrderCreateDto(OrderCreateDto orderCreateDto){
        return Order.builder()
                .withTotal(0.0)
                .withDate(orderCreateDto.getOrderDatetime())
                .withItems(null)
                .withUser(null)
                .withShipping(null)
                .withPaymentMethod(null)
                .build();
    }
}
