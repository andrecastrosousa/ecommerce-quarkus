package mindswap.academy.order.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.order.converter.OrderConverter;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderDto;
import mindswap.academy.order.dto.OrderUpdatedDto;
import mindswap.academy.order.model.Order;
import mindswap.academy.order.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;
    @Inject
    OrderConverter orderConverter;

    public List<OrderDto> getAll() {
       List<Order> orders = orderRepository.findAll().list();
       List<OrderDto> orderDtos = new ArrayList<>();
       for(Order o : orders){
           orderDtos.add(orderConverter.toDto(o));
       }
       return orderDtos;
    }

    public OrderDto getById(Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        return orderConverter.toDto(order);
    }

    public OrderDto create(OrderCreateDto orderCreateDto) {
        Order order = orderConverter.fromOrderCreateDto(orderCreateDto);
        orderRepository.persist(order);
        return orderConverter.toDto(order);
    }

    public void delete(Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        orderRepository.delete(order);
    }

    public OrderDto update(Long orderId, OrderUpdatedDto orderUpdatedDto) {
        Order existingOrder = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not found", 404));
        if(!orderUpdatedDto.getOrderItems().equals(existingOrder.getOrderItems())){
            throw new WebApplicationException("Order items can not be changed", 400);
        }
        if(!orderUpdatedDto.getPaymentMethod().equals(existingOrder.getPaymentMethod())){
            throw new WebApplicationException("Order payment can not be changed", 400);
        }
        if(!(orderUpdatedDto.getTotal() == existingOrder.getTotal())) {
            throw new WebApplicationException("Order price can not be changed", 400);
        }
        existingOrder.setOrderDatetime(orderUpdatedDto.getOrderDatetime());
        existingOrder.setShipping(orderUpdatedDto.getShipping());
        orderRepository.persist(existingOrder);
        return orderConverter.toDto(existingOrder);
    }
}
