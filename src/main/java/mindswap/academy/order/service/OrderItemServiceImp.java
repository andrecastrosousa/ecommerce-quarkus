package mindswap.academy.order.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.item.dto.ItemCreateDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.order.converter.OrderItemConverter;
import mindswap.academy.order.dto.OrderAddItemDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdateDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.model.OrderItem;
import mindswap.academy.order.repository.OrderItemRepository;
import mindswap.academy.order.repository.OrderRepository;

import java.util.List;

@ApplicationScoped
public class OrderItemServiceImp implements OrderItemService {

    @Inject
    OrderItemRepository orderItemRepository;
    @Inject
    OrderRepository orderRepository;
    @Inject
    ItemRepository itemRepository;
    @Inject
    ItemConverter itemConverter;
    @Inject
    OrderItemConverter orderItemConverter;


    @Override
    public List<OrderItemDto> getAll(Long orderId) {
        return orderRepository.findById(orderId).getOrderItems()
                .stream().map(orderItem -> orderItemConverter.forDto(orderItem)).toList();
    }

    @Override
    public OrderItemDto getById(Long orderId, Long itemId) {
                return orderRepository.findById(orderId).getOrderItems().stream()
                .filter(orderItem -> orderItem.getItem().getId().equals(itemId))
                .map(orderItem -> orderItemConverter.forDto(orderItem))
                .findFirst().orElseThrow(() -> new WebApplicationException("Item not found", 404));
    }

    @Override
    public OrderItemDto create(Long orderId, OrderAddItemDto orderAddItemDto){
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));

        Item item = itemRepository.findByIdOptional(orderAddItemDto.getItem().getId())
                .orElseThrow(() -> new WebApplicationException("Item not Found", 404));

        OrderItem existOrderItem = orderItemRepository.find("order.id = ?1 and item.id = ?2", order.getId(), item.getId())
                .firstResultOptional().orElse(null);

        if(existOrderItem != null){
            throw new WebApplicationException("Order already have this item", 400);
        }

        OrderItem orderItem = new OrderItem(order, item, orderAddItemDto.getQuantity());
        order.setTotal(order.getTotal() + (orderAddItemDto.getItem().getPrice() * orderAddItemDto.getQuantity()));
        orderRepository.persist(order);
        orderItemRepository.persist(orderItem);
        return orderItemConverter.forDto(orderItem);
    }

    @Override
    public void delete(Long orderId, Long itemId) {
        OrderItem existOrderItem = orderItemRepository.find("order.id = ?1 and item.id = ?2", orderId, itemId)
                .firstResultOptional().orElse(null);
        if(existOrderItem == null){
            throw new WebApplicationException("Item not found in this order", 404);
        }
        existOrderItem.getOrder().setTotal(existOrderItem.getOrder().getTotal()
                - (existOrderItem.getItem().getPrice() * existOrderItem.getQuantity()));
        orderItemRepository.delete(existOrderItem);
    }

    @Override
    public OrderItemDto update(Long orderId, OrderItemUpdateDto orderItemUpdateDto) {
        Order existingOrder = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));

        Item item = itemRepository.findByIdOptional(orderItemUpdateDto.getItem().getId())
                .orElseThrow(() -> new WebApplicationException("Item not Found", 404));

        OrderItem existingOrderItem = orderItemRepository.find("order.id = ?1 and item.id = ?2", existingOrder.getId(), item.getId())
                .firstResultOptional().orElse(null);

        if(existingOrderItem == null){
            throw new WebApplicationException("Order don't have this item", 400);
        }

        double oldPrice = existingOrderItem.getItem().getPrice() * existingOrderItem.getQuantity();

        existingOrderItem.setQuantity(orderItemUpdateDto.getQuantity());

        existingOrder.setTotal(existingOrder.getTotal() +
                (existingOrderItem.getItem().getPrice() * existingOrderItem.getQuantity()) - oldPrice);

        orderRepository.persist(existingOrder);
        orderItemRepository.persist(existingOrderItem);
        return orderItemConverter.forDto(existingOrderItem);
    }
}
