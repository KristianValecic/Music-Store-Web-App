package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.repository.PersonRepository;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.security.AuthRequest;
import hr.valecic.musicstorewebapp.security.AuthResponse;
import hr.valecic.musicstorewebapp.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes({"isAuthenticated", "authRole"})
public class LoginController {
//    private AuthenticationManager authenticationManager;
    private PersonRepository personRepository;
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody @Valid AuthRequest auhtRequest, HttpServletRequest request) throws Exception {
//        authenticate(auhtRequest.getEmail(), auhtRequest.getPassword());

        final Person person = personRepository.findByEmail(auhtRequest.getEmail()).get();

//        final String token = jwtUtil.generateAccessToken(person);

//        return ResponseEntity.ok(new AuthResponse(token));

        try {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword());
//            Authentication result = userDetailsService.authenticate(authentication);

//            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtil.generateAccessToken(person);

            // Store the token in the client's session or send it as a response header
            request.getSession().setAttribute("token", token);
            // Or
            // response.addHeader("Authorization", "Bearer " + token);

            return "redirect:/home";
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return "login";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().getClass().equals(CustomPersonDetails.class)){
            model.addAttribute("authRole", "");
        }
        //Just for checking requests
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

        return "login";
    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }

    @GetMapping("/login-error")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) ((HttpSession) session)
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

}
