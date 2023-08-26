package mindswap.academy.supplier.dto;

import jdk.jfr.Category;
import mindswap.academy.address.model.Address;
import mindswap.academy.stock.model.StockRequest;
import mindswap.academy.supplier.model.SupplierCategory;

public class SupplierDto {
    private Long id;
    private String name;
    private int nif;
    private int phoneNumber;
    private Address address;
    private SupplierCategory supplierCategory;
    private StockRequest stockRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public SupplierCategory getSupplierCategory() {
        return supplierCategory;
    }

    public void setSupplierCategory(SupplierCategory supplierCategory) {
        this.supplierCategory = supplierCategory;
    }

    public StockRequest getStockRequest() {
        return stockRequest;
    }

    public void setStockRequest(StockRequest stockRequest) {
        this.stockRequest = stockRequest;
    }
}
