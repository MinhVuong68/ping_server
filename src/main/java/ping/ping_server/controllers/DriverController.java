package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.OrderStatus;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.StatusLocationDTO;
import ping.ping_server.models.dto.UpdateOrderStatusDTO;
import ping.ping_server.models.response.DriverResponse;
import ping.ping_server.services.DriverService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/online/{vehicleId}")
    public ResponseEntity<List<DriverResponse>> getAllDriverOnlineByVehicleId(@PathVariable("vehicleId") Long id) {
        return ResponseEntity.ok().body(driverService.getAllDriverOnlineByVehicleId(id));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        Object response = driverService.login(loginDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/status_location")
    public ResponseEntity<?> updateStatusAndLocation(@RequestBody StatusLocationDTO statusLocationDTO) {
        Object response = driverService.updateStatusAndLocation(statusLocationDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<?> updateOrderStatus(
            @RequestParam(name = "driverId", required = true) Long driverId,
            @RequestParam(name = "orderId", required = true) Long orderId,
            @RequestParam(name = "orderStatus", required = true) OrderStatus orderStatus,
            @RequestParam(name = "reasonDenied", required = false) String reasonDenied
    ) {
        Object response = driverService.updateOrderStatus(driverId, orderId, orderStatus, reasonDenied);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getOrdersByOrderStatusAndDriverId")
    public ResponseEntity<?> getOrdersByOrderStatusAndDriverId(@RequestParam("orderStatus")OrderStatus orderStatus,@RequestParam("driverId") Long driverId) {
        Object response = driverService.getOrdersByOrderStatusAndDriverId(orderStatus,driverId);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

}
