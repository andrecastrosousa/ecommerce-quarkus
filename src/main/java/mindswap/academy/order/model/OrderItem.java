package mindswap.academy.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mindswap.academy.item.model.Item;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity (name ="OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Item item;

    private int quantity;

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

    public static OrderItemBuilder builder(){
        return new OrderItemBuilder();
    }

    public static class OrderItemBuilder{

        private OrderItem orderItem;

        public OrderItemBuilder() {
            orderItem = new OrderItem();
        }

        public OrderItemBuilder withOrder(Order order){
            orderItem.setOrder(order);
            return this;
        }

        public OrderItemBuilder withItem(Item item){
            orderItem.setItem(item);
            return this;
        }

        public OrderItemBuilder withQuantity(int quantity){
            orderItem.setQuantity(quantity);
            return this;
        }

        public OrderItem build(){
            return orderItem;
        }
    }
}
