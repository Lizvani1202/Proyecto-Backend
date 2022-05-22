package bo.edu.ucb.sis.BackendProy.security;

import bo.edu.ucb.sis.BackendProy.contants.Contants;
import bo.edu.ucb.sis.BackendProy.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
    public JwtUser validate(String token){
        JwtUser jwtUser = null;
        try {

            Claims body = Jwts.parser()
                    .setSigningKey(Contants.YOUR_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            jwtUser = new JwtUser();
            jwtUser.setUserName(body.getSubject());
            jwtUser.setUserid(Long.parseLong((String)body.get(Contants.USER_ID)));
        }catch (Exception e){
            System.out.println("Error"+e);
        }
        return jwtUser;
    }
}
