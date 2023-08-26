package mindswap.academy.supplier.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import mindswap.academy.supplier.converter.SupplierConverter;
import mindswap.academy.supplier.dto.SupplierCreateDto;
import mindswap.academy.supplier.dto.SupplierDto;
import mindswap.academy.supplier.dto.SupplierUpdateDto;
import mindswap.academy.supplier.model.Supplier;
import mindswap.academy.supplier.repository.SupplierCategoryRepository;
import mindswap.academy.supplier.repository.SupplierRepository;

import java.util.List;

@ApplicationScoped
public class SupplierImpl implements SupplierService {

    @Inject
    SupplierRepository supplierRepository;

    @Inject
    SupplierConverter supplierConverter;


    @Override
    public List<SupplierDto> getAll() {
        return supplierRepository.listAll()
                .stream()
                .map(supplier -> supplierConverter.toDto(supplier))
                .toList();
    }

    @Override
    public SupplierDto getById(Long id) {
        Supplier supplier = supplierRepository.findById(id);
        if(supplier == null){
            throw new WebApplicationException("Supplier not found.", 404);
        }
        return supplierConverter.toDto(supplier);
    }

    @Override
    public SupplierDto update(Long id, SupplierUpdateDto supplierUpdateDto) {
        Supplier supplierFound = supplierRepository.findById(id);
        Supplier supplier = supplierConverter.toEntityFromUpdateDto(supplierUpdateDto);
        if(supplierFound == null){
            throw new WebApplicationException("Supplier not found", 404);
        }
        if(!supplier.getId().equals(id)){
            throw new WebApplicationException("Supplier ID not valid", 404);
        }
        supplierFound.setName(supplier.getName());
        supplierFound.setNif(supplier.getNif());
        supplierFound.setPhoneNumber(supplier.getPhoneNumber());
        supplierFound.setCategories(supplier.getCategories());
        supplierFound.setAddresses(supplier.getAddresses());
        supplierRepository.persist(supplierFound);
        return supplierConverter.toDto(supplierFound);
    }

    @Override
    public SupplierDto create(SupplierCreateDto supplierCreateDto) {
        Supplier supplier = supplierConverter.toEntityFromCreateDto(supplierCreateDto);
        supplierRepository.persist(supplier);
        return supplierConverter.toDto(supplier);
    }

    @Override
    public void delete(Long id) {
    Supplier supplier = supplierRepository.findById(id);
    if(supplier == null){
        throw new WebApplicationException("Supplier not found", 404);
    }
    supplierRepository.delete(supplier);
    }
}
