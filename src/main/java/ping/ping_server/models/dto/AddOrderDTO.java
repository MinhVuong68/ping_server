package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.OrderStatus;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class AddOrderDTO {
    private String fromAddress;
    private Double fromLatitude;
    private Double fromLongitude;
    private String toAddress;
    private Double toLatitude;
    private Double toLongitude;
    private String customerNote;
    private boolean backTo;
    private double distance;
    private double price;
    private double priceDiscount;
    private double totalPrice;
    private Long customerId;
    private Long driverId;
    private Long discountId;

    public AddOrderDTO() {
    }

    public AddOrderDTO(String fromAddress, Double fromLatitude, Double fromLongitude, String toAddress, Double toLatitude, Double toLongitude, String customerNote, boolean backTo, double distance, double price, double priceDiscount, double totalPrice, Long customerId, Long driverId, Long discountId) {
        this.fromAddress = fromAddress;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toAddress = toAddress;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.customerNote = customerNote;
        this.backTo = backTo;
        this.distance = distance;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.totalPrice = totalPrice;
        this.customerId = customerId;
        this.driverId = driverId;
        this.discountId = discountId;
    }
}
