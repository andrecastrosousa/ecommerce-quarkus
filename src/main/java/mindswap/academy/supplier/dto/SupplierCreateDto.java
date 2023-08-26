package mindswap.academy.supplier.dto;

import mindswap.academy.address.model.Address;
import mindswap.academy.supplier.model.SupplierCategory;

import java.util.List;

public class SupplierCreateDto {
    private Long id;
    private String name;
    private int nif;
    private int phoneNumber;
    private List<Address> addresses;
    private List<SupplierCategory> supplierCategory;

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

    public List<Address> getAddress() {
        return addresses;
    }

    public void setAddress(List<Address> address) {
        this.addresses = address;
    }

    public List<SupplierCategory> getSupplierCategory() {
        return supplierCategory;
    }

    public void setSupplierCategory(List<SupplierCategory> supplierCategory) {
        this.supplierCategory = supplierCategory;
    }

}
