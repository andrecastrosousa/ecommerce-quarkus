package mindswap.academy.stock.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.stock.dto.StockDto;
import mindswap.academy.stock.dto.StockItemDto;
import mindswap.academy.stock.dto.StockSupplierDto;
import mindswap.academy.stock.model.Stock;
import mindswap.academy.supplier.converter.SupplierConverter;
import mindswap.academy.supplier.dto.SupplierDto;



@ApplicationScoped
public class StockConverter {

    @Inject
    ObjectMapper objectMapper;
    @Inject
    SupplierConverter supplierConverter;
    @Inject
    ItemConverter itemConverter;

    public StockDto fromStock(Stock stock){
        return objectMapper.convertValue(stock, StockDto.class);
    }

    public StockItemDto fromStockToStockItemDto(Stock stock){
        ItemDto itemDto = itemConverter.toDto(stock.getItem());
        return new StockItemDto(itemDto, stock.getQuantity());
    }

    public StockSupplierDto fromStockToStockSupplierDto(Stock stock){
        SupplierDto supplierDto = supplierConverter.toDto(stock.getSupplier());
        return new StockSupplierDto(supplierDto, stock.getQuantity());
    }

}
