package mindswap.academy.stock.service;

import mindswap.academy.stock.dto.StockDto;

public interface StockService {
    StockDto getByItemId(Long itemId);

    StockDto getBySupplierId(Long supplierId);

    StockDto getItemStockFromSupplier(Long supplierId, Long itemId);
}
