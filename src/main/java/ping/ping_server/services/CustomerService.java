package ping.ping_server.services;

import ping.ping_server.models.dto.CustomerRegisterDTO;
import ping.ping_server.models.dto.CustomerUpdateDTO;
import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.PasswordChangeDTO;

public interface CustomerService {
    Object register(CustomerRegisterDTO customerRegisterDTO);
    Object login(LoginDTO loginDTO);
    Object changePassword(PasswordChangeDTO passwordChangeDTO);

    void logout(String token);
    Object update(Long id, CustomerUpdateDTO customerUpdateDTO);
}
