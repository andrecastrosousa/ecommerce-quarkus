package mindswap.academy.item.model;

import jakarta.persistence.*;
import mindswap.academy.item.dto.ItemCreateDto;

import java.util.List;

@Entity(name = "Items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Double price;

    private String description;

    @ManyToMany
    private List<ItemCategory> categories;

    @OneToMany
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

    public List<ItemCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ItemCategory> categories) {
        this.categories = categories;
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
        public ItemBuilder withCategory(List<ItemCategory> categories){
            item.setCategories(categories);
            return this;
        }
        public Item build(){
            return item;
        }

    }

}
