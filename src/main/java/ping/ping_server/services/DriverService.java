package ping.ping_server.services;

import ping.ping_server.models.Driver;
import ping.ping_server.models.DriverStatus;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.StatusLocationDTO;
import ping.ping_server.models.dto.UpdateOrderStatusDTO;
import ping.ping_server.models.response.DriverResponse;

import java.util.List;

public interface DriverService {

    Object login(LoginDTO loginDTO);
    List<DriverResponse> getAllDriverOnlineByVehicleId(Long vehicleId);

    Object updateStatusAndLocation(StatusLocationDTO statusLocationDTO);

    Object updateOrderStatus(Long driverId, Long orderId, OrderStatus orderStatus, String reasonDenied);

    Object getOrdersByOrderStatusAndDriverId(OrderStatus orderStatus, Long driverId);
}
