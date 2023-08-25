package mindswap.academy.stock.model;

import jakarta.persistence.*;
import mindswap.academy.supplier.model.Supplier;

import java.time.LocalDateTime;

@Entity(name = "StockRequest")
public class StockRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Stock stock;

    private double pricePerUnit;

    private int quantity;

    private LocalDateTime requestDate;

    private LocalDateTime receivedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
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

    public static StockRequestBuilder builder(){
        return new StockRequestBuilder();
    }
    public static final class StockRequestBuilder{
        private final StockRequest stockRequest;

        StockRequestBuilder(){
            stockRequest = new StockRequest();
        }

        public StockRequestBuilder withId(Long id){
            stockRequest.setId(id);
            return this;
        }

        public StockRequestBuilder withPricePerUnity(Double pricePerUnity){
            stockRequest.setPricePerUnit(pricePerUnity);
            return this;
        }

        public StockRequestBuilder withSupplier(Supplier supplier){
            stockRequest.setSupplier(supplier);
            return this;
        }
        public StockRequestBuilder withQuantity(int quantity){
            stockRequest.setQuantity(quantity);
            return this;
        }

        public StockRequestBuilder withReceivedDate(LocalDateTime receivedDate){
            stockRequest.setReceivedDate(receivedDate);
            return this;
        }
        public StockRequestBuilder withRequestDate(LocalDateTime requestDate){
            stockRequest.setRequestDate(requestDate);
            return this;
        }

        public StockRequestBuilder withStock(Stock stock) {
            stockRequest.setStock(stock);
            return this;
        }

        public StockRequest build(){
            return stockRequest;
        }
    }
}
