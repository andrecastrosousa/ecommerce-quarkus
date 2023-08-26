package mindswap.academy.stock.dto;

import mindswap.academy.item.dto.ItemDto;

public class StockItemDto {

    private ItemDto itemDto;
    private int quantity;

    public StockItemDto() {
    }

    public StockItemDto(ItemDto itemDto, int quantity) {
        this.itemDto = itemDto;
        this.quantity = quantity;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public void setItemDto(ItemDto itemDto) {
        this.itemDto = itemDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
