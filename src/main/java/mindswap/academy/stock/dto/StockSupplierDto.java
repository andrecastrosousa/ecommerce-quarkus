package mindswap.academy.stock.dto;

import mindswap.academy.supplier.dto.SupplierDto;

public class StockSupplierDto {

    private SupplierDto supplierDto;
    private int quantity;

    public StockSupplierDto() {
    }

    public StockSupplierDto(SupplierDto supplierDto, int quantity) {
        this.supplierDto = supplierDto;
        this.quantity = quantity;
    }

    public SupplierDto getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(SupplierDto supplierDto) {
        this.supplierDto = supplierDto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
