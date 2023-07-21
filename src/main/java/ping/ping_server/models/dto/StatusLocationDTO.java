package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.DriverStatus;

@Getter
@Setter
@Builder
public class StatusLocationDTO {
    private Long id;
    private DriverStatus driverStatus;
    private String currentLocation;
    private Double latitude;
    private Double longitude;
}
