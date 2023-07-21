package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Vehicle;
import ping.ping_server.models.dto.VehicleRequestDTO;
import ping.ping_server.repositories.VehicleRepository;
import ping.ping_server.services.VehicleService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public Object addVehicle(VehicleRequestDTO vehicleRequestDTO) {
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public Object getVehicle(Long id) {
        Optional<Vehicle> optional = vehicleRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        } else return AppException.builder().message("Vehicle does not exist").code(404).build();
    }
}
