package ping.ping_server.services;

import ping.ping_server.models.dto.LoginDTO;
import ping.ping_server.models.dto.PasswordChangeDTO;

public interface EmployeeService {
    Object login(LoginDTO loginDTO);
    Object changePassword(PasswordChangeDTO passwordChangeDTO);
}
