package mindswap.academy.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mindswap.academy.payment.model.PaymentMethod;
import mindswap.academy.shipping.model.Shipping;
import mindswap.academy.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private double total;
    private LocalDateTime orderDatetime;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToOne
    private Shipping shipping;

    @ManyToOne
    private PaymentMethod paymentMethod;

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static OrderBuilder builder(){
       return new OrderBuilder();
    }

    public static class OrderBuilder {

        private Order order;


        public OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder withTotal(double total){
            order.setTotal(total);
            return this;
        }

        public OrderBuilder withDate(LocalDateTime localDateTime){
            order.setOrderDatetime(localDateTime);
            return this;
        }

        public OrderBuilder withItems(List<OrderItem> orderItems){
            order.setOrderItems(orderItems);
            return this;
        }

        public OrderBuilder withUser(User user){
            order.setUser(user);
            return this;
        }

        public OrderBuilder withShipping(Shipping shipping){
            order.setShipping(shipping);
            return this;
        }

        public OrderBuilder withPaymentMethod(PaymentMethod paymentMethod){
            order.setPaymentMethod(paymentMethod);
            return this;
        }

        public Order build(){
            return order;
        }

    }
}
