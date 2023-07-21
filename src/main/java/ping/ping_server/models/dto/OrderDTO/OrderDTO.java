package ping.ping_server.models.dto.OrderDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.Customer;
import ping.ping_server.models.Driver;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.Vehicle;

@Getter
@Setter
@Builder
public class OrderDTO {
    private Long id;
    private TimeDTO requireAt;
    private LocationDTO fromLocation;
    private LocationDTO toLocation;
    private String customerNote;
    private boolean goBack;
    private double totalPrice;
    private double price;
    private double priceDiscount;
    private Customer customer;
    private DriverOrderDTO driver;
    private Vehicle vehicle;
    private OrderStatus orderStatus;

    public OrderDTO() {
    }

    public OrderDTO(Long id, TimeDTO requireAt, LocationDTO fromLocation, LocationDTO toLocation, String customerNote, boolean goBack, double totalPrice, double price, double priceDiscount, Customer customer, DriverOrderDTO driver, Vehicle vehicle, OrderStatus orderStatus) {
        this.id = id;
        this.requireAt = requireAt;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.customerNote = customerNote;
        this.goBack = goBack;
        this.totalPrice = totalPrice;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.customer = customer;
        this.driver = driver;
        this.vehicle = vehicle;
        this.orderStatus = orderStatus;
    }
}
