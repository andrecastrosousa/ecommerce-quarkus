package mindswap.academy.user.model;

import jakarta.persistence.*;
import mindswap.academy.address.model.Address;
import mindswap.academy.order.model.Order;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;

    private int phoneNumber;

    private int nif;

    private String citizenCard;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getCitizenCard() {
        return citizenCard;
    }

    public void setCitizenCard(String citizenCard) {
        this.citizenCard = citizenCard;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static UserBuilder builder(){
        return new UserBuilder();
    }

    public static final class UserBuilder{
        private final User user;

        UserBuilder(){
            user = new User();
        }

        public UserBuilder withEmail(String email){
            user.setEmail(email);
            return this;
        }

        public UserBuilder withId(Long id){
            user.setId(id);
            return this;
        }

        public UserBuilder withNif(int nif){
            user.setNif(nif);
            return this;
        }

        public UserBuilder withPhoneNumber(int phoneNumber){
            user.setPhoneNumber(phoneNumber);
            return this;
        }
        public UserBuilder withCitizenCard(String citizenCard){
            user.setCitizenCard(citizenCard);
            return this;
        }

        public UserBuilder withAddress(List<Address> addresses){
            user.setAddresses(addresses);
            return this;
        }
        public UserBuilder withOrders(List<Order> orders){
            user.setOrders(orders);
            return this;
        }

        public User build(){
            return user;
        }
    }
}
