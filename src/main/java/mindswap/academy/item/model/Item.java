package mindswap.academy.item.model;

import jakarta.persistence.*;
import mindswap.academy.item.dto.ItemCreateDto;

import java.util.List;

@Entity(name = "item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Double price;

    private String description;

    @ManyToOne
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ItemCategory  getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public static final class ItemBuilder{
        private final Item item;

        public ItemBuilder() {
            item = new Item();
        }

        public ItemBuilder withName(String name){
            item.setName(name);
            return this;
        }
        public ItemBuilder withPrice(Double price){
            item.setPrice(price);
            return this;
        }
        public ItemBuilder withDescription(String description){
            item.setDescription(description);
            return this;
        }
        public ItemBuilder withItemCategory(ItemCategory itemCategory){
            item.setItemCategory(itemCategory);

            return this;
        }
        public Item build(){
            return item;
        }

    }

}
