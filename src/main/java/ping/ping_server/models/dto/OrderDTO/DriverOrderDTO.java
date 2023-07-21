package ping.ping_server.models.dto.OrderDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DriverOrderDTO {
    private Long id;
    private String fullName;
    private String avatar;
    private String licensePlate;
    private double reviewRate;
    private String phoneNumber;

    public DriverOrderDTO() {
    }

    public DriverOrderDTO(Long id, String fullName, String avatar, String licensePlate, double reviewRate, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.avatar = avatar;
        this.licensePlate = licensePlate;
        this.reviewRate = reviewRate;
        this.phoneNumber = phoneNumber;
    }
}
