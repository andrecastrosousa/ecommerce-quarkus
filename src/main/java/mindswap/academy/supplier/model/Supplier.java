package mindswap.academy.supplier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mindswap.academy.address.model.Address;
import mindswap.academy.stock.model.StockRequest;

import java.util.List;

@Entity (name = "Supplier" )
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nif;
    private int phoneNumber;

    @OneToMany(mappedBy = "supplier")
    private List<Address> addresses;

    @ManyToMany
    private List<SupplierCategory> categories;

    @OneToMany
    @JsonIgnore
    private List<StockRequest> stockRequests;

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

    public List<StockRequest> getStockRequests() {
        return stockRequests;
    }

    public void setStockRequests(List<StockRequest> stockRequests) {
        this.stockRequests = stockRequests;
    }

    public List<SupplierCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SupplierCategory> categories) {
        this.categories = categories;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public static SupplierBuilder builder() {
        return new SupplierBuilder();
    }
    public static final class SupplierBuilder{
        private final Supplier supplier;

        SupplierBuilder() {
            supplier = new Supplier();
        }
        public SupplierBuilder withId(Long id){
            supplier.setId(id);
            return this;
        }
        public SupplierBuilder withName(String name){
            supplier.setName(name);
            return this;
        }
        public SupplierBuilder withNif(int nif){
            supplier.setNif(nif);
            return this;
        }
        public SupplierBuilder withPhoneNumber(int phoneNumber){
            supplier.setPhoneNumber(phoneNumber);
            return this;
        }
        public SupplierBuilder withAddress(List<Address> addresses){
            supplier.setAddresses(addresses);
            return this;
        }
        public SupplierBuilder withSupplierCategory(List<SupplierCategory> supplierCategory){
            supplier.setCategories(supplierCategory);
            return this;
        }
        public SupplierBuilder withStockRequest(List<StockRequest> stockRequests){
            supplier.setStockRequests(stockRequests);
            return this;
        }
        public Supplier build(){
            return supplier;
        }


    }
}
