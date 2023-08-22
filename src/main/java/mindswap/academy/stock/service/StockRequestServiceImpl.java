package mindswap.academy.stock.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.stock.converter.StockRequestConverter;
import mindswap.academy.stock.dto.StockRequestCreateDto;
import mindswap.academy.stock.dto.StockRequestDto;
import mindswap.academy.stock.dto.StockRequestUpdateDto;
import mindswap.academy.stock.model.Stock;
import mindswap.academy.stock.model.StockRequest;
import mindswap.academy.stock.repository.StockRepository;
import mindswap.academy.stock.repository.StockRequestRepository;

import java.util.List;

@ApplicationScoped
public class StockRequestServiceImpl implements StockRequestService{

    @Inject
    StockRequestRepository stockRequestRepository;

    @Inject
    StockRequestConverter stockRequestConverter;

    @Inject
    StockRepository stockRepository;


    @Override
    public List<StockRequestDto> getAll() {
        return stockRequestRepository.listAll()
                .stream()
                .map(stockRequest -> stockRequestConverter.toDto(stockRequest))
                .toList();
    }

    @Override
    public StockRequestDto getById(Long id) {
        StockRequest stockRequest = stockRequestRepository.findById(id);
        if(stockRequest == null){
            throw new WebApplicationException("Stock Request not found", 404);
        }
        return stockRequestConverter.toDto(stockRequest);
    }

    @Override
    public StockRequestDto update(Long id, StockRequestUpdateDto stockRequestUpdateDto) {
       StockRequest stockRequestFound = stockRequestRepository.findById(id);
       StockRequest stockRequest = stockRequestConverter.toEntityFromUpdateDto(stockRequestUpdateDto);
       if(stockRequestFound == null){
           throw new WebApplicationException("Stock Request not found", 404);
       }
       if(!stockRequest.getId().equals(id)){
           throw new WebApplicationException("Stock Request ID not valid.", 400);
       }
       stockRequestFound.setQuantity(stockRequest.getQuantity());
       stockRequestFound.setPricePerUnit(stockRequest.getPricePerUnit());
       stockRequestRepository.persist(stockRequestFound);
       return stockRequestConverter.toDto(stockRequestFound);
    }

    @Override
    public StockRequestDto create(StockRequestCreateDto stockRequestCreateDto) {
        StockRequest stockRequest = stockRequestConverter.toEntityFromCreateDto(stockRequestCreateDto);
        Stock stock = stockRepository.findBySupplierIdAndItemId(stockRequest.getSupplier().getId(), stockRequestCreateDto.getItem().getId());
        if(stock == null){
            throw new WebApplicationException("Supplier doesn't have this item!", 404);
        }
        stockRequest.setStock(stock);
        stockRequestRepository.persist(stockRequest);
        return stockRequestConverter.toDto(stockRequest);
    }

    @Override
    public void delete(Long id) {

    }
}
