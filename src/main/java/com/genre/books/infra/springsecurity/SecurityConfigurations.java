package com.genre.books.infra.springsecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login", "/login/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/login").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/login").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/book/{}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/book").permitAll()
                .requestMatchers(HttpMethod.GET, "/book").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/book/titulo").permitAll()
                .requestMatchers(HttpMethod.GET, "/book/genero").permitAll()
                .requestMatchers(HttpMethod.GET, "book/author").permitAll()
                .requestMatchers(HttpMethod.GET, "/book/name").permitAll()
                .requestMatchers(HttpMethod.PUT, "/book/{}").hasRole("ADMIN")
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
