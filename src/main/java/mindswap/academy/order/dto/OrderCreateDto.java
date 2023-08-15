package mindswap.academy.order.dto;

import java.time.LocalDateTime;

public class OrderCreateDto {


    private LocalDateTime orderDatetime;

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }
}
