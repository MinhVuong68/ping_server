package ping.ping_server.models.dto.OrderDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDTO {
    private String address;
    private CoordinatesDTO coordinate;

    public LocationDTO() {
    }

    public LocationDTO(String address, CoordinatesDTO coordinate) {
        this.address = address;
        this.coordinate = coordinate;
    }
}
