package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DriverResponse {
    private Long id;
    private String fullName;
    private String avatar;
    private String phoneNumber;
    private double reviewRate;
    private String currentLocation;
    private Double latitude;
    private Double longitude;
    private String licensePlate;
    private String token;

    public DriverResponse() {
    }

    public DriverResponse(Long id, String fullName, String avatar, String phoneNumber, double reviewRate, String currentLocation, double latitude, double longitude, String licensePlate, String token) {
        this.id = id;
        this.fullName = fullName;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.reviewRate = reviewRate;
        this.currentLocation = currentLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.licensePlate = licensePlate;
        this.token = token;
    }

}
