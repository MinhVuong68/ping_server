package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Driver;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.PasswordChangeDTO;
import ping.ping_server.models.response.EmployeeResponse;
import ping.ping_server.repositories.EmployeeRepository;
import ping.ping_server.services.EmployeeService;
import ping.ping_server.services.JwtService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final JwtService jwtService;
    @Override
    public Object login(LoginDTO loginDTO) {
        Driver employee = employeeRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if(employee == null)
            return AppException.builder().message("Account does not exist").code(400).build();
        else {
            if(passwordEncoder.matches(loginDTO.getPassword(),employee.getPassword())) {
                EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
                employeeResponse.setToken(jwtService.generateToken(loginDTO.getPhoneNumber()));
                return employeeResponse;
            }
            return AppException.builder().message("Incorrect username or password").code(400).build();
        }
    }

    @Override
    public Object changePassword(PasswordChangeDTO passwordChangeDTO) {
        return null;
    }
}
