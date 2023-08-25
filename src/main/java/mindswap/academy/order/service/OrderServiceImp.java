package mindswap.academy.order.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.order.converter.OrderConverter;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderUpdateDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderServiceImp implements OrderService {

    @Inject
    OrderRepository orderRepository;
    @Inject
    OrderConverter orderConverter;

    @Override
    public List<mindswap.academy.order.dto.OrderDto> getAll() {
       List<Order> orders = orderRepository.findAll().list();
       List<mindswap.academy.order.dto.OrderDto> orderDtoDtos = new ArrayList<>();
       for(Order o : orders){
           orderDtoDtos.add(orderConverter.toDto(o));
       }
       return orderDtoDtos;
    }

    @Override
    public mindswap.academy.order.dto.OrderDto getById(Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        return orderConverter.toDto(order);
    }

    @Override
    public mindswap.academy.order.dto.OrderDto create(OrderCreateDto orderCreateDto) {
        Order order = orderConverter.fromOrderCreateDto(orderCreateDto);
        orderRepository.persist(order);
        return orderConverter.toDto(order);
    }

    @Override
    public void delete(Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        orderRepository.delete(order);
    }

    @Override
    public mindswap.academy.order.dto.OrderDto update(Long orderId, OrderUpdateDto orderUpdateDto) {
        Order existingOrder = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
        if(!orderUpdateDto.getId().equals(existingOrder.getId())){
            throw new WebApplicationException("Id can not be changed", 400);
        }
        existingOrder.setOrderDatetime(orderUpdateDto.getOrderDatetime());
        orderRepository.persist(existingOrder);
        return orderConverter.toDto(existingOrder);
    }
}
