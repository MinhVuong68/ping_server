package ping.ping_server.models.dto.OrderDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeDTO {
    private String time;
    private String date;

    public TimeDTO(String time, String date) {
        this.time = time;
        this.date = date;
    }

    public TimeDTO() {
    }
}
