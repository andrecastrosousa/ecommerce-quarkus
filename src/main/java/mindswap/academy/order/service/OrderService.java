package mindswap.academy.order.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.order.converter.OrderConverter;
import mindswap.academy.order.dto.OrderCreateDto;
import mindswap.academy.order.dto.OrderDto;
import mindswap.academy.model.Order;
import mindswap.academy.order.repository.OrderRepository;

import java.util.List;

@ApplicationScoped
public class OrderService {

    @Inject
    OrderRepository orderRepository;
    @Inject
    OrderConverter orderConverter;

    public List<OrderDto> getAll() {
        return null;
    }

    public OrderDto getById( Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        return orderConverter.toDto(order);
    }

    public OrderDto create( OrderCreateDto orderCreateDto) {

        return null;
    }

    public void delete( Long orderId) {
        Order order = orderRepository.findByIdOptional(orderId)
                .orElseThrow(() -> new WebApplicationException("Order not Found", 404));
        orderRepository.delete(order);
    }

    public OrderDto update( Long orderId) {

        return null;
    }
}
