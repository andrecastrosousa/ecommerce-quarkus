package mindswap.academy.stock.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.stock.dto.StockRequestCreateDto;
import mindswap.academy.stock.dto.StockRequestDto;
import mindswap.academy.stock.dto.StockRequestUpdateDto;
import mindswap.academy.stock.model.StockRequest;

@ApplicationScoped
public class StockRequestConverter {

    @Inject
    ObjectMapper objectMapper;

    public StockRequestDto toDto(StockRequest stockRequest){
        return objectMapper.convertValue(stockRequest, StockRequestDto.class);
    }

    public StockRequest toEntityFromCreateDto(StockRequestCreateDto stockRequestCreateDto){
        return StockRequest.builder()
                .withPricePerUnity(stockRequestCreateDto.getPricePerUnit())
                .withQuantity(stockRequestCreateDto.getQuantity())
                .withReceivedDate(stockRequestCreateDto.getReceivedDate())
                .withRequestDate(stockRequestCreateDto.getRequestDate())
                .withSupplier(stockRequestCreateDto.getSupplier())
                .build();

    }

    public StockRequest toEntityFromUpdateDto(StockRequestUpdateDto stockRequestUpdateDtoDto){
        return StockRequest.builder()
                .withId(stockRequestUpdateDtoDto.getId())
                .withPricePerUnity(stockRequestUpdateDtoDto.getPricePerUnity())
                .withQuantity(stockRequestUpdateDtoDto.getQuantity())
                .build();
    }

    public StockRequest fromDto(StockRequestDto stockRequestDto){
        return objectMapper.convertValue(stockRequestDto, StockRequest.class);
    }
}
