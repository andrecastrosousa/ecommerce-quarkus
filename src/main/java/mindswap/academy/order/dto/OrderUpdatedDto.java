package mindswap.academy.order.dto;

import mindswap.academy.order.model.OrderItem;
import mindswap.academy.payment.model.PaymentMethod;
import mindswap.academy.shipping.model.Shipping;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUpdatedDto {

    private Long id;
    private double total;
    private LocalDateTime orderDatetime;
    private List<OrderItem> orderItems;
    private Shipping shipping;
    private PaymentMethod paymentMethod;


    public OrderUpdatedDto() {
    }

    public OrderUpdatedDto(Long id, double total, LocalDateTime orderDatetime, List<OrderItem> orderItems, Shipping shipping, PaymentMethod paymentMethod) {
        this.id = id;
        this.total = total;
        this.orderDatetime = orderDatetime;
        this.orderItems = orderItems;
        this.shipping = shipping;
        this.paymentMethod = paymentMethod;
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

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
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
}
