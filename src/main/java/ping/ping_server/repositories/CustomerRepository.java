package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ping.ping_server.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByPhoneNumber(String phoneNumber);
}
