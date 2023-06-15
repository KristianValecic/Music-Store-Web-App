package hr.valecic.musicstorewebapp.security;

import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.OncePerRequestFilter;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class AppSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/resources/**",
                                "/static/**", "/webjars/**", "/login",
                                "/register", "/css/**", "/script/**", "/authenticate"
                                , "/home", "/shoppingCart", "/itemPage/**",
                                "/addItemToCart", "/filterHome",
                                "/removeAmountFromCart/**", "/removeItemFromCart/**", "/clearShoppingCart",
                                "/butItems", "/buyPage")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/home", true)
                                .failureUrl("/login-error")
                                .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
