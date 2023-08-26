package mindswap.academy.supplier.service;

import mindswap.academy.address.model.Address;
import mindswap.academy.supplier.dto.SupplierCreateDto;
import mindswap.academy.supplier.dto.SupplierDto;
import mindswap.academy.supplier.dto.SupplierUpdateDto;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.model.SupplierCategory;

import java.util.List;

public interface SupplierService {

    List<SupplierDto> getAll();

    SupplierDto getById(Long id);
    SupplierDto update(Long id, SupplierUpdateDto supplierUpdateDto);
    SupplierDto create(SupplierCreateDto supplierCreateDto);
    void delete(Long id);
}
