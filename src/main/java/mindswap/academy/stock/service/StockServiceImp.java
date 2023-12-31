package mindswap.academy.stock.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.item.converter.ItemConverter;
import mindswap.academy.item.dto.ItemDto;
import mindswap.academy.item.model.Item;
import mindswap.academy.item.repository.ItemRepository;
import mindswap.academy.stock.converter.StockConverter;
import mindswap.academy.stock.dto.StockDto;
import mindswap.academy.stock.dto.StockItemDto;
import mindswap.academy.stock.dto.StockSupplierDto;
import mindswap.academy.stock.model.Stock;
import mindswap.academy.stock.repository.StockRepository;
import mindswap.academy.supplier.converter.SupplierConverter;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.repository.SupplierRepository;


@ApplicationScoped
public class StockServiceImp implements StockService {

    @Inject
    StockRepository stockRepository;
    @Inject
    ItemRepository itemRepository;
    @Inject
    SupplierRepository supplierRepository;
    @Inject
    StockConverter stockConverter;

    @Override
    public StockItemDto getByItemId(Long itemId) {
        Item existingItem = itemRepository.findByIdOptional(itemId)
                .orElseThrow(() -> new WebApplicationException("Item not found", 404));
        Stock stock = stockRepository.find("item.id", existingItem.getId()).firstResultOptional()
                .orElse(null);
        if(stock == null){
            throw new WebApplicationException("Item not found", 404);
        }
        return stockConverter.fromStockToStockItemDto(stock);
    }

    @Override
    public StockSupplierDto getBySupplierId(Long supplierId) {
        Supplier supplier = supplierRepository.findByIdOptional(supplierId)
                .orElseThrow(() -> new WebApplicationException("Supplier not found", 404));
        Stock stock = stockRepository.find("supplier.id", supplier.getId()).firstResultOptional()
                .orElse(null);
        if(stock == null){
            throw new WebApplicationException("Supplier not found", 404);
        }
        return stockConverter.fromStockToStockSupplierDto(stock);
    }


    @Override
    public StockDto getItemStockFromSupplier(Long supplierId, Long itemId) {
        Stock stock = stockRepository.findBySupplierIdAndItemId(supplierId, itemId);
        if(stock == null){
            throw new WebApplicationException("Item not found in supplier stock", 404);
        }
        return stockConverter.fromStock(stock);
    }
}
