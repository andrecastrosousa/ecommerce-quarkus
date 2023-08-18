package mindswap.academy.order.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.order.converter.OrderItemConverter;
import mindswap.academy.order.dto.OrderItemCreateDto;
import mindswap.academy.order.dto.OrderItemDto;
import mindswap.academy.order.dto.OrderItemUpdatedDto;
import mindswap.academy.order.model.OrderItem;
import mindswap.academy.order.repository.OrderItemRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderItemServiceImp implements OrderItemService {

    @Inject
    OrderItemRepository orderItemRepository;

    @Inject
    OrderItemConverter orderItemConverter;


    @Override
    public List<OrderItemDto> getAll() {
        List<OrderItem> orderItems = orderItemRepository.findAll().list();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for(OrderItem oi : orderItems){
           orderItemDtos.add(orderItemConverter.forDto(oi));
        }

        return orderItemDtos;
    }


    @Override
    public OrderItemDto getById(Long id) {
        OrderItem orderItem = orderItemRepository.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
        return orderItemConverter.forDto(orderItem);
    }


    @Override
    public OrderItemDto create(OrderItemCreateDto orderItemCreateDto) {
        OrderItem orderItem = orderItemConverter.fromOrderItemCreateDto(orderItemCreateDto);
        orderItemRepository.persist(orderItem);
        return orderItemConverter.forDto(orderItem);
    }

    @Override
    public void delete(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findByIdOptional(orderItemId)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
        orderItemRepository.delete(orderItem);
    }

    @Override
    public OrderItemDto update(Long orderItemId, OrderItemUpdatedDto orderItemUpdatedDto) {
        OrderItem existingOrderItem = orderItemRepository.findByIdOptional(orderItemId)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
        if(!orderItemUpdatedDto.getId().equals(existingOrderItem.getId())){
            throw new WebApplicationException("Order item id can not be changed", 400);
        }
        existingOrderItem.setQuantity(orderItemUpdatedDto.getQuantity());
        orderItemRepository.persist(existingOrderItem);
        return orderItemConverter.forDto(existingOrderItem);
    }
}
