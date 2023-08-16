package mindswap.academy.item.dto;

public class ItemDto {
    private String name;

    private Double price;

    public ItemDto() {
    }

    public ItemDto(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
