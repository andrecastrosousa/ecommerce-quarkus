package mindswap.academy.order.dto;

import mindswap.academy.item.model.Item;
import mindswap.academy.order.model.Order;

public class OrderItemUpdatedDto {
    private Long id;
    private Order order;
    private Item item;
    private int quantity;

    public OrderItemUpdatedDto(Long id, Order order, Item item, int quantity) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
