package mindswap.academy.order.service;

import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdatedDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAll();

    OrderItemDto getById(Long id);

    OrderItemDto create(OrderItemCreateDto orderItemCreateDto);

    void delete(Long orderItemId);

    OrderItemDto update(Long orderItemId, OrderItemUpdatedDto orderItemUpdatedDto);
}
