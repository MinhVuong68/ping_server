package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.Order;

import java.util.List;

@Getter
@Setter
@Builder
public class OrdersOfDriverResponse{
    private List<Order> orders;
    private double revenue;
    private int totalBill;

    public OrdersOfDriverResponse() {
    }

    public OrdersOfDriverResponse(List<Order> orders, double revenue, int totalBill) {
        this.orders = orders;
        this.revenue = revenue;
        this.totalBill = totalBill;
    }
}
