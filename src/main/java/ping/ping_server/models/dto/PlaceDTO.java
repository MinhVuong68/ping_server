package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlaceDTO {
    private String placeName;
    private String address;
    private double latitude;
    private double longitude;
    private Long customerId;
}
