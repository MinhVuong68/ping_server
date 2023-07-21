package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateStatusAndLocationResponse {
    private String currentLocation;
    private Double latitude;
    private Double longitude;

    public UpdateStatusAndLocationResponse() {
    }

    public UpdateStatusAndLocationResponse(String currentLocation, Double latitude, Double longitude) {
        this.currentLocation = currentLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
