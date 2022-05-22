package bo.edu.ucb.sis.BackendProy.security;

import bo.edu.ucb.sis.BackendProy.model.JwtAuthentiacionToken;
import bo.edu.ucb.sis.BackendProy.model.JwtUser;
import bo.edu.ucb.sis.BackendProy.model.JwtUserDeatils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticacionProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private JwtValidator validator;
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        JwtAuthentiacionToken jwtAuthentiacionToken = (JwtAuthentiacionToken) authentication;
        String token = jwtAuthentiacionToken.getToken();
        JwtUser jwtUser = validator.validate(token);
        if(jwtUser == null){
            throw new RuntimeException("Jwt es incorrecto");
        }
        return new JwtUserDeatils(jwtUser.getUserName(), jwtUser.getUserid(), token);
    }

    @Override
    public boolean supports(Class<?> authentication){
        return (JwtAuthentiacionToken.class.isAssignableFrom(authentication));
    }
}
