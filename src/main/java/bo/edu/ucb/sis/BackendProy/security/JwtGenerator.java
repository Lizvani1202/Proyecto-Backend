package bo.edu.ucb.sis.BackendProy.security;

import bo.edu.ucb.sis.BackendProy.contants.Contants;
import bo.edu.ucb.sis.BackendProy.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

@Component
public class JwtGenerator {
    public String generate(JwtUser jwtUser){
        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put(Contants.USER_ID,String.valueOf(jwtUser.getUserid()));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,Contants.YOUR_SECRET)
                .compact();
    }
}
