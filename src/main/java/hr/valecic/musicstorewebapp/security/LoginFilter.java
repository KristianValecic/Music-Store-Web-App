package hr.valecic.musicstorewebapp.security;

import java.io.IOException;

import hr.valecic.musicstorewebapp.dal.service.LoginhistoryService;
import hr.valecic.musicstorewebapp.model.Loginhistory;
import hr.valecic.musicstorewebapp.model.Person;
import hr.valecic.musicstorewebapp.model.enums.AuthType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import hr.valecic.musicstorewebapp.model.CustomPersonDetails;

@Component
@AllArgsConstructor
@Order(1)
public class LoginFilter extends OncePerRequestFilter {
    private LoginhistoryService loginhistoryService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getPrincipal() != AuthType.ANONYMOUS_USER.value &&
                !ifAdmin(authentication)) {
            // User is already authenticated, no need to execute filter logic
            insertLoginToDb();
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

    private void insertLoginToDb() {
        Loginhistory loginhistoryEntry = new Loginhistory();
        System.out.println("insertLoginToDb");
//        loginhistoryEntry.setPersonByPersonid();
//        loginhistoryEntry.setIpadress();
//        loginhistoryEntry.setTimeoflogin();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getMethod().equals("GET") || !request.getServletPath().equals("/login") && request.isRequestedSessionIdValid();
    }
}
