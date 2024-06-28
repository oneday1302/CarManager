package ua.foxminded.javaspring.carmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .anyRequest().authenticated()
        ).cors(withDefaults()
        ).oauth2ResourceServer((oauth0) -> oauth0
                .jwt(withDefaults()));
        return http.build();
    }
}
