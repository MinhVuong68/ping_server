package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.OrderStatus;

@Getter
@Setter
@Builder
public class UpdateOrderStatusDTO {
    private Long driverId;
    private OrderStatus orderStatus;
}
