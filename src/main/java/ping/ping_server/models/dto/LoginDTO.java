package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginDTO {
    private String phoneNumber;
    private String password;
}
