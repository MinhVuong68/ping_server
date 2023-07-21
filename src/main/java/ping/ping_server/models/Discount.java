package ping.ping_server.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;
    private int percentageDiscount;
    private LocalDateTime expirationDate;
    private boolean isUsed;
    private String code;
    @OneToOne(mappedBy = "discount")
    private Order order;

    public Discount() {
    }

    public Discount(Long id, int percentageDiscount, LocalDateTime expirationDate, boolean isUsed, String code) {
        this.id = id;
        this.percentageDiscount = percentageDiscount;
        this.expirationDate = expirationDate;
        this.isUsed = isUsed;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
