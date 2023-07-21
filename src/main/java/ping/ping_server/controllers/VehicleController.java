package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.dto.VehicleRequestDTO;
import ping.ping_server.services.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("")
    public ResponseEntity<?> getAllVehicle() {
        return ResponseEntity.ok(vehicleService.getAll());
    }
    @PostMapping("")
    public ResponseEntity<?> addVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO) {
        Object response = vehicleService.addVehicle(vehicleRequestDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicle(@PathVariable("id") Long id) {
        Object response = vehicleService.getVehicle(id);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

}
