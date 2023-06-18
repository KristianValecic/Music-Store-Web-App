package hr.valecic.musicstorewebapp.security;

import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    //    private AuthenticationManager authenticationManager;
//    private JwtUtil jwtUtil;
    private final PersonService personService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(password);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        System.out.println(password);

        UserDetails userDetails = new CustomPersonDetails(personService.getPersonByEmail(email).get());

        System.out.println(userDetails.getPassword());


        if (userDetails != null && userDetails.getUsername().equals(email) && userDetails.getPassword().equals(password)) {
            System.out.println("user exists");

            return new UsernamePasswordAuthenticationToken
                    (userDetails, password, userDetails.getAuthorities());
        } else {
            System.out.println("bad credentials");

            throw new
                    BadCredentialsException("External system authentication failed");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}