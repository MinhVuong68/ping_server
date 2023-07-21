package ping.ping_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"password"})
//@Builder
public class Driver extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @Column(name = "full_name", columnDefinition = "nvarchar(80)")
    private String fullName;

    private String avatar;

    @Column(columnDefinition = "varchar(11)")
    private String phoneNumber;

    private String password;

    @Column(name = "review_rate",precision = 3, scale = 1)
    private double reviewRate;

    @Column(name = "current_location", columnDefinition = "nvarchar(255)",nullable = true)
    private String currentLocation;

    @Column(nullable = true)
    private Double latitude;
    @Column(nullable = true)
    private Double longitude;
    @Enumerated(EnumType.STRING)
    private DriverStatus driverStatus;

    @Column(columnDefinition = "varchar(10)")
    private String licensePlate;

    @OneToOne(mappedBy = "driver")
    private DriverProfile driverProfile;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "driver")
    private List<Order> orders;

    public Driver() {
    }

    public Driver(Long id, String fullName, String avatar, String phoneNumber, String password, double reviewRate, String currentLocation, Double latitude, Double longitude, DriverStatus driverStatus, String licensePlate, Vehicle vehicle) {
        this.id = id;
        this.fullName = fullName;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.reviewRate = reviewRate;
        this.currentLocation = currentLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverStatus = driverStatus;
        this.licensePlate = licensePlate;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(double reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
