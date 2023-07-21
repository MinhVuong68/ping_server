package ping.ping_server.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private String avatar;
    private String phoneContact;
    private String token;

    public CustomerResponse() {
    }

    public CustomerResponse(Long id, String name, String phoneNumber, String avatar, String phoneContact, String token) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.phoneContact = phoneContact;
        this.token = token;
    }
    @Override
    public String toString() {
        return "CustomerResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phoneContact='" + phoneContact + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
