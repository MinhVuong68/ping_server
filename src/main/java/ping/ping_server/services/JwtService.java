package ping.ping_server.services;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getPhoneNumberFromToken(String token);
    boolean isTokenValid(String token);
    String generateToken(String phoneNumber);

}
