package mindswap.academy.order.dto;

import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.order.model.Order;

public class OrderItemDto {

    private Long id;

    //Meter orderdto
    private OrderDto orderDto;
    //Meter itemdto
    private ItemDto itemDto;
    private int quantity;

    public OrderItemDto(Long id, OrderDto orderDto, ItemDto itemDto, int quantity) {
        this.id = id;
        this.orderDto = orderDto;
        this.itemDto = itemDto;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
