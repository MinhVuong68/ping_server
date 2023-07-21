package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ping.ping_server.models.Role;

@Getter
@Setter
@Builder
public class AdminResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private String role;
    private String token;

    public AdminResponse() {
    }

    public AdminResponse(Long id, String firstName, String lastName, String phoneNumber, String avatar, String role, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.role = role;
        this.token = token;
    }
}
