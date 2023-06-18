package hr.valecic.musicstorewebapp.controller;

import hr.valecic.musicstorewebapp.dal.service.PersonService;
import hr.valecic.musicstorewebapp.dal.service.RolesService;
import hr.valecic.musicstorewebapp.event.CustomSpringEvent;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.enums.RoleEnum;
import hr.valecic.musicstorewebapp.publisher.CustomSpringEventPublisher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegisterController {
    private PersonService personService;
    private RolesService roleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private CustomSpringEventPublisher customSpringEventPublisher;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("authRole", "");
        return "register";
    }

    @PostMapping("/register")
    public String saveOsoba(@ModelAttribute("newPerson") @Valid Person person, HttpServletRequest request, Model model) {
        try {
            customSpringEventPublisher.publishCustomEvent(person.getEmail());
        } catch (BadCredentialsException e){
            model.addAttribute("ErrorMessage", e.getMessage());
            return "register";
        }

        Optional<Person> personWithEmail = personService.getPersonByEmail(person.getEmail());

        model.addAttribute("ErrorMessage", "");

        if (!personWithEmail.isPresent()){
//            Person person = Person.getPersonFromDto(personDTO);

            person.setRole(roleService.getRoleByRoleName(RoleEnum.USER_ROLE.toString()));
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            personService.savePerson(person);

//            try {
//                request.login(person.getEmail(), person.getPassword());
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
//            authenticate(person, request);
//            authWithAuthManager(request, person.getPassword(), person.getEmail());

//            model.addAttribute("Message", "Success!");
            return "redirect:/login";
        } else if (personWithEmail.get().getEmail().trim().equals(person.getEmail().trim())) {
            model.addAttribute("ErrorMessage", "Email already in use!");
        }
        return "register";
    }

    private static void authenticate(@Valid Person person, HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        UserDetails userDetails = new CustomPersonDetails(person);
        Authentication authentication =  new UsernamePasswordAuthenticationToken(userDetails, person.getPassword());

        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
