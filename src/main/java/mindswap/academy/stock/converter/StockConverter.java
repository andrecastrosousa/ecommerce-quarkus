package mindswap.academy.stock.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import mindswap.academy.stock.dto.StockDto;
import mindswap.academy.stock.model.Stock;

public class StockConverter {

    @Inject
    ObjectMapper objectMapper;

    public StockDto fromStock(Stock stock){
        return objectMapper.convertValue(stock, StockDto.class);
    }
}
