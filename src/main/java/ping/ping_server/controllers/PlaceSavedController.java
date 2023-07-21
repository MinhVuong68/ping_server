package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.dto.PlaceDTO;
import ping.ping_server.services.PlaceSavedService;

@RestController
@RequestMapping("/api/v1/place-saved")
@RequiredArgsConstructor
public class PlaceSavedController {

    private final PlaceSavedService placeSavedService;
    @GetMapping("/{customer_id}")
    public ResponseEntity<?> getPlaceSaved(@PathVariable("customer_id") Long id) {
        return ResponseEntity.ok().body(placeSavedService.getAllPlaceSavedByCustomerId(id));
    }

    @PostMapping("")
    public ResponseEntity<?> addPlace(@RequestBody PlaceDTO placeDTO) {
        Object response = placeSavedService.addPlace(placeDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }
}
