package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ping.ping_server.models.Order;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.OrderDTO.*;
import ping.ping_server.repositories.OrderRepository;
import ping.ping_server.services.OrderService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    private OrderDTO convertFromOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());

        TimeDTO requireAt = new TimeDTO();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        requireAt.setDate(order.getCustomerRequireAt().format(formatterDate));
        requireAt.setTime(
                order.getCustomerRequireAt().getHour()
                        + ":" + order.getCustomerRequireAt().getMinute()
                        + ":" + order.getCustomerRequireAt().getSecond()
        );
        orderDTO.setRequireAt(requireAt);

        LocationDTO fromLocation = new LocationDTO();
        fromLocation.setAddress(order.getFromAddress());
        fromLocation.setCoordinate(new CoordinatesDTO(order.getFromLatitude(),order.getFromLongitude()));
        orderDTO.setFromLocation(fromLocation);

        LocationDTO toLocation = new LocationDTO();
        toLocation.setAddress(order.getToAddress());
        toLocation.setCoordinate(new CoordinatesDTO(order.getToLatitude(),order.getToLongitude()));
        orderDTO.setToLocation(toLocation);

        orderDTO.setCustomerNote(order.getCustomerNote());
        orderDTO.setGoBack(order.isBackTo());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setPriceDiscount(order.getPriceDiscount());
        orderDTO.setCustomer(order.getCustomer());
        orderDTO.setDriver(mapper.map(order.getDriver(), DriverOrderDTO.class));
        orderDTO.setVehicle(order.getDriver().getVehicle());
        orderDTO.setOrderStatus(order.getOrderStatus());
        return orderDTO;
    }

    @Override
    public List<OrderDTO> getOrders(String status, String customerId) {
        List<Order> orders = new ArrayList<>();
        List<OrderDTO> orderDTOs = new ArrayList<>();
        if(status !=null && customerId!=null) {
            orders = orderRepository.findByOrderStatusEqualsAndCustomerId(OrderStatus.valueOf(status),Long.parseLong(customerId));
        }
        else if(status != null & customerId == null) {
            if(status.equalsIgnoreCase("completed")) {
                orders =  orderRepository.findByOrderStatusEquals(OrderStatus.COMPLETED);
            } else if (status.equalsIgnoreCase("coming")) {
                List<OrderStatus> comingStatuses = Arrays.asList(OrderStatus.DRIVER_ACCEPT,
                        OrderStatus.COMING,
                        OrderStatus.RECEIVING,
                        OrderStatus.DELIVERING);
                orders = orderRepository.findByOrderStatusIn(comingStatuses);
            } else {
                // Trạng thái không hợp lệ
                throw new IllegalArgumentException("Invalid status");
            }
        } else {
            assert customerId != null;
            orders = orderRepository.findByCustomerId(Long.parseLong(customerId));
        }

        for (Order order : orders) {
            OrderDTO orderDTO = convertFromOrderToOrderDTO(order);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));
        return convertFromOrderToOrderDTO(order);
    }
}
