package hr.valecic.musicstorewebapp.security;

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


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class AppSecurityConfig {

//    private PersonService personService;
//    private JwtRequestFilter jwtRequestFilter;
//    private JwtAuthenticationProvider jwtAuthenticationProvider;
//    private AuthenticationManager authManager;
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/resources/**",
                                "/static/**", "/webjars/**", "/login",
                                "/register", "/css/**", "/js/**", "/authenticate")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("email")
//                        .loginProcessingUrl("/authenticate")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login-error")
                        .permitAll()
                )
//                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout.permitAll())
//                .httpBasic(withDefaults())
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .httpBasic();
;
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
//                    .authenticationProvider(jwtAuthenticationProvider)
//                        .userDetailsService(personService)
                        .getSharedObject(AuthenticationManagerBuilder.class);
        //authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
