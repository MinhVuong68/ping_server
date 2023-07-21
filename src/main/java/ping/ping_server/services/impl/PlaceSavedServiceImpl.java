package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Customer;
import ping.ping_server.models.PlaceSaved;
import ping.ping_server.models.dto.PlaceDTO;
import ping.ping_server.repositories.CustomerRepository;
import ping.ping_server.repositories.PlaceSavedRepository;
import ping.ping_server.services.PlaceSavedService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceSavedServiceImpl implements PlaceSavedService {

    private final PlaceSavedRepository placeSavedRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PlaceSaved> getAllPlaceSavedByCustomerId(Long customerId) {
        return placeSavedRepository.findByCustomerId(customerId);
    }

    @Override
    public Object addPlace(PlaceDTO placeDTO) {
        if(placeDTO.getPlaceName().isEmpty())
            return AppException.builder().code(500).message("Place name is not empty!");
        else if(placeDTO.getAddress().isEmpty())
            return AppException.builder().code(500).message("Address is not empty!");
        else if(String.valueOf(placeDTO.getLatitude()).isEmpty())
            return AppException.builder().code(500).message("Latitude is not empty!");
        else if(String.valueOf(placeDTO.getLongitude()).isEmpty())
            return AppException.builder().code(500).message("Longitude is not empty!");
        else if(String.valueOf(placeDTO.getCustomerId()).isEmpty())
            return AppException.builder().code(500).message("customer id is not empty!");
        PlaceSaved placeSaved = modelMapper.map(placeDTO,PlaceSaved.class);
        Customer customer = customerRepository.findById(placeDTO.getCustomerId()).get();
        placeSaved.setCustomer(customer);
        return placeSavedRepository.save(placeSaved);
    }
}
