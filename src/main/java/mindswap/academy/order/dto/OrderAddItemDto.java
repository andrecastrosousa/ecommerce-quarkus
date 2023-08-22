package mindswap.academy.order.dto;

import mindswap.academy.item.model.Item;

public class OrderAddItemDto {

    private Item item;
    private int quantity;

    public OrderAddItemDto() {
    }

    public OrderAddItemDto(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
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
