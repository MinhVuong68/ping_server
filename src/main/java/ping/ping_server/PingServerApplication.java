package ping.ping_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PingServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PingServerApplication.class, args);
    }
}
