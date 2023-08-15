package mindswap.academy.shipping.model;

import jakarta.persistence.*;
import mindswap.academy.address.model.Address;

@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Address address;

    @ManyToOne
    private ShippingType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShippingType getType() {
        return type;
    }

    public void setType(ShippingType type) {
        this.type = type;
    }
}
