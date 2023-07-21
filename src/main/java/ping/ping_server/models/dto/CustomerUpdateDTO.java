package ping.ping_server.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerUpdateDTO {
    private String name;
    //private String phoneNumber;
    private String avatar;
    private String phoneContact;
}
