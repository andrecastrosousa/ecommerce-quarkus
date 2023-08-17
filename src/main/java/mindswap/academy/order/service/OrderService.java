package mindswap.academy.order.service;

import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderDto;
import mindswap.academy.order.dto.OrderUpdatedDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();

    OrderDto getById(Long orderId);

    OrderDto create(OrderCreateDto orderCreateDto);

    void delete(Long orderId);

    OrderDto update(Long orderId, OrderUpdatedDto orderUpdatedDto);
}
