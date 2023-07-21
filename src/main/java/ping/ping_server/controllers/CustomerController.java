package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Customer;
import ping.ping_server.models.dto.CustomerRegisterDTO;
import ping.ping_server.models.dto.CustomerUpdateDTO;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.PasswordChangeDTO;
import ping.ping_server.models.response.CustomerResponse;
import ping.ping_server.repositories.CustomerRepository;
import ping.ping_server.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerRegisterDTO customerRegisterDTO) {
        Object response = customerService.register(customerRegisterDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Object response = customerService.login(loginDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        Object response = customerService.changePassword(passwordChangeDTO);
        if(response instanceof AppException) {
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        } return ResponseEntity.ok().body(response);
    }

    @GetMapping("/logout/{token}")
    public ResponseEntity<String> logout(@PathVariable("token") String token) {
        customerService.logout(token);
        return ResponseEntity.status(200).body("Logout successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@RequestBody CustomerUpdateDTO customerUpdateDTO) {
        Object response = customerService.update(id,customerUpdateDTO);
        if(response instanceof AppException) {
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        } return ResponseEntity.ok().body(response);
    }
}
