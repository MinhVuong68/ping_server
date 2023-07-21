package ping.ping_server.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "_order")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String fromAddress;
    private double fromLatitude;
    private double fromLongitude;
    @Column(columnDefinition = "nvarchar(255)")
    private String toAddress;
    private double toLatitude;
    private double toLongitude;
    @Column(columnDefinition = "nvarchar(255)")
    private String customerNote;
    private boolean backTo;
    private LocalDateTime customerRequireAt;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String reasonDenied;
    private LocalDateTime driverAcceptAt;
    private double distance;
    private double price;
    private double priceDiscount;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "custmer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public Order(Long id, String fromAddress, double fromLatitude, double fromLongitude, String toAddress, double toLatitude, double toLongitude, String customerNote, boolean backTo, LocalDateTime customerRequireAt, OrderStatus orderStatus, String reasonDenied, LocalDateTime driverAcceptAt, double distance, double price, double priceDiscount, double totalPrice, Customer customer, Driver driver, Discount discount) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toAddress = toAddress;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.customerNote = customerNote;
        this.backTo = backTo;
        this.customerRequireAt = customerRequireAt;
        this.orderStatus = orderStatus;
        this.reasonDenied = reasonDenied;
        this.driverAcceptAt = driverAcceptAt;
        this.distance = distance;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.driver = driver;
        this.discount = discount;
    }

    public Order() {
    }
}
