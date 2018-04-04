package com.malinovski.helpdesk.model;

        import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    private long addressId;
    private String street;
    private String city;
    private String state;
    private User user;

    public Address() {
    }


    @Id
    @GeneratedValue
    @Column(name = "ID")
    public long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    @Column(name = "STREET", nullable = false, length=250)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "CITY", nullable = false, length=50)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STATE", nullable = false, length=50)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
