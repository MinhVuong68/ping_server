package ping.ping_server.services.impl;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ping.ping_server.services.JwtService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${app.secretKey}")
    private String SECRET_KEY;

    @Override
    public String getPhoneNumberFromToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build();
        Claims claims = jwtParser.parseClaimsJws(token).getBody();
        return claims.get("phoneNumber", String.class);
    }

    @Override
    public boolean isTokenValid(String token) {
        return isTokenExpired(token);
    }

    @Override
    public String generateToken(String phoneNumber) {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder().claim("phoneNumber",phoneNumber)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration((new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 )))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


//    @Override
//    public String extractPhoneNumber(String token) {
//        return extractClaim(token,Claims::getSubject);
//    }
//
//    public String generateToken(String phoneNumber) {
//        Map<String,Object> claims = new HashMap<>();
//        return Jwts.builder().claim("phoneNumber",phoneNumber)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration((new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 )))
//                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    public boolean isTokenValid(String token) {
//        final String phoneNumber = extractPhoneNumber(token);
//        return isTokenExpired(token);
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignInKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
}
