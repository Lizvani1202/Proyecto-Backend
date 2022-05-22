package bo.edu.ucb.sis.BackendProy.security;

import bo.edu.ucb.sis.BackendProy.contants.Contants;
import bo.edu.ucb.sis.BackendProy.model.JwtAuthentiacionToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthentiacionTokenFilter extends AbstractAuthenticationProcessingFilter {
    public JwtAuthentiacionTokenFilter() {
        super("/api/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {

        String header = request.getHeader(Contants.AUTHORIZATION_HEADER);
        if(header == null || !header.startsWith(Contants.BEARER_TOKEN)){
            throw new RuntimeException("JWT es incorrecto");
        }
        String authentacitonToken = header.substring(7);
        JwtAuthentiacionToken token = new JwtAuthentiacionToken(authentacitonToken);

        return getAuthenticationManager().authenticate(token);
    }
}
