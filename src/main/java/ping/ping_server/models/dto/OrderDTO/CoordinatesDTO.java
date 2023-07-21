package ping.ping_server.models.dto.OrderDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatesDTO {
    private double latitude;
    private double longitude;

    public CoordinatesDTO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CoordinatesDTO() {
    }
}
