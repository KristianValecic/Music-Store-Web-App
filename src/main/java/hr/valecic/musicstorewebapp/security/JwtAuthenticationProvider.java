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


        Person person = personService.getPersonByEmail(email);

//        String token = jwtUtil.generateAccessToken(person);

        // Load user details by email
        UserDetails userDetails = new CustomPersonDetails(personService.getPersonByEmail(email));

        // Perform additional authentication checks if needed (e.g., password verification)

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
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