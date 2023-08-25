package mindswap.academy.item.dto;

import java.util.List;

public class ItemCategoryDto {
    private String name;

    public ItemCategoryDto(String name, List<String> items) {
        this.name = name;
        this.items = items;
    }

    public List<String> items;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    //retirar adicionar endpoint no items getByCategory
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
