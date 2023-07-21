package ping.ping_server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.services.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Object response = employeeService.login(loginDTO);
        if(response instanceof AppException)
            return ResponseEntity.status(((AppException) response).getCode()).body(response);
        return ResponseEntity.ok().body(response);
    }
}
