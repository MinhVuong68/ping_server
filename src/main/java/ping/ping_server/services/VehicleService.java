package ping.ping_server.services;

import ping.ping_server.models.Vehicle;
import ping.ping_server.models.dto.VehicleRequestDTO;

import java.util.List;

public interface VehicleService {
    Object addVehicle(VehicleRequestDTO vehicleRequestDTO);

    List<Vehicle> getAll();

    Object getVehicle(Long id);
}
