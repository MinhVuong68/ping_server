package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordChangeDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
