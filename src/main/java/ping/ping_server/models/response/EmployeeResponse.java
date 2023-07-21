package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private String token;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Long id, String firstName, String lastName, String phoneNumber, String avatar, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.token = token;
    }
}
