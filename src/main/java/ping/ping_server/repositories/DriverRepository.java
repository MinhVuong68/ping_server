package ping.ping_server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ping.ping_server.models.Driver;
import ping.ping_server.models.DriverStatus;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {
    Driver findByPhoneNumber(String phoneNumber);
    List<Driver> findByDriverStatusAndVehicleId(DriverStatus driverStatus, Long vehicleId);
    @Transactional
    @Modifying
    @Query("UPDATE Driver d SET d.driverStatus = :status, d.currentLocation = :location, d.latitude = :latitude, d.longitude = :longitude WHERE d.id = :driverId")
    void updateDriverStatusAndLocation(
            @Param("driverId") Long driverId,
            @Param("status") DriverStatus status,
            @Param("location") String location,
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude
    );
}
