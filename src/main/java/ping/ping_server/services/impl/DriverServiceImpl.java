package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingServletRequestParameterException;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Driver;
import ping.ping_server.models.DriverStatus;
import ping.ping_server.models.Order;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.StatusLocationDTO;
import ping.ping_server.models.dto.UpdateOrderStatusDTO;
import ping.ping_server.models.response.DriverResponse;
import ping.ping_server.models.response.OrdersOfDriverResponse;
import ping.ping_server.models.response.UpdateStatusAndLocationResponse;
import ping.ping_server.repositories.DriverRepository;
import ping.ping_server.repositories.OrderRepository;
import ping.ping_server.services.DriverService;
import ping.ping_server.services.JwtService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final JwtService jwtService;

    @Override
    public Object login(LoginDTO loginDTO) {
        Driver driver = driverRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if(driver == null)
            return AppException.builder().message("Account does not exist").code(400).build();
        else {
            if(passwordEncoder.matches(loginDTO.getPassword(),driver.getPassword())) {
                DriverResponse driverResponse = mapper.map(driver, DriverResponse.class);
                driverResponse.setToken(jwtService.generateToken(loginDTO.getPhoneNumber()));
                return driverResponse;
            }
            return AppException.builder().message("Incorrect username or password").code(400).build();
        }
    }
    @Override
    public List<DriverResponse> getAllDriverOnlineByVehicleId(Long vehicleId) {
        DriverStatus onlineStatus = DriverStatus.ONLINE;
        List<Driver> lsDriver =  driverRepository.findByDriverStatusAndVehicleId(onlineStatus,vehicleId);
        if(lsDriver.isEmpty()) return new ArrayList<DriverResponse>();
        List<DriverResponse> driverResponses = new ArrayList<>();
        for(Driver driver : lsDriver) {
            driverResponses.add(modelMapper.map(driver, DriverResponse.class));
        }
        return driverResponses;
    }

    @Override
    public Object updateStatusAndLocation(StatusLocationDTO statusLocationDTO) {
        Driver driver = driverRepository.findById(statusLocationDTO.getId()).orElse(null);
        if(driver!=null) {
            if (driver.getDriverStatus().equals(DriverStatus.BUSY)) {
                return AppException.builder().message("It is not allowed to turn off online/offline status updates while there is an order status").code(403).build();
            }
            driverRepository.updateDriverStatusAndLocation(
                    statusLocationDTO.getId(),
                    statusLocationDTO.getDriverStatus(),
                    statusLocationDTO.getCurrentLocation(),
                    statusLocationDTO.getLatitude(),
                    statusLocationDTO.getLongitude()
            );
            return modelMapper.map(statusLocationDTO, UpdateStatusAndLocationResponse.class);
        } else return null;
    }

    @Override
    public Object updateOrderStatus(Long driverId, Long orderId, OrderStatus orderStatus, String reasonDenied) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if(order==null) {
            return AppException.builder().code(404).message("Order not found").build();
        } else {
            if (order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
                return AppException.builder().code(403).message("can't update orders with COMPLETED status").build();
            } else {
                Driver driver = driverRepository.findById(driverId).orElse(null);
                if (driver==null) return  AppException.builder().code(404).message("Not found driver !");
                if(orderStatus.equals(OrderStatus.DRIVER_REJECT)){
                    if(reasonDenied==null) {
                        return AppException.builder().code(403).message("Missing order decline reason field when updating this status").build();
                    } else {
                        order.setOrderStatus(orderStatus);
                        order.setReasonDenied(reasonDenied);
                        driver.setDriverStatus(DriverStatus.ONLINE);
                        orderRepository.save(order);
                        driverRepository.save(driver);
                    }
                } else {
                    if(reasonDenied!=null) {
                        return AppException.builder().code(403).message("status does not match reason").build();
                    } else {
                        order.setOrderStatus(orderStatus);
                        if(orderStatus.equals(OrderStatus.COMPLETED)) driver.setDriverStatus(DriverStatus.ONLINE);
                        orderRepository.save(order);
                        driverRepository.save(driver);
                    }
                }
            }
        }
        return order;
    }

    @Override
    public Object getOrdersByOrderStatusAndDriverId(OrderStatus orderStatus, Long driverId) {
        return orderRepository.findByOrderStatusEqualsAndDriverId(orderStatus,driverId);
    }

    @Override
    public Object getOrdersFilterDate(Long driverId, String dateString) {
        int totalBill = 0;
        long revenue = 0;
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Order> orders = orderRepository.findByOrderStatusEqualsAndDriverId(OrderStatus.COMPLETED, driverId);
        System.out.println(orders);
        List<Order> ordersResult = new ArrayList<>();
        for (Order order : orders) {
            if (dateString.equals(order.getCustomerRequireAt().format(formatterDate))) {
                ordersResult.add(order);
                totalBill++;
                revenue += order.getTotalPrice();
            }
        }
        return OrdersOfDriverResponse
                .builder()
                .orders(ordersResult)
                .revenue(revenue)
                .totalBill(totalBill).build();

    }
}
