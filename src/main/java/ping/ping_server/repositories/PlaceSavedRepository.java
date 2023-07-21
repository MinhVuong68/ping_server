package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ping.ping_server.models.PlaceSaved;

import java.util.List;

public interface PlaceSavedRepository extends JpaRepository<PlaceSaved,Long> {
    List<PlaceSaved> findByCustomerId(Long customerId);
}
