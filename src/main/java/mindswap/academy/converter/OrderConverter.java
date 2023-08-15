package mindswap.academy.converter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.dto.OrderCreateDto;
import mindswap.academy.dto.OrderDto;
import mindswap.academy.model.Order;

@ApplicationScoped
public class OrderConverter {

    @Inject
    ObjectMapper objectMapper;

    public OrderDto toDto(Order order){
        return null;
    }

    public Order fromOrderCreateDto(OrderCreateDto orderCreateDto){
        return null;
    }
}
