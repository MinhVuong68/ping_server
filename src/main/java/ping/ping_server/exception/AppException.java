package ping.ping_server.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AppException {
    private int code;
    private String message;
}
