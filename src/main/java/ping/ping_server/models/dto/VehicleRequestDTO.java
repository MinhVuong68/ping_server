package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@Builder
public class VehicleRequestDTO {
    private String nameVehicle;
    private String image;
    private Long weight;
    private double cbm;
    private double length;
    private double width;
    private double height;
}
