package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Driver;
import ping.ping_server.models.DriverStatus;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.StatusLocationDTO;
import ping.ping_server.models.response.DriverResponse;
import ping.ping_server.models.response.UpdateStatusAndLocationResponse;
import ping.ping_server.repositories.DriverRepository;
import ping.ping_server.services.DriverService;
import ping.ping_server.services.JwtService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final JwtService jwtService;

    @Override
    public Object login(LoginDTO loginDTO) {
        Driver driver = driverRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if(driver == null)
            return AppException.builder().message("Account does not exist").code(400).build();
        else {
            if(passwordEncoder.matches(loginDTO.getPassword(),driver.getPassword())) {
                DriverResponse driverResponse = mapper.map(driver, DriverResponse.class);
                driverResponse.setToken(jwtService.generateToken(loginDTO.getPhoneNumber()));
                return driverResponse;
            }
            return AppException.builder().message("Incorrect username or password").code(400).build();
        }
    }
    @Override
    public List<DriverResponse> getAllDriverOnlineByVehicleId(Long vehicleId) {
        DriverStatus onlineStatus = DriverStatus.ONLINE;
        List<Driver> lsDriver =  driverRepository.findByDriverStatusAndVehicleId(onlineStatus,vehicleId);
        if(lsDriver.isEmpty()) return new ArrayList<DriverResponse>();
        List<DriverResponse> driverResponses = new ArrayList<>();
        for(Driver driver : lsDriver) {
            driverResponses.add(modelMapper.map(driver, DriverResponse.class));
        }
        return driverResponses;
    }

    @Override
    public Object updateStatusAndLocation(StatusLocationDTO statusLocationDTO) {
        Driver driver = driverRepository.findById(statusLocationDTO.getId()).orElse(null);
        if(driver!=null) {
            if (driver.getDriverStatus().equals(DriverStatus.BUSY)) {
                return AppException.builder().message("It is not allowed to turn off online/offline status updates while there is an order status").code(403).build();
            }
            driverRepository.updateDriverStatusAndLocation(
                    statusLocationDTO.getId(),
                    statusLocationDTO.getDriverStatus(),
                    statusLocationDTO.getCurrentLocation(),
                    statusLocationDTO.getLatitude(),
                    statusLocationDTO.getLongitude()
            );
            return modelMapper.map(statusLocationDTO, UpdateStatusAndLocationResponse.class);
        } else return null;
    }
}
