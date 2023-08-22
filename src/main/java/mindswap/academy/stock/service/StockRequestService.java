package mindswap.academy.stock.service;

import mindswap.academy.stock.dto.StockRequestCreateDto;
import mindswap.academy.stock.dto.StockRequestDto;
import mindswap.academy.stock.dto.StockRequestUpdateDto;

import java.util.List;

public interface StockRequestService {

    List<StockRequestDto> getAll();

    StockRequestDto getById(Long id);
    StockRequestDto update(Long id, StockRequestUpdateDto stockRequestUpdateDto);

    StockRequestDto create(StockRequestCreateDto stockRequestCreateDto);

    void delete(Long id);
}
