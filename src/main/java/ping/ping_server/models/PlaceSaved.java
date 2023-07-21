package ping.ping_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PlaceSaved {
    @Id
    @Column(name="placeSaved_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String placeName;
    @Column(columnDefinition = "nvarchar(255)")
    private String address;
    private double latitude;
    private double longitude;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public PlaceSaved(String placeName, String address, double latitude, double longitude, Customer customer) {
        this.placeName = placeName;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.customer = customer;
    }

    public PlaceSaved() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "PlaceSaved{" +
                "id=" + id +
                ", placeName='" + placeName + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", customer=" + customer +
                '}';
    }
}
