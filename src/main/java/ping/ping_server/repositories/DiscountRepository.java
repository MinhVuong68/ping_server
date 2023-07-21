package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ping.ping_server.models.Discount;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Discount findByCode(String code);
}
