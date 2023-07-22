package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.DriverStatus;

@Getter
@Setter
@Builder
public class UpdateStatusAndLocationResponse {
    private String currentLocation;
    private Double latitude;
    private Double longitude;
    private DriverStatus driverStatus;

    public UpdateStatusAndLocationResponse() {
    }

    public UpdateStatusAndLocationResponse(String currentLocation, Double latitude, Double longitude, DriverStatus driverStatus) {
        this.currentLocation = currentLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driverStatus = driverStatus;
    }
}
