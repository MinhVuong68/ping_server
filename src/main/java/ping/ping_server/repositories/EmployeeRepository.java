package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ping.ping_server.models.Driver;

public interface EmployeeRepository extends JpaRepository<Driver, Long> {
    Driver findByPhoneNumber(String phoneNumber);

}
