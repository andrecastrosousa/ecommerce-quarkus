package mindswap.academy.stock.dto;

import mindswap.academy.item.model.Item;
import mindswap.academy.supplier.model.Supplier;

import java.time.LocalDateTime;

public class StockRequestCreateDto {
    private Long id;
    private double pricePerUnit;
    private int quantity;
    private LocalDateTime requestDate;
    private LocalDateTime receivedDate;
    private Supplier supplier;
    private Item item;

    public StockRequestCreateDto(Supplier supplier, Item item) {
        this.supplier = supplier;
        this.item = item;
    }

    public StockRequestCreateDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Long pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
