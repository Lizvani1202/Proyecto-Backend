package bo.edu.ucb.sis.BackendProy.config;

import bo.edu.ucb.sis.BackendProy.security.JwtAuthentiacionEntryPoint;
import bo.edu.ucb.sis.BackendProy.security.JwtAuthentiacionTokenFilter;
import bo.edu.ucb.sis.BackendProy.security.JwtAuthenticacionProvider;
import bo.edu.ucb.sis.BackendProy.security.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

import java.util.Collections;

@EnableWebSecurity
@Component
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticacionProvider authenticacionProvider;

    @Autowired
    private JwtAuthentiacionEntryPoint entryPoint;
    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(authenticacionProvider));
    }

    @Bean
    public JwtAuthentiacionTokenFilter authentiacionTokenFilter(){
        JwtAuthentiacionTokenFilter filter = new JwtAuthentiacionTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;
    }

    @Override
    protected  void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
