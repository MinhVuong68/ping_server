package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ping.ping_server.models.Order;
import ping.ping_server.models.OrderStatus;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByOrderStatusEquals(OrderStatus orderStatus);
    List<Order> findByOrderStatusEquals(OrderStatus orderStatus);
    List<Order> findByOrderStatusIn(List<OrderStatus> orderStatuses);
    List<Order> findByOrderStatusEqualsAndCustomerId(OrderStatus orderStatus, Long customerId);
    List<Order> findByCustomerId(Long customerId);

    Order findByDriverIdAndOrderStatus(Long driverId, OrderStatus orderStatus);

    List<Order> findByOrderStatusEqualsAndDriverId(OrderStatus orderStatus, Long driverId);

    //List<Order> findByDriverIdAndCustomerRequireAt(Long driver_id, LocalDateTime customerRequireAt);
}
