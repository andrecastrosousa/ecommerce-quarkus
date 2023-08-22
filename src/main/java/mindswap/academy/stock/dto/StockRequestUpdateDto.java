package mindswap.academy.stock.dto;

public class StockRequestUpdateDto {
    private Long id;
    private double pricePerUnity;

    private int quantity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPricePerUnity() {
        return pricePerUnity;
    }

    public void setPricePerUnity(int pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public void setPricePerUnity(double pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
