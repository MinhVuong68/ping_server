package ping.ping_server.services;

import ping.ping_server.models.PlaceSaved;
import ping.ping_server.models.dto.PlaceDTO;

import java.util.List;

public interface PlaceSavedService {
    List<PlaceSaved> getAllPlaceSavedByCustomerId(Long customerId);

    Object addPlace(PlaceDTO placeDTO);
}
