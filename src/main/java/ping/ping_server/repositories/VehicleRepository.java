package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ping.ping_server.models.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
