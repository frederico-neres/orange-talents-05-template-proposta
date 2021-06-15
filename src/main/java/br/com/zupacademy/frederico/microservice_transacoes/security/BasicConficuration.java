package br.com.zupacademy.frederico.microservice_transacoes.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class BasicConficuration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().httpStrictTransportSecurity().disable();
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests(request->
                request.antMatchers("/**")
                        .hasAuthority("SCOPE_client-scope")
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
}
