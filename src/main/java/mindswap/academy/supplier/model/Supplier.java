package mindswap.academy.supplier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mindswap.academy.stock.model.StockRequest;

import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int nif;

    private int phoneNumber;

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
}
