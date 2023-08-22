package mindswap.academy.order.service;

import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.order.dto.OrderAddItemDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdateDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAll(Long orderId);
    OrderItemDto getById(Long orderId, Long itemId);
    OrderItemDto create(Long orderId, OrderAddItemDto orderAddItemDto);
    void delete(Long orderId, Long itemId);
    OrderItemDto update(Long orderId, OrderItemUpdateDto orderItemUpdateDto);
}
