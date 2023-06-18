package hr.valecic.musicstorewebapp.security;

import java.io.IOException;

import hr.valecic.musicstorewebapp.Utilities.TimeUtils;
import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.enums.AuthType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;
import hr.valecic.musicstorewebapp.dal.service.PersonService;

@Component
@AllArgsConstructor
@Order(1)
public class LoginFilter extends OncePerRequestFilter {
    private LoginhistoryService loginhistoryService;
    private PersonService personService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("Filter");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() != AuthType.ANONYMOUS_USER.value &&
                !ifAdmin(authentication)) {
            // User is already authenticated, no need to execute filter logic
            insertLoginToDb(authentication, request);
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean ifAdmin(Authentication authentication) {
        boolean isPrincipalPerson = authentication.getPrincipal().getClass() == CustomPersonDetails.class;
        if  (isPrincipalPerson){
            String username = ((CustomPersonDetails) authentication.getPrincipal()).getUsername();
            boolean isPersonAdmin = username.equals(AuthType.ADMIN.value);
            return isPersonAdmin;
        }
        return false;
    }

    private void insertLoginToDb(Authentication authentication, HttpServletRequest request) {
        System.out.println("Login history added");
        Loginhistory loginhistoryEntry = new Loginhistory();
        String email = ((CustomPersonDetails) authentication.getPrincipal()).getUsername();
        Person person = personService.getPersonByEmail(email).get();

        loginhistoryEntry.setPerson(person);
        loginhistoryEntry.setIpadress(request.getRemoteAddr());
        loginhistoryEntry.setTimeoflogin(TimeUtils.getCurrentTime());

        loginhistoryService.saveLogin(loginhistoryEntry);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getMethod().equals("GET") || !request.getServletPath().equals("/home") && request.isRequestedSessionIdValid();
    }
}
