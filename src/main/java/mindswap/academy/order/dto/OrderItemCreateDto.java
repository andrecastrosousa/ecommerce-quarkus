package mindswap.academy.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import mindswap.academy.item.model.Item;
import mindswap.academy.order.model.Order;

public class OrderItemCreateDto {


    @NotNull
    private Order order;
    @NotNull
    private Item item;
    @NotNull
    private int quantity;

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
