package mindswap.academy.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OrderCreateDto {


    @NotNull
    private LocalDateTime orderDatetime;

    public OrderCreateDto() {
    }

    public OrderCreateDto(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public LocalDateTime getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(LocalDateTime orderDatetime) {
        this.orderDatetime = orderDatetime;
    }
}
