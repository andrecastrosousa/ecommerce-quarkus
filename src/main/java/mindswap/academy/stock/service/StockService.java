package mindswap.academy.stock.service;

import mindswap.academy.stock.dto.StockDto;
import mindswap.academy.stock.dto.StockItemDto;
import mindswap.academy.stock.dto.StockSupplierDto;

public interface StockService {
    StockItemDto getByItemId(Long itemId);

    StockSupplierDto getBySupplierId(Long supplierId);

    StockDto getItemStockFromSupplier(Long supplierId, Long itemId);
}
