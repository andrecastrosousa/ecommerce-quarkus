package mindswap.academy.supplier.converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mindswap.academy.supplier.dto.SupplierCreateDto;
import mindswap.academy.supplier.dto.SupplierDto;
import mindswap.academy.supplier.dto.SupplierUpdateDto;
import mindswap.academy.supplier.model.Supplier;

@ApplicationScoped
public class SupplierConverter {

    @Inject
    ObjectMapper objectMapper;

    public SupplierDto toDto(Supplier supplier){
        return objectMapper.convertValue(supplier, SupplierDto.class);
    }

    public Supplier fromDto(SupplierDto supplierDto){
        return objectMapper.convertValue(supplierDto, Supplier.class);
    }

    public Supplier toEntityFromCreateDto(SupplierCreateDto supplierCreateDto){
        return Supplier.builder()
                .withId(supplierCreateDto.getId())
                .withNif(supplierCreateDto.getNif())
                .withName(supplierCreateDto.getName())
                .withPhoneNumber(supplierCreateDto.getPhoneNumber())
                .withAddress(supplierCreateDto.getAddress())
                .withSupplierCategory(supplierCreateDto.getSupplierCategory())
                .build();
    }

    public Supplier toEntityFromUpdateDto(SupplierUpdateDto supplierUpdateDto){
        return Supplier.builder()
                .withId(supplierUpdateDto.getId())
                .withNif(supplierUpdateDto.getNif())
                .withName(supplierUpdateDto.getName())
                .withPhoneNumber(supplierUpdateDto.getPhoneNumber())
                .withAddress(supplierUpdateDto.getAddress())
                .withSupplierCategory(supplierUpdateDto.getSupplierCategory())
                .build();

    }
}
