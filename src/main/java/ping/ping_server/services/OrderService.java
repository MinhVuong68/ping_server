package ping.ping_server.services;

import ping.ping_server.models.Order;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.OrderDTO.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrders(String status, String customerId);
    OrderDTO getOrderById(Long id);
}
