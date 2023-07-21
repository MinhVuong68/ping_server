package ping.ping_server.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ping.ping_server.exception.AppException;
import ping.ping_server.models.Customer;
import ping.ping_server.models.dto.CustomerRegisterDTO;
import ping.ping_server.models.dto.CustomerUpdateDTO;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.PasswordChangeDTO;
import ping.ping_server.models.response.CustomerResponse;
import ping.ping_server.repositories.CustomerRepository;
import ping.ping_server.services.CustomerService;
import ping.ping_server.services.JwtService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final ModelMapper mapper;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.no_avatar}")
    private String noAvatar;

    private Object checkValidationFormRegister(CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = customerRepository.findByPhoneNumber(customerRegisterDTO.getPhoneNumber());
        if(customer != null)
            return AppException.builder().message("Account is already exist").code(409).build();
        if (!customerRegisterDTO.getPhoneNumber().matches("^0\\d{9}$"))
            return AppException.builder().message("phone number consists of 10 numbers and starts with 0").code(400).build();
        if(!customerRegisterDTO.getName().matches("^[\\p{L}\\s]{2,30}$"))
            return AppException.builder().message("Name consists of letters from 2 to 30 characters").code(400).build();
        return true;
    }

    @Override
    public Object register(CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = mapper.map(customerRegisterDTO, Customer.class);
        Object isValidForm = checkValidationFormRegister(customerRegisterDTO);
        if(isValidForm instanceof AppException) return isValidForm;
        else {
            customer.setAvatar(noAvatar);
            customer.setPhoneContact(customerRegisterDTO.getPhoneNumber());
            customerRepository.save(customer);
            CustomerResponse customerResponse = mapper.map(customer,CustomerResponse.class);
            customerResponse.setToken(jwtService.generateToken(customerRegisterDTO.getPhoneNumber()));
            return customerResponse;
        }
    }

    @Override
    public Object login(LoginDTO loginDTO) {
        Customer customer = customerRepository.findByPhoneNumber(loginDTO.getPhoneNumber());
        if(customer == null)
            return AppException.builder().message("Account does not exist").code(400).build();
        else {
            if(passwordEncoder.matches(loginDTO.getPassword(),customer.getPassword())) {
                CustomerResponse customerResponse = mapper.map(customer,CustomerResponse.class);
                customerResponse.setToken(jwtService.generateToken(loginDTO.getPhoneNumber()));
                return customerResponse;
            }
            return AppException.builder().message("Incorrect username or password").code(400).build();
        }
    }

    @Override
    public Object changePassword(PasswordChangeDTO passwordChangeDTO) {
        Long id = passwordChangeDTO.getId();
        Customer customer = customerRepository.findById(id).get();
        if(customer.getPassword() == null) {
            if(!passwordChangeDTO.getNewPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$"))
                return AppException.builder().message("Password must contain at least 8 characters including uppercase, lowercase letters, numbers and special characters").code(400).build();
            else {
                customer.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
                customerRepository.save(customer);
                return mapper.map(customer,CustomerResponse.class);
            }
        } else {
                if(!passwordEncoder.matches(passwordChangeDTO.getOldPassword(), customer.getPassword()))
                    return AppException.builder().message("Old password does not match").code(400).build();
                else {
                    customer.setPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
                    customerRepository.save(customer);
                    return mapper.map(customer,CustomerResponse.class);
                }
        }
    }

    @Override
    public void logout(String token) {
        //Object
    }

    @Override
    public Object update(Long id, CustomerUpdateDTO customerUpdateDTO) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) return AppException.builder().message("Customer not found").code(404).build();
        else {
            customer.setName(customerUpdateDTO.getName());
            customer.setAvatar(customerUpdateDTO.getAvatar());
            customer.setPhoneContact(customerUpdateDTO.getPhoneContact());
            return customerRepository.save(customer);
        }
    }
}
